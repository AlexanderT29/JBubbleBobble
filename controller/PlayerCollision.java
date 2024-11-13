package controller;

import model.Direzione;
import model.Entita;
import model.Livello;
import model.livello.Tile;

import java.awt.*;

public class PlayerCollision {
    private static boolean isOverlapping(Rectangle r1, Rectangle r2) {
        Point r1BottomRightPoint = new Point((int) r1.getX() + (int) r1.getWidth(), (int) r1.getY() + (int) r1.getHeight());
        Point r2BottomRightPoint = new Point((int) r2.getX() + (int) r2.getWidth(), (int) r2.getY() + (int) r2.getHeight());
        return (int) r1.getX() < r2BottomRightPoint.getX() && r1BottomRightPoint.getX() > (int) r2.getX() && (int) r1.getY() < r2BottomRightPoint.getY() && r1BottomRightPoint.getY() > (int) r2.getY();
    }

    public static boolean checkCollisione(Entita entita, Direzione direzione, Livello livello){
        boolean collisione = false;
        int puntoInizialeX = entita.getX();
        int puntoLunghezza = entita.getX() + 48;
        int puntoInizialeY = entita.getY();
        int puntoAltezza = entita.getY() + 48;
        int nuovoCheckDestra;
        int nuovoCheckSinistra;
        int nuovoCheckSopra;
        int nuovoCheckSotto;
        Tile blocco1, blocco2;
        switch(direzione){
            case DESTRA -> {
                nuovoCheckDestra = (puntoLunghezza + entita.getVelocitaX()) / 48;
                blocco1 = livello.getMappa()[puntoInizialeY/ 24][nuovoCheckDestra];
                blocco2 = livello.getMappa()[puntoInizialeY/ 24][nuovoCheckDestra];
                if (blocco1.getChecker() || blocco2.getChecker()) {
                    collisione = true;
                }

            }
            case SINISTRA -> {
                nuovoCheckSinistra = (puntoInizialeX - entita.getVelocitaX()) / 48;
                blocco1 = livello.getMappa()[puntoInizialeY/ 24][nuovoCheckSinistra];
                blocco2 = livello.getMappa()[puntoInizialeY/ 24][nuovoCheckSinistra];
                if (blocco1.getChecker() || blocco2.getChecker()) {
                    collisione = true;
                }

            }
            case SOPRA -> {
                nuovoCheckSopra = (puntoInizialeY - entita.getVelocitay()) / 24;
                blocco1 = livello.getMappa()[nuovoCheckSopra][puntoInizialeX / 48];
                blocco2 = livello.getMappa()[nuovoCheckSopra][puntoInizialeX / 48];
                if (blocco1.getChecker() || blocco2.getChecker()) {
                    collisione = true;
                }
            }
            case SOTTO -> {
                nuovoCheckSotto = (puntoInizialeY + 48 + entita.getVelocitay()) /24;
                blocco1 = livello.getMappa()[nuovoCheckSotto][puntoInizialeX / 48];
                blocco2 = livello.getMappa()[nuovoCheckSotto][puntoInizialeX / 48];
                if (blocco1.getChecker() || blocco2.getChecker()) {
                    collisione = true;
                }
            }

        }
        return collisione;
    }

}
