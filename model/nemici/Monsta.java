package model.nemici;

import model.Direzione;
import model.Nemico;

public class Monsta extends Nemico{
    private Direzione direzione2 = Direzione.SOTTO;
    public Monsta(int x, int y, int velocitax, int velocitay, int pv, int altezza, int larghezza, int punteggio) {
        super(x, y, 3, 3, pv, altezza, larghezza, 500);
        movimento = new MovimentoMonsta(this);
    }

    public Direzione getDirezione2() {
        return direzione2;
    }

    public void setDirezione2(Direzione direzione2) {
        this.direzione2 = direzione2;
    }
}
