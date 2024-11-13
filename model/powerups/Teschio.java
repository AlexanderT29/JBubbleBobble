package model.powerups;

import model.Personaggio;
import java.util.Timer;
import java.util.TimerTask;

public class Teschio extends PowerUp{
    public Teschio(int x, int y) {
        super(x, y, 0,0, 1, 48, 48);
    }

    @Override
    public void powerUpEffect() {
        Personaggio personaggio = Personaggio.getInstance();
        personaggio.setInvincibile(true);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {public void run(){
            personaggio.setInvincibile(false);
            }
        };
        timer.schedule(timerTask, 8000);
    }
}
