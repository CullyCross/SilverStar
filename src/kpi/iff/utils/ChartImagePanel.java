package kpi.iff.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cullycross on 2/12/15.
 */
public class ChartImagePanel extends JPanel {

    private final static int PANEL_WIDTH = 400;
    private final static int PANEL_HEIGHT = 300;

    public final static int MARGIN = 10;

    private final AppLogic mAppLogic;

    public ChartImagePanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(0, MARGIN, MARGIN, MARGIN));
        mAppLogic = AppLogic.getInstance();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mAppLogic.renderChart(g);
    }
}
