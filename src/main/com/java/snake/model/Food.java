package com.java.snake.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Food {

    private Point position;
    private Random random;

    public Food() {
        random = new Random();
        position = new Point(0, 0);
    }

    /**
     * Plaats het voedsel op een willekeurige vrije plek, gegeven de lijst met slang-segmenten.
     */
    public void respawn(List<Point> snakeBody) {
        List<Point> freeCells = new ArrayList<>();

        // Doorloop de hele grid
        for (int x = 0; x < Grid.COLS; x++) {
            for (int y = 0; y < Grid.ROWS; y++) {
                Point p = new Point(x, y);
                if (!snakeBody.contains(p)) {
                    freeCells.add(p);
                }
            }
        }

        // Kies een willekeurige vrije cel
        int idx = random.nextInt(freeCells.size());
        position = freeCells.get(idx);
    }

    public Point getPosition() {
        return position;
    }

}
