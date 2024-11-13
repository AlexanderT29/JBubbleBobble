package model;

public abstract class Entita{
    // coordinata x dell'model.Entita
    protected int x;
    // coordinata y dell'model.Entita
    protected int y;
    //Velocità
    protected int velocitax;
    protected int velocitay;
    //punti vita
    protected int pv;
    protected int altezza;
    protected int larghezza;

    protected Direzione direzione = Direzione.DESTRA;



    public Entita(int x, int y, int velocitax, int velocitay, int pv, int altezza, int larghezza){
        this.x = x;
        this.y = y;
        this.velocitax = velocitax;
        this.velocitay = velocitay;
        this.pv = pv;
        this.altezza = altezza;
        this.larghezza = larghezza;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }



    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public void addX(int i) { x = x + i;}

    public void addY(int k) { y = y + k;}
    public int getVelocitaX(){
        return velocitax;
    }
    public void setVelocitaX(int velocitax){
        this.velocitax = velocitax;
    }
    public int getVelocitay() {
        return velocitay;
    }
    public void setVelocitaY(int velocita){
        this.velocitay = velocita;
    }

    public int getPv(){
        return pv;
    }
    public void setPv(int pv){
        this.pv = pv;
    }

    public void setDirezione(Direzione direzione){
        this.direzione = direzione;
    }
    public Direzione getDirezione(){ return direzione;}



}
