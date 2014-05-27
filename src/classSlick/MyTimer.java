package classSlick;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyTimer extends TimerTask {

    private int timerVal;

    public MyTimer() {
        timerVal = 0;
    }

    @Override
    public void run() {
        try {
            this.runTimer();
        } catch (InterruptedException ex) {
            Logger.getLogger(MyTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runTimer() throws InterruptedException {
        while (true) {
            this.timerVal += 1;
            Thread.sleep(1000);
        }
    }

    public int getTimerVal() {
        return timerVal;
    }
}
