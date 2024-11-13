package model.livello;

import view.Sprite;

public class Tile {
    private boolean checker;
    private Sprite tile;

    public Tile(boolean checker, Sprite tile){
        this.checker = checker;
        this.tile = tile;
    }

    public Sprite getTile() {
        return tile;
    }

    public boolean getChecker(){
        return checker;
    }


}
