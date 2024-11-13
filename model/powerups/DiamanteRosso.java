package model.powerups;

import model.Personaggio;

public class DiamanteRosso extends PowerUp{


    public DiamanteRosso(int x, int y) {
        super(x, y, 0,0, 1, 48, 48);
    }

    @Override
    public void powerUpEffect() {

        Personaggio personaggio = Personaggio.getInstance();
        personaggio.addScore(1000);
    }
}
