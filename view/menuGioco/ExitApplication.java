package view.menuGioco;

import model.Personaggio;
import model.Utente;

public class ExitApplication extends Thread{

    @Override
    public void run() {
        if (Utente.getNickname() == null){
            return;
        }
        Evento.salvaDatiUtente();
    }
}
