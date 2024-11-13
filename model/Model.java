package model;

import java.awt.Image;
import java.awt.Graphics;

public class Model extends Observable {

    private Personaggio player;
    private static Model instance;
    private Livello livello;
    private static String pathLivello;
    private static String pathNemici;


    private Model(){
        instance = this;

    }

    public static Model getInstance(){
        if (instance == null){
            instance = new Model();
        }

        return instance;
    }

    public void caricaLivello(String pathLivello, String pathNemici){
        livello = new Livello(pathLivello, pathNemici);
    }



    public Livello getLivello(){
        return livello;
    }

    public void setLivello(Livello livello){
        this.livello = livello;
    }
    public static void setNemici(String pathNemici){
        instance.pathNemici = pathNemici;
    }
    public static void setPathLivello(String pathLivello){
        instance.pathLivello = pathLivello;
    }

    public static String getPathLivello(){
        return pathLivello;
    }
    public static String getPathNemici(){
        return pathNemici;
    }

    public void reset(){
        instance = null;
    }



}
