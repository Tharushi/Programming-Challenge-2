/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classGame;

import AI.GenPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class MyPlayer extends Player {

    Map map;
    int gridLength = 20;
    int path[][] = new int[gridLength][gridLength];
    boolean gotPath = false;        //if false just roaming

    public MyPlayer(int num) {
        super(num);
        map = Map.getInstance();
    }

    /*
     * Directions
     0 North
     1 East
     2 South 
     3 West 
     */
    public String nextMove() {
        createPath();
        String move = null;

        //shoot
        Player player[] = map.getPlayer();
        for (int i = 0; i < player.length; i++) {
            if (!player[i].isDead()) {
                if (player[i].getX() == getX()) {                 // if the player is in the same column
                    boolean stone = false;

                    if (getY() < player[i].getY()) {              //if the player is below
                        for (int j = getY(); j < player[i].getY(); j++) {
                            if (map.getMap()[j][getX()] == 'S' || map.getMap()[j][getX()] == 'B') {
                                stone = true;
                                break;
                            }
                        }
                        if (getDirection() == 2 && !stone) {                //if I am facing the enemy
                            move = "SHOOT#";
                            return move;
                        } else {                                  
                        }
                    } else if (getY() > player[i].getY()) {//If I am above the object
                        for (int j = player[i].getY(); j < getY(); j++) {
                            if (map.getMap()[j][getX()] == 'S' || map.getMap()[j][getX()] == 'B') {
                                stone = true;
                                break;
                            }
                        }
                        if (getDirection() == 0 && !stone) {//I am facing an enemy
                            move = "SHOOT#";
                            return move;
                        } else {
                        }
                    }
                } else if (player[i].getY() == getY()) {//Object and I at the same row
                    boolean stone = false;

                    if (getX() < player[i].getX()) {//Object is left to me
                        for (int j = getX(); j < player[i].getX(); j++) {
                            if (map.getMap()[getY()][j] == 'S' || map.getMap()[getY()][j] == 'B') {
                                stone = true;
                                break;
                            }
                        }
                        if (getDirection() == 1 && !stone) {//I am facing the object and the object is an enemy
                            move = "SHOOT#";
                            return move;
                        } else {
                        }
                    } else if (getX() < player[i].getX()) {//Object is right to me
                        for (int j = player[i].getX(); j < getX(); j++) {
                            if (map.getMap()[getY()][j] == 'S' || map.getMap()[getY()][j] == 'B') {
                                stone = true;
                                break;
                            }
                        }
                        if (getDirection() == 3 && !stone) {//I am facing the object and the object is an enemy
                            move = "SHOOT#";
                            return move;
                        } else {
                        }
                    }
                }
            }

        }

        //goin to coins or life
        if (getX() + 1 < gridLength && path[getY()][getX() + 1] == 1) {
            //wants to go 1 cell to east
            //if the player is facing east it moves 1 cell to east otherwise turns to left
            move = "RIGHT#";
        } else if (getX() - 1 >= 0 && path[getY()][getX() - 1] == 1) {
            move = "LEFT#";
        } else if (getY() + 1 < gridLength && path[getY() + 1][getX()] == 1) {
            move = "DOWN#";
        } else if (getY() - 1 >= 0 && path[getY() - 1][getX()] == 1) {
            move = "UP#";
        }

        return move;
    }

    public void createPath() {

        if (health == 50 && !map.getLife().isEmpty()) {//Priority to health if my power is low
            System.out.println("generating path life");
            //go to the health pack if life is too low
            gotPath = true;
            createPathLife();
        } else if (!map.getCoin().isEmpty()) {//Next priority to get coins if I have enough power
            gotPath = true;
            System.out.println("generating path coin");
            createPathCoin();
        } else if (!map.getLife().isEmpty()) {//If I do not have coins to collect, priority goes to health packs
            System.out.println("generating path life");
            //go to the health pack
            gotPath = true;
            createPathLife();
        } else {
            System.out.println("generating path roam");//Else move 
            createPathRoam();
        }

        System.out.println("Me>");
        for (int i = 0; i < gridLength; i++) {//Printing my path
            for (int j = 0; j < gridLength; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void createPathCoin() {

        final GenPath genPath = new GenPath();
        genPath.setGrid(map.getMap());
        genPath.setDir(direction);//setting the location coordinates and my direction
        genPath.setXs(getX());
        genPath.setYs(getY());

        genPath.generatePath();
        ArrayList<Coin> coinList = map.getCoin();
        System.out.println("Coin piles " + coinList.size());
        
        //sort the coin list in the descending order of value
        Collections.sort(coinList, new Comparator<Coin>() {
            @Override
            public int compare(Coin coin1, Coin coin2) {//comparing the values of the coins
                return Integer.compare(genPath.getNodeArr()[coin1.getY()][coin1.getX()].getD(), genPath.getNodeArr()[coin2.getY()][coin2.getX()].getD());
            }
        });

        Coin coin = coinList.get(0);//Getting the coin with the maximum value
        genPath.plotPath(coin.getX(), coin.getY());//Generate a path to the coin
        path = genPath.getPath();
        System.out.println();

    }

    private void createPathLife() {
        GenPath genPath = new GenPath();
        genPath.setGrid(map.getMap());//Getting the locations of the objects from map
        genPath.setDir(direction);
        genPath.setXs(getX());
        genPath.setYs(getY());

        genPath.generatePath();
        ArrayList<Life> lifeList = map.getLife();

        //iterate through the sorted coin list to find a coin that can reach in the life time
        Life life = lifeList.get(0);
        Iterator<Life> it = lifeList.iterator();
        while (it.hasNext()) {
            life = it.next();

            if (life.getLife() >= genPath.getNodeArr()[life.getY()][life.getX()].getD()) {
                break;
            }
        }

        genPath.plotPath(life.getX(), life.getY());
        path = genPath.getPath();

    }

    private void createPathRoam() {
        if (direction == 1) {
            if (getX() + 1 < gridLength && map.getMap()[getY()][getX() + 1] == 'E') {
                path[getY()][getX() + 1] = 1;
            }
        } else if (direction == 2) {
            if (getY() - 1 >= 0 && map.getMap()[getY() - 1][getX()] == 'E') {
                path[getY() - 1][getX()] = 1;
            }
        } else if (direction == 3) {
            if (getX() - 1 >= 0 && map.getMap()[getY()][getX() - 1] == 'E') {
                path[getY()][getX() - 1] = 1;
            }
        } else if (direction == 0) {
            if (getY() + 1 < gridLength && map.getMap()[getY() + 1][getX()] == 'E') {
                path[getY() + 1][getX()] = 1;
            }
        }
    }

    public void printlocation() {
        System.out.println("my location> " + getX() + "," + getY());
    }
}
