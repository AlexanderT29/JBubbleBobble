package controller;

import model.*;
import model.livello.Tile;
import model.powerups.PowerUp;
import view.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.invoke.SwitchPoint;
import java.util.*;

import view.GameView;
import view.menuGioco.Evento;
import view.menuGioco.MenuGioco;

import javax.swing.*;
import javax.swing.Timer;
import java.util.TimerTask;

public class PlayerController implements KeyListener {

    private GameView view;
    private GamePanel panel;
    private Personaggio player;
    private Livello livello;
    private boolean destra,sinistra, salto, sparo;
    //private boolean inAria;
    private Timer jumpTimer;
    private Timer sparoTimer;
    private final int Jump_Delay = 500;
    private final int Sparo_Delay = 200;
    private Collection<Proiettile> proiettili;
    private Gravita gravita;
    private Collection<PowerUp> powerUps;
    private Collection<Nemico> nemici;


    private Collection<Proiettile> proiettiliFermi;
    private Collection<Proiettile> proiettiliFermi2;
    public PlayerController(GameView view){

        player = Personaggio.getInstance();
        gravita = new Gravita(Personaggio.getInstance());
        this.view = view;
        this.panel = view.getGamePanel();
        livello = Model.getInstance().getLivello();
        //powerUps = Model.getInstance().getLivello().getPowerUps();
        jumpTimer = new Timer(Jump_Delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salto = false;
                jumpTimer.stop();
            }
        });


        sparoTimer = new Timer(Sparo_Delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personaggio.getInstance().setSparando(false);
                Model.getInstance().notifyObservers(this);
                sparoTimer.stop();

            }
        });
        sparoTimer.setRepeats(false);

        proiettili = new ArrayList<Proiettile>();
        proiettiliFermi = new ArrayList<Proiettile>();
        proiettiliFermi2 = new ArrayList<Proiettile>();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            sinistra = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            destra = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_UP && !gravita.inAria && !salto){
            salto = true;
            gravita.inAria = true;
            jumpTimer.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !Personaggio.getInstance().getSparando()){
            Personaggio.getInstance().setSparando(true);
            sparoTimer.start();
            if (!player.getIncensiere()){
                Proiettile p = Personaggio.getInstance().spara();
                proiettili.add(p);
                Model.getInstance().notifyObservers(p);
            }
            else {
                Proiettile p = Personaggio.getInstance().spara2();
                proiettili.add(p);
                Model.getInstance().notifyObservers(p);
            }


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            sinistra = false;}
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            destra = false;}
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            Personaggio.getInstance().setSparando(false);

        }
    }

    public void playerMovement(){

        if(!sinistra && !destra && !salto && !sparo){
            return;
        }
        else if(sinistra){
            Personaggio.getInstance().setDirezione(Direzione.SINISTRA);
            if (!PlayerCollision.checkCollisione(player, Direzione.SINISTRA, Model.getInstance().getLivello() )) {
                player.addX(-(player.getVelocita()));
            }
        }
        else if(destra){
            Personaggio.getInstance().setDirezione(Direzione.DESTRA);
            if (!PlayerCollision.checkCollisione(player, Direzione.DESTRA, Model.getInstance().getLivello() )) {
                player.addX(player.getVelocita());
            }
        }
        if (salto){
            player.addY(-(player.getVelocitay()));
            if (player.getY() <= 0)
                salto = false;
        }



        Model.getInstance().notifyObservers(this);
    }

    public Gravita getGravita(){
        return gravita;
    }



    public void muoviProiettili() {
        Iterator<Proiettile> iterator = proiettili.iterator();
        while (iterator.hasNext()) {
            Proiettile proiettile = iterator.next();
            switch (proiettile.getVerso()) {
                case SINISTRA -> {
                    proiettile.addXProiettile(proiettile.getVelocita() * -1);
                }
                case DESTRA -> {
                    proiettile.addXProiettile(proiettile.getVelocita());
                }
            }
            if (proiettile.checkDistanza()) {
                proiettile.setStato(false);
                iterator.remove();
                if (!player.getIncensiere()) {
                    proiettiliFermi.add(proiettile);
                } else {
                    proiettiliFermi2.add(proiettile);
                }
            }
            Model.getInstance().notifyObservers(this);

        }
    }


    public void proiettiliSalita(){
        Iterator<Proiettile> iterator = proiettiliFermi.iterator();
        while (iterator.hasNext()){
            Proiettile proiettile = iterator.next();
            proiettile.addYProiettile(- 3);
            Model.getInstance().notifyObservers(this);
            if (proiettile.getY() - 5 <  0){
                iterator.remove();
                Model.getInstance().notifyObservers(proiettile);
            }
        }
    }


    public Collection<Proiettile> getProiettili() {
        return proiettili;
    }

    public Collection<Proiettile> getProiettiliFermi2() {
        return proiettiliFermi2;
    }


    public void collisionePowerUp(){
        powerUps = Model.getInstance().getLivello().getPowerUps();
        Iterator<PowerUp> it = powerUps.iterator();
        while (it.hasNext()){
            PowerUp powerUp = it.next();
            Rectangle r1 = new Rectangle(powerUp.getX(), powerUp.getY(), 48, 48);
            Rectangle r2 = new Rectangle(player.getX(), player.getY(), 48, 48);
            if (CollisionController.isOverlapping(r1, r2)) {
                player.aggiungiPowerUp(powerUp);
                powerUp.powerUpEffect();
                it.remove();
                Model.getInstance().getLivello().rimuoviPowerUp(powerUp);
                Model.getInstance().notifyObservers(powerUp);

            }
            break;
        }
    }

    public void addPowerUp(PowerUp up){
        powerUps.add(up);
    }

    public void checkColpito(){
        nemici = Model.getInstance().getLivello().getListaNemici();
        nemici.stream().forEach(n ->{
            Rectangle r1 = new Rectangle(n.getX(), n.getY(), 48, 48);
            Rectangle r2 = new Rectangle(player.getX(), player.getY(), 48, 48);
            if (CollisionController.isOverlapping(r1, r2) && !player.getInvincibile()) {
                player.infliggiDanno();
                if (player.getPV() == 0){

                    JOptionPane.showMessageDialog(GameController.getInstanceGameController().getGameView().getGamePanel(), "GAME OVER","fine partita", JOptionPane.INFORMATION_MESSAGE);
                    Utente.getInstance().addPartitePerse();
                    Utente.getInstance().setScore(Utente.getInstance().getScore() + Personaggio.getInstance().getScore());
                    Evento.salvaDatiUtente();
                    GameView.getInstance().chiudiFinestra();
                    GameController.getInstanceGameController().close();



                    return;
                }

                player.setInvincibile(true);
                System.out.println("Sei stato colpito e hai ancora " + player.getPV() +" PV");
                Timer timer = new Timer(1000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        player.setInvincibile(false);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
    }

}
