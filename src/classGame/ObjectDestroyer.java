package classGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaRu
 */
public class ObjectDestroyer {

    int time;
    Map map;

    public ObjectDestroyer(int time, Coin c) {
        this.time = time;
        final Coin coin = c;
        map = Map.getInstance();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            // Variables as member variables instead of local variables in main()
            @Override
            public void run() {
                System.out.println("before remove " + map.getCoin().size());
                map.getCoin().remove(coin);
                coin.setLife(0);
                coin.setValue(0);
                System.out.println("removed coin " + map.getCoin().size());

            }
        }, time);
    }

    public ObjectDestroyer(int time, Life l) {
        this.time = time;
        final Life life = l;
        map = Map.getInstance();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            // Variables as member variables instead of local variables in main()
            @Override
            public void run() {
                map.getLife().remove(life);
                life.setLife(0);
            }
        }, time);
    }
}
