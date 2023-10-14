/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

public class Snake {

    private int[] x;
    private int[] y;
    private int tail;

    private final int DOT_SIZE = 10;

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    public final int ALL_DOTS = 900;

    public Snake() {
        x = new int[300];
        y = new int[300];
        tail = 2;
        x[0] = 150;
        y[0] = 150;
        x[1] = 150;
        y[1] = 150;
        x[2] = 150;
        y[2] = 150;
    }

    public void move(char direction) {
        for (int z = tail; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }
        switch (direction) {
            case 'r' ->
                x[0] += DOT_SIZE;
            case 'u' ->
                y[0] -= DOT_SIZE;
            case 'l' ->
                x[0] -= DOT_SIZE;
            case 'd' ->
                y[0] += DOT_SIZE;
        }
    }

    public int getX(int q) {
        return x[q];
    }

    public int getY(int q) {
        return y[q];
    }

    public int getTail() {
        return tail;
    }

    public void addTail() {
        tail++;
    }

    public boolean checkCollision() {
        for (int z = tail; z > 0; z--) {
            if ((x[0] == x[z]) && (y[0] == y[z])) {
                return false;
            }
        }
        if (y[0] >= B_HEIGHT) {
            return false;
        }
        if (y[0] < 0) {
            return false;
        }
        if (x[0] >= B_WIDTH) {
            return false;
        }
        if (x[0] < 0) {
            return false;
        }
        return true;

    }
    
    void reset(){
        x = new int[300];
        y = new int[300];
        tail = 2;
        x[0] = 150;
        y[0] = 150;
        x[1] = 150;
        y[1] = 150;
        x[2] = 150;
        y[2] = 150;

    
    
    }
}
