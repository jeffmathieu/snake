package com.java.snake.model;

import java.awt.*;


public class Grid {

    public static final int CELL_SIZE = 20;
    public static final int COLS = 40;
    public static final int ROWS = 30;

    public static Dimension getPanelSize() {
        return new Dimension(CELL_SIZE * COLS, CELL_SIZE * ROWS);
    }

    public static Point gridToPixel(int gridX, int gridY) {
        return new Point(gridX * CELL_SIZE, gridY * CELL_SIZE);
    }

    public int getWidth() {
        return COLS;
    }

    public int getHeight() {
        return ROWS;
    }
}
