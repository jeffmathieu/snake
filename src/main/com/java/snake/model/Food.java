package com.java.snake.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Food {

    private Point position;
    private FoodType type;
    private Random random;

    public Food(Snake snake) {
        random = new Random();
        respawn(snake.getBody());
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

        FoodType[] types = FoodType.values();
        type = types[random.nextInt(types.length)];
    }

    public Point getPosition() {
        return position;
    }

    public FoodType getType() {
        return type;
    }

    public int getPoints(FoodType type) {
        switch(type) {
            case FoodType.APPLE -> {
                return 1;
            }
            case FoodType.BANANA -> {
                return 3;
            }
            case FoodType.CHERRY -> {
                return 5;
            }
        }
        return 0;
    }

}
