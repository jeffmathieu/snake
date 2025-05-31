package com.java.snake.ui;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java.snake.model.Direction;
import com.java.snake.model.Grid;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Grid grid;
    private final int cellSize = 20;
    private final int GRID_SIZE_X = 40;
    private final int GRID_SIZE_Y = 30;
    private final Timer timer;

    public GamePanel() {
        this.grid = new Grid(GRID_SIZE_X, GRID_SIZE_Y);
        setBackground(Color.pink);

        this.timer = new Timer(1000/10, this);

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
                char c = grid.getCell(x, y);
                if (c == ' ') {
                    g.setColor(Color.BLACK);
                } else if (c == 'O') {
                    g.setColor(Color.GREEN);
                } else if (c == '*') {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(grid.getWidth() * cellSize, grid.getHeight() * cellSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid.move();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> grid.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN -> grid.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT -> grid.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> grid.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

