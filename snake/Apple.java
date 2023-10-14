/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

/**
 *
 * @author Rafael
 */
public class Apple {
        private int x;
    private int y;
    private final int DOT_SIZE = 10;
    private final int RAND_POS = 29;

    public Apple() {
        newApple();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void newApple() {
        int r = (int) (Math.random() * RAND_POS);
        x = r * DOT_SIZE;
        r = (int) (Math.random() * RAND_POS);
        y = r * DOT_SIZE;
    }
    
}
