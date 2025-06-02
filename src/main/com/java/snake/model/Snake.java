package com.java.snake.model;

import com.java.snake.ui.GamePanel;
import com.java.snake.ui.GameWindow;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> body;
    private Direction currentDirection;
    private boolean gameOver = false;
    private boolean shouldAdded = false;
    private Point newHead;
    private int score = 0;

    public Snake() {
        int startX = Grid.COLS / 2;
        int startY = Grid.ROWS / 2;
        body = new LinkedList<>();

        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        body.add(new Point(startX - 2, startY));
        body.add(new Point(startX - 3, startY));

        currentDirection = Direction.RIGHT;
    }

    public void move(Direction newDirection, Food food) {
        if (! shouldAdded) {
            if (!newDirection.isOpposite(currentDirection)) {
                currentDirection = newDirection;
            }

            Point head = body.getFirst();
            int newX = head.x;
            int newY = head.y;

            switch (currentDirection) {
                case UP:
                    newY--;
                    break;
                case DOWN:
                    newY++;
                    break;
                case LEFT:
                    newX--;
                    break;
                case RIGHT:
                    newX++;
                    break;
            }

            newHead = new Point(newX, newY);

            if (body.getFirst().x == food.getPosition().x && body.getFirst().y == food.getPosition().y) {
                shouldAdded = true;
                food.respawn(body);
                score += 1;
            } else {
                body.removeLast();
                body.addFirst(newHead);
            }

            if (isCollidingWithSelf() || isCollidingWithWall()) {
                gameOver();
            }
        } else {
            body.addFirst(newHead);
            shouldAdded = false;
        }
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
        return head.x < 0 || head.x >= Grid.COLS || head.y < 0 || head.y >= Grid.ROWS - 1;
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

    private void gameOver() {
        this.gameOver = true;
    }

    public boolean getGameOver() {
        return this.gameOver;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        int startX = Grid.COLS / 2;
        int startY = Grid.ROWS / 2;
        body = new LinkedList<>();

        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        body.add(new Point(startX - 2, startY));
        body.add(new Point(startX - 3, startY));

        currentDirection = Direction.RIGHT;
        score = 0;
        this.gameOver = false;
    }

}
