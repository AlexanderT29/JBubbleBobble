package model.nemici;

import controller.PlayerCollision;
import model.Direzione;
import model.Model;
import model.Nemico;

import java.util.ArrayList;

public class MovimentoZenchan implements MovimentoNemici {
    private Direzione direzione;
    private Nemico nemico;
    private ArrayList<Direzione> direzioni = new ArrayList<Direzione>();

    public MovimentoZenchan(Nemico nemico){
        this.nemico = nemico;
        direzioni.add(Direzione.SINISTRA);
        direzioni.add(Direzione.DESTRA);
    }

    public void updateDirezione(){
        boolean collisioneNemico = PlayerCollision.checkCollisione(nemico, nemico.getDirezione(), Model.getInstance().getLivello());
        if (collisioneNemico){
            if(nemico.getDirezione() == Direzione.SINISTRA){
                nemico.setDirezione(Direzione.DESTRA);
            }else {
                nemico.setDirezione(Direzione.SINISTRA);
            }
        } else {
            if (nemico.getDirezione() == Direzione.DESTRA){
                nemico.addX(nemico.getVelocitaX());
            } else {
                nemico.addX(-(nemico.getVelocitaX()));
            }
        }
    }


}
