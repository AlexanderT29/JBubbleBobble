package model.powerups;

import model.Personaggio;

public class CaramellaVerde extends PowerUp {
    public CaramellaVerde(int x, int y) {
        super(x, y, 0, 0, 1, 48, 48);
    }
    @Override
    public void powerUpEffect() {
        Personaggio personaggio = Personaggio.getInstance();

    }
}
