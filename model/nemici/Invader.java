package model.nemici;

import model.Nemico;

public class Invader extends Nemico {


    public Invader(int x, int y, int velocitax, int velocitay, int pv, int altezza, int larghezza, int punteggio) {
        super(x, y, velocitax, velocitay, pv, altezza, larghezza, 250);
        movimento = new MovimentoInvader(this);
    }
}
