package classGame;

import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    Socket socket;
    PrintWriter s_out = null;
    String address = "127.0.0.1";
    int port = 6000;
    Timer timer = new Timer();
    private static Client instance = null;

    protected Client() {
        // Exists only to defeat instantiation.
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    //BufferedReader s_in = null;
//    public static void main(String[] args)throws IOException{
//        Client myClient = new Client();
//        myClient.clientAct();
//        
//    }
    public void clientAct() throws IOException {
        Client.instance.joinGame();
    }

    private void joinGame() throws IOException {
        try {
            socket = new Socket(address, port);
            s_out = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.exit(1);
        }

        String message = "JOIN#";
        s_out.print(message);
        s_out.close();
    }

    public void movePlayer(String m) throws IOException {

        Timer timer = new Timer();
        final String message = m;
        System.out.println("Me> "+message);
        timer.schedule(new TimerTask() {
            // Variables as member variables instead of local variables in main()
            @Override
            public void run() {

                try {
                    socket = new Socket(address, port);
                    s_out = new PrintWriter(socket.getOutputStream(), true);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }

//                String message = message;
                
                s_out.print(message);
                s_out.close();
            }
        }, 700);


    }
}
