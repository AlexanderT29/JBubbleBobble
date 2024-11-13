package model.powerups;

import model.Entita;

public abstract class PowerUp extends Entita {
    private boolean ottenibile = false;
    public PowerUp(int x, int y, int velx, int vely, int pv, int altezza, int larghezza) {
       super(x, y, 0,0, 1, 48, 48);

   }


    public void setOttenibile(boolean ottenibile) {
        this.ottenibile = ottenibile;
    }

    public abstract void powerUpEffect();
}
