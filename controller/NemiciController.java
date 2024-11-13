package controller;

import model.*;
import model.nemici.Invader;
import model.nemici.Monsta;
import model.nemici.Zenchan;
import model.powerups.*;
import view.GameView;
import view.menuGioco.Evento;
import view.menuGioco.MenuGioco;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class NemiciController {

    private Collection<Nemico> nemici;
    private Collection<Gravita> listaGravita;
    private Collection<PowerUp> listaPowerUp;




    public NemiciController(){
        listaGravita = new ArrayList<Gravita>();
        nemici = Model.getInstance().getLivello().getListaNemici();
        for (Nemico nemico : nemici){
            if (nemico instanceof Invader || nemico instanceof Monsta){
                continue;
            }
            listaGravita.add(new Gravita(nemico));
        }
    }

    public void muoviNemici() {
        for (Nemico nemico : nemici){
            nemico.getMovimentoNemico().updateDirezione();
            Model.getInstance().notifyObservers(this);
        }
    }


    public Collection<Gravita> getGravita(){
        return listaGravita;
    }

    public Collection<Nemico> getNemici() {
        return nemici;
    }

    public void aggiungiGravitaPowerUp(){
        listaPowerUp = Model.getInstance().getLivello().getPowerUps();
        if (!listaPowerUp.isEmpty()){

            for (PowerUp up: listaPowerUp){
                listaGravita.add(new Gravita(up));
            }
        }
    }

    public void collisioneProiettile(){
        Random random = new Random();
        Iterator<Nemico> it = nemici.iterator();
        Collection<Proiettile> pr = GameController.getInstanceGameController().getMovement().getProiettili();
        while (it.hasNext()){
            Nemico nem = it.next();
            for (Proiettile proiettile : pr){
                Rectangle r1 = new Rectangle(proiettile.getX(), proiettile.getY(), 48, 48);
                Rectangle r2 = new Rectangle(nem.getX(), nem.getY(), 48, 48);
                if( CollisionController.isOverlapping(r1, r2)){
                    Personaggio.getInstance().addScore(nem.getPunteggio());
                    it.remove();
                    pr.remove(proiettile);
                    Model.getInstance().notifyObservers(nem);
                    Model.getInstance().notifyObservers(proiettile);
                    if (nemici.isEmpty()){
                        if(GameController.getInstanceGameController().getLevelController().getNumeroLivello() == 2){
                            JOptionPane.showMessageDialog(GameController.getInstanceGameController().getGameView().getGamePanel(), "HAI VINTO","fine partita", JOptionPane.INFORMATION_MESSAGE);
                            Utente.getInstance().addPartiteVinte();
                            Utente.getInstance().setScore(Utente.getInstance().getScore() + Personaggio.getInstance().getScore());
                            Evento.salvaDatiUtente();
                            GameView.getInstance().chiudiFinestra();
                            GameController.getInstanceGameController().close();

                            return;
                        }
                        timerReset();
                        //GameController.getInstanceGameController().getLevelController().caricaLivelloCorrente();
                        //GameController.getInstanceGameController().reset();
                    }

                    if (random.nextInt(100)<40){
                        generaPowerUp(nem.getX(), nem.getY());
                    }
                    break;
                }
            }
        }
    }
    public void collisioneProiettileFermi(){
        Random random = new Random();
        Iterator<Nemico> it = nemici.iterator();
        Collection<Proiettile> pr = GameController.getInstanceGameController().getMovement().getProiettiliFermi2();
        while(it.hasNext()){
            Nemico n = it.next();
            for (Proiettile p: pr){
                Rectangle r1 = new Rectangle(p.getX(), p.getY(), 48, 48);
                Rectangle r2 = new Rectangle(n.getX(), n.getY(), 48, 48);
                if( CollisionController.isOverlapping(r1, r2)){
                    Personaggio.getInstance().addScore(n.getPunteggio());
                    it.remove();
                    pr.remove(p);
                    Model.getInstance().notifyObservers(n);
                    Model.getInstance().notifyObservers(p);
                    if (nemici.isEmpty()){
                        System.out.println("eccomi if nemici empty");
                        if(GameController.getInstanceGameController().getLevelController().getNumeroLivello() == 2){
                            System.out.println("eccomi");
                            JOptionPane.showMessageDialog(GameController.getInstanceGameController().getGameView().getGamePanel(), "HAI VINTO!","fine partita", JOptionPane.INFORMATION_MESSAGE);
                            Utente.getInstance().addPartiteVinte();
                            Utente.getInstance().setScore(Utente.getInstance().getScore() + Personaggio.getInstance().getScore());
                            Evento.salvaDatiUtente();
                            GameView.getInstance().chiudiFinestra();
                            GameController.getInstanceGameController().close();
                            return;
                        }

                        timerReset();
                        //Personaggio.getInstance().resetPersonaggio();
                        //GameController.getInstanceGameController().getLevelController().caricaLivelloCorrente();
                        //GameController.getInstanceGameController().reset();
                    }

                    if (random.nextInt(100)<40){
                        generaPowerUp(n.getX(), n.getY());
                    }
                    break;
                }
            }
        }
    }

    private void generaPowerUp(int x, int y) {
        PowerUp power = randomPowerUp(x,y);

        Model.getInstance().getLivello().aggiungiPowerUp(power);
        Model.getInstance().notifyObservers(power);
    }

    private PowerUp randomPowerUp(int x, int y){
        Random rand = new Random();
        int scelta = rand.nextInt(10);
        return switch (scelta) {
            case 0 -> new CaramellaRosa(x, y);
            case 1 -> new CaramellaGialla(x, y);
            case 2 -> new CaramellaVerde(x, y);
            case 3 -> new BastoneMagico(x, y);
            case 4 -> new Incensiere(x, y);
            case 5 -> new DiamanteRosso(x, y);
            case 6 -> new DiamanteGiallo(x, y);
            case 7 -> new Diamante(x, y);
            case 8 -> new Torta(x, y);
            case 9 -> new Teschio(x, y);
            default -> new CaramellaRosa(x, y);
        };
    }

    public static void timerReset(){
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Personaggio.getInstance().resetPersonaggio();
                Model.getInstance().getLivello().resetPowerUps();
                GameController.getInstanceGameController().getLevelController().caricaLivelloCorrente();
                GameController.getInstanceGameController().reset();
                ((Timer) e.getSource()).stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
