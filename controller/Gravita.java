package controller;

import model.Entita;
import model.Livello;
import model.Model;
import model.livello.Tile;
import model.powerups.PowerUp;

import java.io.File;
import java.util.Scanner;

public class Gravita {
    protected boolean inAria;
    protected int gravita;
    protected Entita entita;
    protected PowerUp powerUp;
    public Gravita(Entita entita){
        inAria = false;
        gravita = 5;
        this.entita = entita;
    }
    public void fall(){
        int nuovaY;
        Tile blocco1, blocco2;
        nuovaY = (entita.getY() + 48 + 5)/24;
        blocco1 = Model.getInstance().getLivello().getMappa()[nuovaY][entita.getX()/48];
        blocco2 = Model.getInstance().getLivello().getMappa()[nuovaY][(entita.getX() + 48)/48];
        if ((blocco1.getChecker() || blocco2.getChecker())){
            inAria = false;
        } else {
            inAria = true;
            entita.addY( gravita);
            Model.getInstance().notifyObservers(this);
        }



    }

    public void setGravita(int gravita){
        this.gravita = gravita;
    }


}
