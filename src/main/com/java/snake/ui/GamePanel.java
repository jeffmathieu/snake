package com.java.snake.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.java.snake.model.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final Grid grid;
    private final int cellSize = 20;
    private final Timer timer;
    private final Snake snake;
    private final Food food;
    private Direction direction;
    private boolean gameOver = false;
    private Direction nextDirection = Direction.RIGHT;
    private Image appleImage;

    public GamePanel() {
        this.grid = new Grid();
        setBackground(Color.pink);
        setFocusable(true);
        addKeyListener(this);

        this.timer = new Timer(100, this);
        this.snake = new Snake();
        this.food = new Food(snake);
        this.direction = Direction.RIGHT;

        try {
            appleImage = new ImageIcon("src/main/recources/apple.png").getImage();
        } catch (Exception e) {
            appleImage = null;
        }

    }

    private void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void startGame() {
        timer.start();
    }

    public void pauseGame() {
        if (timer.isRunning()) timer.stop();
        else timer.start();
    }


    public void stopGame() {
        timer.stop();
        this.gameOver = true;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (!gameOver) {
            for (int y = 0; y < grid.getHeight(); y++) {
                for (int x = 0; x < grid.getWidth(); x++) {
                    int i = 1;
                    for (java.awt.Point p : snake.getBody()) {
                        int offSet = 1;
                        if (i == 1) {
                            g.setColor(new Color(0x038C1D));
                            g.setFont(new Font("Arial", Font.BOLD, 19));
                            FontMetrics fm = g.getFontMetrics();
                            g.drawString("🐍", p.x * cellSize, p.y * cellSize + fm.getAscent());

                            //g.fillRect(p.x * cellSize + offSet, p.y * cellSize + offSet, cellSize - 2 * offSet, cellSize - 2 * offSet);
                        } else {
                            if (i % 2 == 0) g.setColor(new Color(0x276301));
                            else g.setColor(new Color(0x184100));
                            g.fillRect(p.x * cellSize + offSet, p.y * cellSize + offSet, cellSize - 2 * offSet, cellSize - 2 * offSet);
                        }


                        i++;
                    }

                    Point p = food.getPosition();
                    FoodType type = food.getType();

                    String str = "";
                    switch (type) {
                        case APPLE -> str = "🍎";
                        case BANANA -> str = "🍌";
                        case CHERRY -> str = "🍒";
                    }

                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    FontMetrics fm = g.getFontMetrics();
                    g.drawString(str, p.x * cellSize, p.y * cellSize + fm.getAscent());

                }
            }
            String msg = "Score: " + snake.getScore();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g.getFontMetrics();
            int x = 2;
            int y = 2 + fm.getAscent();
            g.drawString(msg, x, y);
        } else {
            String msg = "GAME OVER";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(msg)) / 2;
            int y = (getHeight() - fm.getHeight()) / 3 +  2 * fm.getAscent();
            g.drawString(msg, x, y);
            String msg1 = "SCORE: " + snake.getScore();
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 28));
            FontMetrics fm1 = g.getFontMetrics();
            int x1 = (getWidth() - fm1.stringWidth(msg)) / 2;
            int y1 = (getHeight() - fm1.getHeight()) / 3 + fm1.getAscent() + 2 * fm.getAscent();
            g.drawString(msg1, x1, y1);
        }
    }

    public void repaintPLS() {
        repaint();
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(grid.getWidth() * cellSize, grid.getHeight() * cellSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        direction = nextDirection;
        if (snake.getGameOver()) stopGame();
        snake.move(direction, food);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) pauseGame();

        if (timer.isRunning()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> nextDirection = Direction.UP;
                case KeyEvent.VK_DOWN -> nextDirection = Direction.DOWN;
                case KeyEvent.VK_LEFT -> nextDirection = Direction.LEFT;
                case KeyEvent.VK_RIGHT -> nextDirection = Direction.RIGHT;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
            if (gameOver) {
                snake.reset();
                food.respawn(snake.getBody());
                gameOver = false;
                repaint();
                nextDirection = Direction.RIGHT;
                startGame();
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}

