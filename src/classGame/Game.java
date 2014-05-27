package classGame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import classSlick.SimpleGame;

public class Game {

    public Game() throws IOException, SlickException {
        SimpleGame sg = new SimpleGame();
        final AppGameContainer app = new AppGameContainer(sg);

        GameInit myGamePlay = new GameInit();
        Client myClient = Client.getInstance();
        Server myServer = Server.getInstance();
        
        myServer.setGame(sg);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.setDisplayMode(1056, 640, false);
                    app.setTargetFrameRate(26);
                    app.setShowFPS(false);
                    Display.setLocation(-1, -1);
                    app.start();
                } catch (SlickException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        th.start();

        myServer.getGamePlay(myGamePlay);
        myClient.clientAct();
        myServer.serverAct();

    }
    
    public static void main(String args[]) throws IOException, SlickException{
        Game game=new Game();
    }
}
