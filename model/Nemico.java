package model;

import model.nemici.MovimentoNemici;

public  abstract class Nemico extends Entita {
    protected MovimentoNemici movimento;
    protected int punteggio;

    public Nemico(int x, int y, int velocitax, int velocitay, int pv, int altezza, int larghezza, int punteggio) {
        super(x, y, velocitax, velocitay, pv, altezza, larghezza);
        this.punteggio = punteggio;
    }

    public MovimentoNemici getMovimentoNemico(){
        return movimento;
    }

    public int getPunteggio(){
        return punteggio;
    }





}
