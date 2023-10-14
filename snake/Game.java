package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;

    private final int DOT_SIZE = 8;
    private final int DELAY = 140;

    private char direction;
    private boolean inGame = true;

    private Apple apple;
    private Snake snake;
    private Timer timer;

    public Game() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        initGame();
    }

    private void initGame() {
        apple = new Apple();
        snake = new Snake();
        direction = 'r';
        snake.move(direction);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.setColor(Color.red);
            g.fillRect(apple.getX(), apple.getY(), DOT_SIZE, DOT_SIZE);

            g.setColor(Color.green);
            g.fillRect(snake.getX(0), snake.getY(0), DOT_SIZE, DOT_SIZE);

            for (int z = 1; z < snake.getTail(); z++) {
                g.setColor(Color.green);
                g.fillRect(snake.getX(z), snake.getY(z), DOT_SIZE, DOT_SIZE);
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over.";
        String msg2 = "Press Space to Restart.";
        g.setColor(Color.WHITE);
        g.drawString(msg, (B_WIDTH - getFontMetrics(g.getFont()).stringWidth(msg)) / 2, B_HEIGHT / 2);
        g.drawString(msg2, (B_WIDTH - getFontMetrics(g.getFont()).stringWidth(msg2)) / 2, B_HEIGHT / 2 + 20);
    }

    private void checkApple() {
        if ((snake.getX(0) == apple.getX()) && (snake.getY(0) == apple.getY())) {
            snake.addTail();
            apple.newApple();
        }
    }

    private void checkColision() {
        if (snake.checkCollision() == false) {
            inGame = false;
            timer.stop();
        }
    }

    private void resetGame() {
        snake.reset(); 
        apple.newApple();
        direction = 'r';
        inGame = true; 
        timer.start(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            snake.move(direction);
            checkApple();
            checkColision();
        }
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_LEFT) && (direction != 'r')) {
                direction = 'l';
            }
            if ((key == KeyEvent.VK_RIGHT) && (direction != 'l')) {
                direction = 'r';
            }
            if ((key == KeyEvent.VK_UP) && (direction != 'd')) {
                direction = 'u';
            }
            if ((key == KeyEvent.VK_DOWN) && (direction != 'u')) {
                direction = 'd';
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE && !inGame) {
                resetGame();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
