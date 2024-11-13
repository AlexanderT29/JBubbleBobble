package model.powerups;

import model.Personaggio;

public class Torta extends PowerUp {
    public Torta(int x, int y) {
        super(x, y, 0,0, 1, 48, 48);
    }

    @Override
    public void powerUpEffect() {
        Personaggio personaggio = Personaggio.getInstance();
        personaggio.addScore(1000);
        personaggio.setPV(personaggio.getPV()+2);
    }
}
