package kpi.iff;

import kpi.iff.utils.AppLogic;
import kpi.iff.utils.ChartImagePanel;
import kpi.iff.utils.DiffusionPanel;

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
    private final static int FORM_HEIGHT = 700;

    private final static int MAXIMUM_ITERATIONS = 5000; //set here count of iterations
    private final static int TIME_BETWEEN_ITERATIONS = 300; //set here delay time(ms)

    public static void main(String... args) {

        final JFrame frame = new JFrame("For my shining stars!");
        final DiffusionPanel diffusionPanel = new DiffusionPanel();
        final ChartImagePanel chartImagePanel = new ChartImagePanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(FORM_WIDTH, FORM_HEIGHT);
        GridLayout layout = new GridLayout(2,1);
        frame.setLayout(layout);

        frame.add(diffusionPanel);
        frame.add(chartImagePanel);

        frame.setVisible(true);

        final java.util.Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                frame.repaint();
                if(AppLogic.sTime == MAXIMUM_ITERATIONS) {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, TIME_BETWEEN_ITERATIONS);

    }

}
