package model.nemici;

import controller.PlayerCollision;
import model.Direzione;
import model.Model;
import model.Nemico;
import java.util.Random;
import java.util.ArrayList;


public class MovimentoMonsta implements MovimentoNemici {
    private Direzione direzione;
    private Monsta nemico;
    private ArrayList<Direzione> direzioni = new ArrayList<Direzione>();
    private Random rand = new Random();
    private int cambio;


    public MovimentoMonsta(Monsta nemico) {
        this.nemico = nemico;
        direzioni.add(Direzione.DESTRA);
        direzioni.add(Direzione.SINISTRA);
        direzioni.add(Direzione.SOPRA);
        direzioni.add(Direzione.SOTTO);
    }

    @Override
    public void updateDirezione() {
        boolean collisioneNemico = PlayerCollision.checkCollisione(nemico, nemico.getDirezione(), Model.getInstance().getLivello());
        boolean secondaCollisione = PlayerCollision.checkCollisione(nemico, nemico.getDirezione2(), Model.getInstance().getLivello());
        if (secondaCollisione || collisioneNemico) {
            if (nemico.getDirezione() == Direzione.DESTRA && nemico.getDirezione2() == Direzione.SOPRA) {
                nemico.setDirezione(Direzione.DESTRA);
                nemico.setDirezione2(Direzione.SOTTO);
            } else if (nemico.getDirezione() == Direzione.DESTRA && nemico.getDirezione2() == Direzione.SOTTO) {
                nemico.setDirezione(Direzione.SINISTRA);
                nemico.setDirezione2(Direzione.SOTTO);
            } else if (nemico.getDirezione() == Direzione.SINISTRA && nemico.getDirezione2() == Direzione.SOTTO) {
                nemico.setDirezione(Direzione.SINISTRA);
                nemico.setDirezione2(Direzione.SOPRA);
            } else if (nemico.getDirezione() == Direzione.SINISTRA && nemico.getDirezione2() == Direzione.SOPRA) {
                nemico.setDirezione(Direzione.DESTRA);
                nemico.setDirezione2(Direzione.SOPRA);
            }
        } else {
                if (nemico.getDirezione() == Direzione.DESTRA && nemico.getDirezione2() == Direzione.SOPRA) {
                    nemico.addX(nemico.getVelocitaX());
                    nemico.addY(-(nemico.getVelocitay()));
                } else if (nemico.getDirezione() == Direzione.DESTRA && nemico.getDirezione2() == Direzione.SOTTO) {
                    nemico.addX(nemico.getVelocitaX());
                    nemico.addY(nemico.getVelocitay());
                } else if (nemico.getDirezione() == Direzione.SINISTRA && nemico.getDirezione2() == Direzione.SOTTO) {
                    nemico.addX(-(nemico.getVelocitaX()));
                    nemico.addY(nemico.getVelocitay());
                } else if (nemico.getDirezione() == Direzione.SINISTRA && nemico.getDirezione2() == Direzione.SOPRA) {
                    nemico.addX(-(nemico.getVelocitaX()));
                    nemico.addY(-(nemico.getVelocitay()));
                }
        }
    }


}
