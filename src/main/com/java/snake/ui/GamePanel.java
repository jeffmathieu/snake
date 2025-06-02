package com.java.snake.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java.snake.model.Direction;
import com.java.snake.model.Food;
import com.java.snake.model.Grid;
import com.java.snake.model.Snake;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Grid grid;
    private final int cellSize = 20;
    private final Timer timer;
    private final Snake snake;
    private final Food food;
    private Direction direction;

    public GamePanel() {
        this.grid = new Grid();
        setBackground(Color.pink);
        setFocusable(true);
        addKeyListener(this);

        this.timer = new Timer(1000/3, this);
        this.snake = new Snake();
        this.food = new Food();
        this.direction = Direction.RIGHT;

    }

    private void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void startGame() {
        timer.start();
    }

    public void pauseGame() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.PINK);
                for (java.awt.Point p : snake.getBody()) {
                    int offSet = 1;
                    g.fillRect(p.x * cellSize + offSet, p.y * cellSize + offSet, cellSize - 2*offSet, cellSize - 2*offSet);
                }
                g.setColor(Color.RED);
                Point p = food.getPosition();
                g.fillRect(p.x * cellSize, p.y * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(grid.getWidth() * cellSize, grid.getHeight() * cellSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move(direction);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> setDirection(Direction.RIGHT);
            case KeyEvent.VK_P -> pauseGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

