package classGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Player {
    private int number;
    protected int x;
    protected int y;
    protected int direction;
    private boolean shot;
    protected int health;
    private int coins;
    private int points;
    private boolean createdCoinPileAfterDeath = false;
    private boolean dead = false;
    Map map = Map.getInstance();
    
    public Player(int num){
        this.number = num;
    }

    public int getNumber() {
        return number;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * @param coins the coins to set
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the shot
     */
    public boolean isShot() {
        return shot;
    }

    /**
     * @param shot the shot to set
     */
    public void setShot(boolean shot) {
        this.shot = shot;
    }
    
    public void cleanMap(){
        ArrayList<Coin> coinList = map.getCoin();

        Coin coin;
        Iterator<Coin> itc = coinList.iterator();
        while (itc.hasNext()) {
            coin = itc.next();

            if(coin.getX() == x && coin.getY() == y){
                coinList.remove(coin);
                break;
            }
        }
        
        ArrayList<Life> lifeList = map.getLife();

        Life life;
        Iterator<Life> itl = lifeList.iterator();
        while (itl.hasNext()) {
            life = itl.next();

            if(life.getX() == x && life.getY() == y){
                lifeList.remove(life);
                break;
            }
        }
    }

    /**
     * @return the createdCoinPileAfterDeath
     */
    public boolean hasCreatedCoinPileAfterDeath() {
        return createdCoinPileAfterDeath;
    }

    /**
     * @param createdCoinPileAfterDeath the createdCoinPileAfterDeath to set
     */
    public void setCreatedCoinPileAfterDeath(boolean createdCoinPileAfterDeath) {
        this.createdCoinPileAfterDeath = createdCoinPileAfterDeath;
    }

    /**
     * @return the dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * @param dead the dead to set
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
