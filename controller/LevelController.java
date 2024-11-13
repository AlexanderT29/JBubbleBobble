package controller;

import model.Livello;
import model.Model;
import view.GameView;

import java.lang.reflect.Array;
import java.util.Collection;

public class LevelController {
    private GameView gameView;
    private Model model;
    private boolean haiVinto = false;
    private ListaLivelli[] listaLivelli = {ListaLivelli.LIVELLO1, ListaLivelli.LIVELLO2, ListaLivelli.LIVELLO3, ListaLivelli.LIVELLO4,
            ListaLivelli.LIVELLO5, ListaLivelli.LIVELLO6, ListaLivelli.LIVELLO7, ListaLivelli.LIVELLO8};
    private int numeroLivello = 0;


    public LevelController(GameView gameView) {
        this.gameView = gameView;
        this.model = Model.getInstance();
    }

    public void caricaLivelloCorrente(){
        Model.getInstance().setLivello(null);
        model.caricaLivello(listaLivelli[numeroLivello].getPathLivello(), listaLivelli[numeroLivello].getPathNemici());
        numeroLivello++;
    }

    public int getNumeroLivello() {
        return numeroLivello;
    }











}
