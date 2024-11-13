package model;

public class Proiettile  {
    protected int x;
    protected int y;
    protected int velocita;
    protected int altezza;
    protected int larghezza;
    protected int distanzaPercorsa;
    protected int distanzaMassima;
    protected Direzione verso;
    protected boolean inVolo;


    protected Proiettile(int x, int y, int velocita, int altezza, int larghezza, Direzione verso, boolean bool, int distanzaMassima){
        this.x = x;
        this.y = y;
        this.velocita = velocita;
        this.altezza = altezza;
        this.larghezza = larghezza;
        this.distanzaMassima = distanzaMassima;
        this.verso = verso;
        this.inVolo = bool;

    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x){this.x = x; }
    public void setY(int y){ this.y = y;}

    public void addXProiettile(int dist) {
        int a = getX();
        setX(a+dist);
        distanzaPercorsa += Math.abs(dist);
    }

    public void addYProiettile(int dist){
        int a = getY();
        setY(a+dist);
    }

    public int getVelocita(){return velocita;}

    public Direzione getVerso(){
        return verso;
    }

    public boolean checkDistanza(){
        return distanzaPercorsa >= distanzaMassima;
    }

    public boolean getStato(){
        return inVolo;
    }
    public void setStato(boolean b){
        this.inVolo = b;
    }
    public void setDimensioneMassima(int nuovaDimensione){
        this.distanzaMassima = nuovaDimensione;
    }


}
