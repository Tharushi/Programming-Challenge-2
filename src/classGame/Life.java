/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classGame;

/**
 *
 * @author ThaRu
 */
public class Life {

    private int life;
    private int x;
    private int y;

    /**
     *
     * @param x
     * @param y
     * @param life
     */
    public Life(int x, int y) {
        this.x = x;
        this.y = y;
        this.life=0;
    }

    public int getLife() {
        return life;
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

    /**
     * @param life the life to set
     */
    public void setLife(int life) {
        this.life = life;
    }
}
