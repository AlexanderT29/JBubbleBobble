package model.nemici;

import model.Nemico;

public class Zenchan extends Nemico {


    public Zenchan(int x, int y, int velocitax, int velocitay, int pv, int altezza, int larghezza, int punteggio) {
        super(x, y, velocitax, velocitay, pv, altezza, larghezza, 100);
        movimento = new MovimentoZenchan(this);
    }



}
