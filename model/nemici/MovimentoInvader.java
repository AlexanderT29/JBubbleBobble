package model.nemici;

import controller.PlayerCollision;
import model.Direzione;
import model.Model;
import model.Nemico;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovimentoInvader implements MovimentoNemici{
    private Direzione direzione;
    private Nemico nemico;
    private ArrayList<Direzione> direzioni = new ArrayList<Direzione>();

    public MovimentoInvader(Nemico nemico){
        this.nemico = nemico;
        direzioni.add(Direzione.SINISTRA);
        direzioni.add(Direzione.DESTRA);
        direzioni.add(Direzione.SOTTO);
        direzioni.add(Direzione.SOPRA);
    }

    public void updateDirezione() {
        boolean collisioneNemico = PlayerCollision.checkCollisione(nemico, nemico.getDirezione(), Model.getInstance().getLivello());
        if(collisioneNemico){

            if(nemico.getDirezione() == Direzione.SINISTRA){
                nemico.setDirezione(Direzione.SOTTO);

            } else if (nemico.getDirezione() == Direzione.DESTRA){
                nemico.setDirezione(Direzione.SOPRA);

            } else if (nemico.getDirezione() == Direzione.SOTTO){
                nemico.setDirezione(Direzione.DESTRA);

            } else {
                nemico.setDirezione(Direzione.SINISTRA);

            }
        }
        else {
            switch (nemico.getDirezione()) {
                case SINISTRA: nemico.addX(-(nemico.getVelocitaX()));
                break;
                case DESTRA: nemico.addX((nemico.getVelocitaX()));
                break;
                case SOPRA: nemico.addY(-(nemico.getVelocitay()));
                break;
                case SOTTO: nemico.addY((nemico.getVelocitay()));
            }

        }

    }

}
