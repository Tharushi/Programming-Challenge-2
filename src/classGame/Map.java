/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classGame;

import java.util.ArrayList;

/**
 *
 * @author ThaRu
 */
public class Map {
    final int gridLength = 20;
    private char map[][];
    private ArrayList<Coin> coin;
    private ArrayList<Life> life;
    private Player player[];
    
    private static Map instance = null;

    protected Map() {
        // Exists only to defeat instantiation.
        this.life = new ArrayList<>();
        this.coin = new ArrayList<>();
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }
    
    public void printMap(){
        for(int i=0; i<gridLength; i++){
            for(int j=0; j<gridLength; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @return the coin
     */
    public ArrayList<Coin> getCoin() {
        return coin;
    }

    /**
     * @param coin the coin to set
     */
    public void setCoin(ArrayList<Coin> coin) {
        this.coin = coin;
    }

    /**
     * @return the life
     */
    public ArrayList<Life> getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(ArrayList<Life> life) {
        this.life = life;
    }

    /**
     * @return the player
     */
    public Player[] getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player[] player) {
        this.player = player;
    }
}
