package com.java.snake.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Food {

    private Point position;
    private Random random;

    public Food() {
        random = new Random();
        position = new Point(7, 10);
    }

    public void respawn(LinkedList<Point> snakeBody) {
        LinkedList<Point> freeCells = new LinkedList<>();

        for (int x = 1; x < Grid.COLS - 1; x++) {
            for (int y = 1; y < Grid.ROWS - 2; y++) {
                Point p = new Point(x, y);
                if (!snakeBody.contains(p)) {
                    freeCells.add(p);
                }
            }
        }

        int idx = random.nextInt(freeCells.size());
        position = freeCells.get(idx);
    }

    public Point getPosition() {
        return position;
    }

}
