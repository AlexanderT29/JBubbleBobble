package view.menuGioco;

import model.Personaggio;
import model.Utente;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Evento implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        Utente.getInstance().setScore(Utente.getInstance().getScore() +Personaggio.getInstance().getScore() );
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void salvaDatiUtente(){
        Utente utente = Utente.getInstance();
        File file = new File ("src/datiGiocatori/" + Utente.getNickname().toLowerCase() + ".txt");
        try {
            String imagePath;
            FileWriter fileWriter = new FileWriter(file, false);
            if (Utente.getImagePath() == null) {
                imagePath = "null";
            } else imagePath = Utente.getImagePath();
            fileWriter.write( utente.getPartiteGiocate() + " " + utente.getPartiteVinte() + " " + utente.getPartitePerse() + " " + imagePath + " " + utente.getScore());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
