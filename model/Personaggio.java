package model;


import model.powerups.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Personaggio extends Entita {
    private static Personaggio istanza;
    private boolean sparando;
    private Collection<PowerUp> potenziamenti;
    private boolean caramellaRosa = false;
    private boolean caramellaGialla = false;
    private boolean caramellaVerde = false;
    private boolean incensiere = false;
    private boolean morto;
    private boolean invincibile;
    private int score;




    private Personaggio(){
        super(49, 25, 5, 10,3,48,  48 );
        this.potenziamenti = new ArrayList<PowerUp>();
        this.invincibile = false;

    }

    public static Personaggio getInstance(){
        if (istanza == null){
            istanza = new Personaggio();
        }
        return istanza;
    }

    public void setSparando(boolean sparo){
        this.sparando = sparo;
    }

    public boolean getSparando(){
        return sparando;
    }

    public int getVelocita(){
        return velocitax;
    }

    public void setVelocita(int v){
        this.velocitax = v;
    }
    public int getVelocitaY(){ return velocitay;}
    public void setVelocitaY(int v){
        this.velocitay = v;
    }




    public Proiettile spara(){
        int distanza = 220;
        int velocita = 10;
        if (caramellaRosa){
            distanza *= 2;
        }
        if (caramellaVerde){
            velocita *= 2;
        }
        Proiettile p = new Proiettile(this.x , this.y, velocita, 48, 48, Personaggio.getInstance().getDirezione(), true, distanza );

        return p;
    }

    public Proiettile spara2(){
        int distanza = 220;
        int velocita = 10;
        if (caramellaRosa){
            distanza = 440;
        }
        if (caramellaVerde){
            velocita = 20;
        }
        Proiettile p = new Proiettile(this.x , this.y, velocita, 48, 48, Personaggio.getInstance().getDirezione(), true, distanza );
        return p;
    }

    public void aumentaDistanza(int incremento){
        Proiettile p = this.spara();
        p.setDimensioneMassima(incremento);

    }

    public void aggiungiPowerUp(PowerUp up){
        if (up instanceof CaramellaRosa){
            caramellaRosa = true;
        }
        if (up instanceof CaramellaGialla){
            caramellaGialla = true;
            setVelocita(10);
        }
        if (up instanceof CaramellaVerde){
            caramellaVerde = true;
        }
        if (up instanceof BastoneMagico){
            setVelocitaY(15);
        }
        if (up instanceof Incensiere){
            incensiere = true;
        }

    }

    public int getPV(){
        return pv;
    }
    public void setPV(int pv){
        this.pv = pv;
    }
    public void infliggiDanno(){
        setPV(getPV()-1);
        if (getPV() == 0){
            morto = true;
        }
    }

    public boolean getInvincibile(){
        return invincibile;
    }

    public void setInvincibile(boolean invincibile){
        this.invincibile = invincibile;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int score){
        this.score += score;
    }

    public boolean getCaramellaVerde(){
        return caramellaVerde;
    }

    public boolean getIncensiere(){
        return incensiere;
    }

    public void resetPersonaggio(){
        caramellaRosa = false;
        caramellaGialla = false;
        caramellaVerde = false;
        incensiere = false;
        setVelocita(10);

    }

    public void reset(){
        istanza = null;
    }



}
