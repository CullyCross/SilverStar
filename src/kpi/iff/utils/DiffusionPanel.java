package kpi.iff.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cullycross on 2/10/15.
 */
public class DiffusionPanel extends JPanel {

    private final static int PANEL_WIDTH = 400;
    private final static int PANEL_HEIGHT = 300;

    public final static int MARGIN = 10;

    private final AppLogic mAppLogic;

    public DiffusionPanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, 0, MARGIN));
        mAppLogic = AppLogic.getInstance();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mAppLogic.start(g);
    }
}
