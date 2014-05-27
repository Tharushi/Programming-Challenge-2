package classSlick;

import java.net.*;
import java.io.*;
import java.util.*;
import org.newdawn.slick.AppGameContainer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MyServer extends Thread {

    private ServerSocket serSocket;
    Socket serverSideSocket = new Socket();
    MyClient client = new MyClient();
    BufferedReader gameOutPut;
    String response = "";
    String turn;//Direction to turn 
    int listenPort = 7000;//Listen Port
    int currentDirection, move;//current direction and direction to move
    int pathCounter;//use to go through the path vector
    Vertex current;//holds the current vertex
    SimpleGame simpleGame;
    BFSImplement bfs;
    Vector<Vertex> pathVertices;
    int check;

    public MyServer(SimpleGame sg) {
        this.simpleGame = sg;
        bfs = new BFSImplement();
        pathVertices = new Vector<Vertex>();
        this.currentDirection = 0;
        current = bfs.grid[0][0];
        check = 0;
    }

    public void run() {
        StringTokenizer str1;
        try {
            serSocket = new ServerSocket(7000);//Thrugh listening port
            while (true) {
                serverSideSocket = serSocket.accept();
                gameOutPut = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
                response = gameOutPut.readLine();
                System.out.println(response);
                this.simpleGame.stringProcessor(response);

                //Set the game grid at the initializing response
                if ((response.charAt(0) + "").equals("I")) {
                    str1 = new StringTokenizer(response.substring(5, response.length() - 1), ":");

                    bfs.setGrid(str1.nextToken(), "B");//Brick
                    bfs.setGrid(str1.nextToken(), "S");//Stone
                    bfs.setGrid(str1.nextToken(), "W");//Water
                    bfs.setTree(0, 0, 12, 19, pathVertices);
                    pathCounter = pathVertices.size() - 1;
                }


                //go along the path

//                if ((response.charAt(0) + "").equals("G") && pathCounter >= 0) {
//                    if (check != 0) {
//                        turn = calculatedTurns(current, pathVertices.get(pathCounter));
//                        if (turn.equals("UP#")) {
//                            move = 0;
//                        } else if (turn.equals("DOWN#")) {
//                            move = 2;
//                        } else if (turn.equals("LEFT#")) {
//                            move = 3;
//                        } else if (turn.equals("RIGHT#")) {
//                            move = 1;
//                        }
//                        this.giveCommands(turn);
//
//                        if (currentDirection == move) {
//                            current = pathVertices.get(pathCounter);
//                            pathCounter = pathCounter - 1;
//                            currentDirection = move;
//
//                        } else {
//                            currentDirection = move;
//                        }
//                    }
//                    check++;
//                }
                serverSideSocket.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void giveCommands(String gameComnd) {
        System.out.println(gameComnd);
        client.clientConnection(gameComnd);
    }

    public String calculatedTurns(Vertex currentPos, Vertex nextPos) {
        int checkX;
        int checkY;

        checkX = nextPos.xCord - currentPos.xCord;
        checkY = nextPos.yCord - currentPos.yCord;

        if (checkX == 0 && checkY == -1) {
            return "UP#";
        } else if (checkX == 1 && checkY == 0) {
            return "RIGHT#";
        } else if (checkX == 0 && checkY == 1) {
            return "DOWN#";
        } else if (checkX == -1 && checkY == 0) {
            return "LEFT#";
        } else {
            return "Error";
        }
    }

//    public static void main(String[] args) throws SlickException {
//        SimpleGame sg = new SimpleGame();
//        AppGameContainer app = new AppGameContainer(sg);
//
//        MyServer srvr = new MyServer(sg);
//        MyClient mc = new MyClient();
//        mc.clientConnection("JOIN#");
//        srvr.start();
//
//
//        app.setDisplayMode(1056, 640, false);
//        app.start();
//
//    }
}
