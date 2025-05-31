package com.java.snake.model;

public class Snake {

    private char[][] snakeGrid;
    private int INIT_SNAKE_SIZE = 5;

    public Snake(int x, int y) {
        this.snakeGrid = new char[x][y];
    }

}
