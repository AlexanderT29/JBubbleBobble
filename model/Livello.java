package model;

import model.livello.Tile;
import model.nemici.Invader;
import model.nemici.Monsta;
import model.nemici.Zenchan;
import model.powerups.PowerUp;
import view.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Livello {

    private Tile[][] mappa = new Tile[28][25];
    private static HashMap<Integer, Tile> mappatura;
    private Collection<Nemico> listaNemici = new ArrayList<Nemico>();
    private Collection<PowerUp> powerUps = new ArrayList<PowerUp>();

    public Livello(String pathLivello, String pathNemici){

        inizializzaMappa();
        letturaLivello(pathLivello);
        creaNemici(pathNemici);
    }

    public void inizializzaMappa(){
        if (mappatura != null) return;
        mappatura = new HashMap<Integer, Tile>();
        mappatura.put(1,new Tile(true, Sprite.MATTONEROSSO));
        mappatura.put(0, new Tile(false, Sprite.BLOCCOTRASPARENTE));
    }

    public void letturaLivello(String path) {
        try {
            File file = new File(path);
            Scanner scannerfile = new Scanner(file);
            for(int i = 0; i < 28; i++){
                String[] linea = scannerfile.nextLine().split(" ");
                for (int j = 0; j < 25; j++){
                    int value = Integer.parseInt(linea[j]);
                    mappa[i][j] = mappatura.get(value);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Il file non è stato trovato");
        }


    }

    public Tile[][] getMappa(){
        return mappa;
    }

    public void creaNemici(String path){
        try{
            File file = new File(path);
            Scanner scannerNemici = new Scanner(file);
            while (scannerNemici.hasNext()){
                String[] linea = scannerNemici.nextLine().split(" ");
                switch (Integer.parseInt(linea[0])){
                    case 1 -> {
                        Nemico n = new Zenchan(Integer.parseInt(linea[1]), Integer.parseInt(linea[2]), 3, 0, 1, 48, 48, 100);
                        listaNemici.add(n);
                        Model.getInstance().notifyObservers(n);

                    }
                    case 2 ->{
                        Nemico n = new Invader(Integer.parseInt(linea[1]), Integer.parseInt(linea[2]), 4, 4, 1, 48, 48, 250);
                        listaNemici.add(n);
                        Model.getInstance().notifyObservers(n);
                    }
                    case 3 ->{
                        Nemico n = new Monsta(Integer.parseInt(linea[1]), Integer.parseInt(linea[2]),5, 5, 1, 48, 48, 500);
                        listaNemici.add(n);
                        Model.getInstance().notifyObservers(n);
                    }
                }

            }
        } catch (FileNotFoundException e){
            System.out.println("Il file non è stato trovato");
        }
    }

    public Collection<Nemico> getListaNemici(){
        return listaNemici;
    }

    public void aggiungiPowerUp(PowerUp powerUp){
        powerUps.add(powerUp);

    }

    public void rimuoviPowerUp(PowerUp powerUp){
        powerUps.remove(powerUp);

    }

    public Collection<PowerUp> getPowerUps(){
        return powerUps;
    }

    public void resetPowerUps(){
        powerUps.clear();
    }






}
