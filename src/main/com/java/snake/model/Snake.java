package com.java.snake.model;

import java.awt.*;
import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> body;      // body.getFirst() is head
    private Direction currentDirection;
    private boolean shouldGrow;          // Flag: volgende move niet het staartdeel verwijderen

    public Snake() {
        // Startpositie: midden van het scherm
        int startX = Grid.COLS / 2;
        int startY = Grid.ROWS / 2;
        body = new LinkedList<>();

        // Beginlengte 3: head + 2 segmenten naar links
        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        body.add(new Point(startX - 2, startY));

        currentDirection = Direction.RIGHT;  // Beginrichting
        shouldGrow = false;
    }

    /**
     * Verplaats de slang één stap in de gewenste richting (mits niet tegenovergesteld aan huidige richting).
     */
    public void move(Direction newDirection) {
        // Voorkom 180° bocht: negeer als tegenovergestelde
        if (!newDirection.isOpposite(currentDirection)) {
            currentDirection = newDirection;
        }

        // Huidige head
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

        // Als we niet moeten groeien, verwijder het laatste segment
        if (!shouldGrow) {
            body.removeLast();
        } else {
            // Reset de flag na groei
            shouldGrow = false;
        }
    }

    /**
     * Zet de vlag om te groeien. Bij de eerstvolgende move wordt de staart niet verwijderd,
     * waardoor de slang 1 segment langer wordt.
     */
    public void grow() {
        shouldGrow = true;
    }

    /**
     * Check botsing met eigen lichaam (head botst met één van de andere segmenten).
     */
    public boolean isCollidingWithSelf() {
        Point head = body.getFirst();
        // Check alle segmenten behalve index 0
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check botsing met de muren (buiten grid-grenzen).
     */
    public boolean isCollidingWithWall() {
        Point head = body.getFirst();
        return head.x < 0 || head.x >= Grid.COLS || head.y < 0 || head.y >= Grid.ROWS;
    }

    /**
     * Geeft de volledige lijst met segmenten (in grid-coördinaten).
     */
    public List<Point> getBody() {
        return body;
    }

    /**
     * Geeft de huidige positie van de kop (head).
     */
    public Point getHead() {
        return body.getFirst();
    }

    /**
     * Geeft huidige richting (kan handig zijn om in GamePanel te lezen).
     */
    public Direction getDirection() {
        return currentDirection;
    }

}
