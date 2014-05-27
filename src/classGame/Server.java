package classGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import classSlick.SimpleGame;

public class Server {

    ServerSocket s = null;
    Socket conn = null;
    int port = 7000;
    BufferedReader in = null;
    String message = null;
    GameInit gameInit;
    SimpleGame simpleGame;
//    public static void main(String[] args) throws IOException {
//        Server myServer = new Server();
//        myServer.serverAct();
//    }
    private static Server instance = null;

    protected Server() {
        // Exists only to defeat instantiation.
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void getGamePlay(GameInit gamePlay) {
        this.gameInit = gamePlay;
    }

    public void setGame(SimpleGame sg) {
        simpleGame = sg;
    }

    public void serverAct() {

        try {
            s = new ServerSocket(port);

            while (true) {
                conn = s.accept();
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                message = (String) in.readLine();
                System.out.println("Client> " + message);



                if ((message.charAt(1) + "").equals(":")) {
                    
                    this.simpleGame.stringProcessor(message);
                    
                    if ((message.charAt(0) + "").equals("I")) {
                        gameInit.initMap(message);
                    }
                    if ((message.charAt(0) + "").equals("S")) {
                        gameInit.initPlayers(message);
//                        Client.getInstance().movePlayer();
                    }
                    if ((message.charAt(0) + "").equals("G")) {
                        gameInit.globalUpdate(message);
                    }
                    if ((message.charAt(0) + "").equals("C")) {
                        gameInit.coinInit(message);
                    }
                    if ((message.charAt(0) + "").equals("L")) {
                        gameInit.lifeInit(message);
                    }
                }

            }



        } catch (IOException e) {
        }

        try {
            in.close();
            s.close();
        } catch (IOException ioException) {
            System.err.println("Unable to close. IOexception");
        }
    }
}
