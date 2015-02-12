package kpi.iff;

import kpi.iff.utils.AppLogic;
import kpi.iff.utils.Panel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by cullycross on 2/10/15.
 */
public class Frame {

    //form size
    private final static int FORM_WIDTH = 450;
    private final static int FORM_HEIGHT = 600;

    private final static int MAXIMUM_ITERATIONS = 5000; //set here count of iterations
    private final static int TIME_BETWEEN_ITERATIONS = 150; //set here delay time(ms)

    public static void main(String... args) {

        final JFrame frame = new JFrame("For my shining stars!");
        final Panel panel = new Panel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(FORM_WIDTH, FORM_HEIGHT);
        GridLayout layout = new GridLayout(2,1);
        frame.setLayout(layout);

        frame.add(panel);
        //объект кривой
        final XYSeries series = new XYSeries("data");
        //данные
        final XYDataset dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart("Chart",
                "Iterations", "Concentration", dataset, PlotOrientation.VERTICAL, false, true, false);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(Panel.MARGIN,
                Panel.MARGIN,
                Panel.MARGIN,
                Panel.MARGIN));


        frame.getContentPane().add(new ChartPanel(chart));

        frame.setVisible(true);

        final java.util.Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                series.add((double)AppLogic.sTime, (double)AppLogic.getCurrentConcentration());

                frame.repaint();
                if(AppLogic.sTime == MAXIMUM_ITERATIONS) {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, TIME_BETWEEN_ITERATIONS);

    }

}
