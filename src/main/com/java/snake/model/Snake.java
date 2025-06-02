package com.java.snake.model;

import com.java.snake.ui.GamePanel;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> body;
    private Direction currentDirection;
    private boolean shouldGrow;

    public Snake() {
        int startX = Grid.COLS / 2;
        int startY = Grid.ROWS / 2;
        body = new LinkedList<>();

        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        body.add(new Point(startX - 2, startY));
        body.add(new Point(startX - 3, startY));

        currentDirection = Direction.RIGHT;
        shouldGrow = false;
    }

    public void move(Direction newDirection) {
        if (!newDirection.isOpposite(currentDirection)) {
            currentDirection = newDirection;
        }

        Point head = body.getFirst();
        int newX = head.x;
        int newY = head.y;

        switch (currentDirection) {
            case UP:    newY--; break;
            case DOWN:  newY++; break;
            case LEFT:  newX--; break;
            case RIGHT: newX++; break;
        }

        Point newHead = new Point(newX, newY);
        body.addFirst(newHead);

        if (!shouldGrow) {
            body.removeLast();
        } else {
            shouldGrow = false;
        }
    }

    public void grow() {
        shouldGrow = true;
    }

    public boolean isCollidingWithSelf() {
        Point head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }


    public boolean isCollidingWithWall() {
        Point head = body.getFirst();
        return head.x < 0 || head.x >= Grid.COLS || head.y < 0 || head.y >= Grid.ROWS;
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public Direction getDirection() {
        return currentDirection;
    }

}
