/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classGame;

import java.util.TimerTask;

/**
 *
 * @author ThaRu
 */
public class Coin extends TimerTask{

    private int life;
    private int value;
    private int x;
    private int y;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        this.life=0;
        this.value=0;
    }

    public int getLife() {
        return life;
    }

    public int getValue() {
        return value;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param life the life to set
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}
