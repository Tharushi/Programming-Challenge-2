package classGame;

import java.io.IOException;
import java.util.ArrayList;

public class GameInit {

    int gridLength = 20;
    int players = 5;
    int availablePlayers;
    char map[][] = new char[gridLength][gridLength];
    Map mapObj = Map.getInstance();
    /*    
     * map is initialized as 
     * water W
     * brick B
     * Stone S
     * Coin C
     * Life pack L
     */
    int damage[][] = new int[gridLength][gridLength];
    Coin coinMap[][] = new Coin[gridLength][gridLength];
    Life lifeMap[][] = new Life[gridLength][gridLength];
    ArrayList<Coin> coin;
    ArrayList<Life> life;
    Player player[];
    int myNumber;
    MyPlayer myPlayer;
    private Client client;

    public GameInit() {
        this.life = new ArrayList<>();
        this.coin = new ArrayList<>();

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                map[i][j] = 'E';
            }
        }
        mapObj.setMap(map);
        mapObj.setCoin(coin);
        mapObj.setLife(life);


        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                damage[i][j] = 0;
            }
        }

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                coinMap[i][j] = new Coin(j, i);
            }
        }

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                lifeMap[i][j] = new Life(j, i);
            }
        }

        client = Client.getInstance();
    }

    public void initMap(String input) {
        input = input.substring(0, input.length() - 1);
        String temp[] = input.split(":");

        myNumber = Integer.parseInt(temp[1].charAt(1) + "");

        String brick[] = temp[2].split(";");
        for (int i = 0; i < brick.length; i++) {
            String temp1[] = brick[i].split(",");
            int x = Integer.parseInt(temp1[0]);
            int y = Integer.parseInt(temp1[1]);

            map[y][x] = 'B';
        }

        String stone[] = temp[3].split(";");
        for (int i = 0; i < stone.length; i++) {
            String temp1[] = stone[i].split(",");
            int x = Integer.parseInt(temp1[0]);
            int y = Integer.parseInt(temp1[1]);

            map[y][x] = 'S';
        }

        String water[] = temp[4].split(";");
        for (int i = 0; i < water.length; i++) {
            String temp1[] = water[i].split(",");
            int x = Integer.parseInt(temp1[0]);
            int y = Integer.parseInt(temp1[1]);

            map[y][x] = 'W';
        }

//        System.out.println();
//        for (int i = 0; i < gridLength; i++) {
//            for (int j = 0; j < gridLength; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        mapObj.printMap();

    }

    public void initPlayers(String input) {
        input = input.substring(0, input.length() - 1);
        String temp[] = input.split(":");
        availablePlayers = temp.length - 1;
//        System.out.println("                  " + availablePlayers);
        player = new Player[availablePlayers];
        mapObj.setPlayer(player);

        for (int i = 1; i < availablePlayers + 1; i++) {
            String playerTemp[] = temp[i].split(";");
            String temp1[] = playerTemp[1].split(",");
            int x = Integer.parseInt(temp1[0]);
            int y = Integer.parseInt(temp1[1]);


            int dir = Integer.parseInt(playerTemp[2]);

//            if(i-1 == myNumber){
//                player[i-1] = new MyPlayer(i-1);
//            }else{
            player[i - 1] = new Player(i - 1);
            //}
            player[i - 1].setDirection(dir);
            player[i - 1].setX(x);
            player[i - 1].setY(y);

            if (i - 1 == myNumber) {
                myPlayer = new MyPlayer(i - 1);
                myPlayer.setDirection(dir);
                myPlayer.setX(x);
                myPlayer.setY(y);
                myPlayer.createPath();
            }
        }



//        for (int i = 1; i < availablePlayers + 1; i++) {
//            System.out.println("Player " + i + ":" + player[i - 1].getX() + "," + player[i - 1].getY() + " " + player[i - 1].getDirection());
//        }
    }

    public void globalUpdate(String input) throws IOException {

        String temp[] = input.split(":");
//        System.out.println("global " + input);

        for (int i = 1; i < availablePlayers + 1; i++) {
            String playerTemp[] = temp[i].split(";");

            String temp1[] = playerTemp[1].split(",");

            player[i - 1].setX(Integer.parseInt(temp1[0]));
            player[i - 1].setY(Integer.parseInt(temp1[1]));
            player[i - 1].setDirection(Integer.parseInt(playerTemp[2]));

            int life = Integer.parseInt(playerTemp[3]);
            if (life == 0) {                                                //player is dead
                player[i - 1].setShot(false);
            } else {
                player[i - 1].setShot(true);
            }

            player[i - 1].setHealth(Integer.parseInt(playerTemp[4]));
            
            if(player[i - 1].getHealth()==0){
                player[i - 1].setDead(true);
                
                if (!player[i - 1].hasCreatedCoinPileAfterDeath()) {
                    player[i - 1].setCreatedCoinPileAfterDeath(true);
                    
                    coinMap[player[i - 1].getY()][player[i - 1].getY()].setLife(10000000);
                    coinMap[player[i - 1].getY()][player[i - 1].getY()].setValue(player[i - 1].getCoins());
                    coin.add(coinMap[player[i - 1].getY()][player[i - 1].getY()]);
                }
            }
            
            player[i - 1].setCoins(Integer.parseInt(playerTemp[5]));
            player[i - 1].setPoints(Integer.parseInt(playerTemp[6]));
            if(!player[i - 1].isDead()){
                player[i - 1].cleanMap();
            }
            

            if (i - 1 == myNumber) {
                myPlayer = new MyPlayer(i - 1);
                myPlayer.setDirection(Integer.parseInt(playerTemp[2]));
                myPlayer.setX(Integer.parseInt(temp1[0]));
                myPlayer.setY(Integer.parseInt(temp1[1]));
                myPlayer.setHealth(Integer.parseInt(playerTemp[4]));
                myPlayer.setCoins(Integer.parseInt(playerTemp[5]));
                myPlayer.setPoints(Integer.parseInt(playerTemp[6]));
            }
        }

        temp = temp[availablePlayers + 1].split(";");
        for (int i = 0; i < temp.length; i++) {
//            System.out.println(temp[i]);
            String brick[] = temp[i].split(",");
            int x = Integer.parseInt(brick[0]);
            int y = Integer.parseInt(brick[1]);
            int d = Integer.parseInt(brick[2].charAt(0) + "");

            damage[y][x] = d;

            if (damage[y][x] == 4) {
                map[y][x] = 'E';
            }
        }

        client.movePlayer(myPlayer.nextMove());
        myPlayer.printlocation();

//        for (int i = 1; i < availablePlayers + 1; i++) {
//            System.out.println("Player " + i + ":" + player[i - 1].getX() + "," + player[i - 1].getY() + " " + player[i - 1].getDirection() + " " + player[i - 1].getHealth() + " " + player[i - 1].getCoins() + " " + player[i - 1].getPoints());
//        }

//        System.out.println("Map>");
//        for (int i = 0; i < gridLength; i++) {
//            for (int j = 0; j < gridLength; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        mapObj.printMap();
    }

    public void coinInit(String input) {
//        System.out.println("Coin>" +input);
        input = input.substring(0, input.length() - 1);
        String temp[] = input.split(":");
        String cordinates[] = temp[1].split(",");
        int x = Integer.parseInt(cordinates[0]);
        int y = Integer.parseInt(cordinates[1]);
        System.out.println("Coin Points>" + x + "," + y);

//        map[y][x] = 'C';

        int coinLife = Integer.parseInt(temp[2]);
        coinMap[y][x].setLife(coinLife);
        coinMap[y][x].setValue(Integer.parseInt(temp[3]));
        coin.add(coinMap[y][x]);

        ObjectDestroyer od = new ObjectDestroyer(coinLife, coinMap[y][x]);
    }

    public void lifeInit(String input) {
//        System.out.println("Coin>" +input);
        input = input.substring(0, input.length() - 1);
        String temp[] = input.split(":");
        String cordinates[] = temp[1].split(",");
        int x = Integer.parseInt(cordinates[0]);
        int y = Integer.parseInt(cordinates[1]);
        System.out.println("Life Points>" + x + "," + y);

//        map[y][x] = 'L';

        int lifeLife = Integer.parseInt(temp[2]);
        lifeMap[y][x].setLife(lifeLife);
        life.add(lifeMap[y][x]);

        ObjectDestroyer od = new ObjectDestroyer(lifeLife, lifeMap[y][x]);
    }
}
