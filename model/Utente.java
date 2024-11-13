package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class Utente {
    private static String nickname;
    private int partiteGiocate;
    private int partitePerse;
    private int partiteVinte;
    private int score;
    private static String imagePath;
    private static Utente instance;

    public static Utente getInstance() {
        if (instance == null) {
            instance = new Utente();
        }
        return instance;
    }

    private Utente() {
        String path = "src/datiGiocatori/" + nickname + ".txt";
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                fw.write("0 0 0 null 0");
                this.partiteGiocate = 0;
                this.partitePerse = 0;
                this.partiteVinte = 0;
                this.score = 0;
                fw.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        } else {
            try {
                Iterator<String> it = Files.lines(Path.of(path)).iterator();
                while (it.hasNext()) {
                    String[] line = it.next().split(" ");
                    this.partiteGiocate = Integer.parseInt(line[0]);
                    this.partitePerse = Integer.parseInt(line[1]);
                    this.partiteVinte = Integer.parseInt(line[2]);
                    this.score = Integer.parseInt(line[4]);
                    imagePath = line[3];
                    if (imagePath.equals("null")) {
                        imagePath = null;
                    }
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }

    }
    public static String getImagePath(){
        return imagePath;
    }
    public static void setImagePath(String imagePath){
        Utente.imagePath = imagePath;
    }

    public static String getNickname(){
        return nickname;
    }
    public static void setNickname(String nickname){
        Utente.nickname = nickname;
    }
    public void addPartitaGiocata(){
        partiteGiocate++;
    }
    public void addPartitePerse(){
        partitePerse++;
    }
    public void addPartiteVinte(){
        partiteVinte++;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getPartiteGiocate(){
        return partiteGiocate;
    }

    public int getPartitePerse(){
        return partitePerse;
    }
    public int getPartiteVinte(){
        return partiteVinte;
    }
    public int getScore(){
        return score;
    }

}
