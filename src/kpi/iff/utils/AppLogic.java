package kpi.iff.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by cullycross on 2/10/15.
 */
public class AppLogic {

    private static final int MATRIX_WIDTH = 400;
    private static final int MATRIX_HEIGHT = 300;
    private static final int MATRIX_HALF = 200;

    private static final int MARGIN = 25;

    private static final int GOLD = 1;
    private static final int SILVER = -1;

    private static final int POINT_SIZE = 1;

    //private static final int CROSS_CHANCE = 104;
    //private static final int XY_CHANCE = 146;
    private static final int FULL_CHANCE = 1000;

    public volatile static int sTime;

    private int [][] mMatrix = new int[MATRIX_HEIGHT][MATRIX_WIDTH];
    private static int sCurrentConcentration = 0;
    private Random mRandom = new Random();

    public synchronized static int getCurrentConcentration() { return sCurrentConcentration; }

    public AppLogic() {

        for(int i = 0; i < MATRIX_HEIGHT; i++) {
            for (int j = 0; j < MATRIX_WIDTH; j++) {
                mMatrix[i][j] = j <= MATRIX_HALF ? GOLD : SILVER;
            }
        }
    }

    public void start(final Graphics g) {
        update();
        render(g);
    }

    private void swapElements(int iFrom,
                              int jFrom,
                              int iTo,
                              int jTo) {
        int temp = mMatrix[iFrom][jFrom];
        mMatrix[iFrom][jFrom] = mMatrix[iTo][jTo];
        mMatrix[iTo][jTo] = temp;
    }

    private void swap(int i, int j) {

        int chance = mRandom.nextInt(FULL_CHANCE);

        if(chance < 416) {
            if(chance < 104 && i > 0 && j < MATRIX_WIDTH - 2) { //North-East

                swapElements(i, j, i - 1, j + 1);
            } else if(chance < 208 && i < MATRIX_HEIGHT - 2 && j < MATRIX_WIDTH - 2) { //South-East

                swapElements(i, j, i + 1, j + 1);
            } else if(chance < 312 && i < MATRIX_HEIGHT - 2 && j > 0) { //South-West

                swapElements(i, j, i + 1, j - 1);
            } else if (i > 0 && j > 0){ //North-West

                swapElements(i, j, i - 1, j - 1);
            }
        } else if(chance >= 416) {
            if(chance < 562 && i > 0) { //North

                swapElements(i, j, i - 1, j);
            } else if(chance < 708 && j < MATRIX_WIDTH - 2) { //East

                swapElements(i, j, i, j + 1);
            } else if(chance < 854 && i < MATRIX_HEIGHT - 2) { //South

                swapElements(i, j, i + 1, j);
            } else if(j > 0){ //West

                swapElements(i, j, i, j - 1);
            }
        }
    }

    private void update() {

        int counter = 0;
        for(int i = 0; i < MATRIX_HEIGHT; i++) {
            for(int j = 0; j < MATRIX_WIDTH; j++) {
                swap(i,j);
            }
        }
        for(int i = 0; i < MATRIX_HEIGHT; i++) {
            for(int j = MATRIX_HALF + 1; j < MATRIX_WIDTH; j++) {
                if(mMatrix[i][j] == GOLD) counter++;
            }
        }
        sCurrentConcentration = counter;
    }

    private void render(Graphics g) {

        for(int i = 0; i < MATRIX_HEIGHT; i++) {
            for(int j = 0; j < MATRIX_WIDTH; j++) {
                g.setColor(
                        mMatrix[i][j]==GOLD?Color.ORANGE:Color.GRAY
                );

                g.drawRect(
                        POINT_SIZE*j + MARGIN,
                        POINT_SIZE*i + MARGIN,
                        POINT_SIZE,
                        POINT_SIZE
                );
            }
        }

        String diff = Integer.toString(sTime++) + " iter.";
        g.setColor(Color.BLACK);

        g.setFont(new Font("SanSerif", Font.PLAIN, 20));
        g.drawString(diff, 65, 100);

        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(sCurrentConcentration), 325, 100);
    }
}
