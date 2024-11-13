package model.powerups;

import model.Model;
import model.Personaggio;

public class CaramellaRosa extends PowerUp {
    public CaramellaRosa(int x, int y){
        super(x, y, 0, 0, 1, 48, 48);
    }

    @Override
    public void powerUpEffect(){
        Personaggio personaggio = Personaggio.getInstance();
        personaggio.aumentaDistanza(440);
    }

}
