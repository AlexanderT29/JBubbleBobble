package controller;

import model.*;
import model.powerups.PowerUp;
import view.GameView;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class GameController implements Runnable {


    private Thread gameThread;
    //Parametro per capire se devo aggiornare il loop
    private double delta = 0;
    private final double drawInterval = 1000000000 / 60;
    private long previousTime = System.nanoTime();
    private long currentTime;
    private GameView gameView;
    private PlayerController movement;
    private Model model;
    private NemiciController movementNemici;
    private static GameController instanceGameController;
    private LevelController levelController;
    private Collection<PowerUp> powerUps;
    private static Clip song;


    public void update(){
        movement.playerMovement();
        movement.getGravita().fall();
        movementNemici.aggiungiGravitaPowerUp();
        movement.muoviProiettili();
        movement.proiettiliSalita();
        movement.collisionePowerUp();
        movement.checkColpito();
        for (Gravita n : movementNemici.getGravita()) {
            n.fall();
        }
        movementNemici.muoviNemici();
        if (Personaggio.getInstance().getIncensiere()) {
            movementNemici.collisioneProiettileFermi();
        }
        movementNemici.collisioneProiettile();




    }



    private GameController(){
        instanceGameController = this;
        model = Model.getInstance();
        gameView = GameView.getInstance();
        levelController = new LevelController(gameView);
        model.add(gameView.getGamePanel());
        levelController.caricaLivelloCorrente();
        movement = new PlayerController(gameView);
        gameView.addKeyListener(movement);
        Personaggio.getInstance();
        movementNemici = new NemiciController();
        powerUps = Model.getInstance().getLivello().getPowerUps();
        model.add(gameView.getSegnapunti());
        startGameThread();
        song = AudioManager.getInstance().loop("src/model/ost/livello1.wav");

    }

    public static GameController getInstanceGameController(){
        if (instanceGameController == null){
            instanceGameController = new GameController();
        }

        return instanceGameController;
    }

    public LevelController getLevelController(){
        return levelController;
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public PlayerController getMovement() {
        return movement;
    }
    public void reset(){
        movementNemici = new NemiciController();
        powerUps.clear();
        Model.getInstance().notifyObservers(null);
    }

    public void close(){
        Utente.getInstance().addPartitaGiocata();
        gameThread = null;
        model.reset();
        gameView.reset();
        Personaggio.getInstance().resetPersonaggio();
        instanceGameController = null;

    }

    public GameView getGameView() {
        return gameView;
    }

    @Override
    public void run() {
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;
            if (delta >= 1) {
                update();
                delta--;
            }
        }
    }






}
