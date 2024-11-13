package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameView extends JFrame {

    private GamePanel gamePanel;
    private Segnapunti segnapunti;
    private GamePanel gameOver;
    public static GameView instance;

    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        } return instance;
    }

    private GameView(){
        this.setLayout(new BorderLayout());
        segnapunti = new Segnapunti();
        gamePanel = new GamePanel();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(true);
        this.add(segnapunti, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.SOUTH);
        this.pack();
        this.requestFocusInWindow();
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.requestFocusInWindow();
    }

    public static void main(String[] args){
        GameView game = new GameView();
    }

    public Segnapunti getSegnapunti() {
        return segnapunti;
    }



    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void reset(){
        instance = null;
    }

    public void chiudiFinestra(){
        instance.dispose();
    }
}
