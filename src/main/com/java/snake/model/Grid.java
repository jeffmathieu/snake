package com.java.snake.model;

public class Grid {

    private final int width;
    private final int height;
    private char[][] cells;

    private Snake snake;
    private Food food;

    private Direction direction;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new char[height][width];

        this.snake = new Snake(width, height);
        this.food = new Food();

        this.direction = Direction.RIGHT;
        clear();
    }

    public void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = ' ';
            }
        }
    }

    public void setCell(int x, int y, char c) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            cells[y][x] = c;
        }
    }

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(cells[y][x]);
            }
            System.out.println();
        }
    }

    public void move() {
        //TODO: move
    }

    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char getCell(int x, int y) {
        return cells[x][y];
    }
}
