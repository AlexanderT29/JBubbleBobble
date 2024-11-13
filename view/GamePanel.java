package view;

import model.*;
import model.livello.Tile;
import model.nemici.Invader;
import model.nemici.Zenchan;
import model.powerups.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GamePanel extends JPanel implements Observer {

    private SpriteManagerPersonaggio smp;
    private SpriteManagerProiettile smpr;
    private Collection<SpriteManagerNemico> smn;
    private Collection<SpriteManagerPowerUp> smpu;
    private static int counter;
    public GamePanel(){
        this.setPreferredSize(new Dimension(1200,672));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.smp = new SpriteManagerPersonaggio();
        this.smpr = new SpriteManagerProiettile();
        this.smn = new ArrayList<SpriteManagerNemico>();
        this.smpu = new ArrayList<SpriteManagerPowerUp>();
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        this.disegnaLivello(g2);
        this.drawProiettile(g2);
        this.disegnaNemico(g2);
        this.disegnaPowerUp(g2);
        this.drawSquare(g2);

        //System.out.println("sto disegnando");
    }
    //Disegna un quadrato usando un oggetto Graphics2D
    private void drawSquare(Graphics2D g){
        g.drawImage(smp.getSprite().getImage(), Personaggio.getInstance().getX(), Personaggio.getInstance().getY(), 48,48, null);
    }


    private void drawProiettile(Graphics2D g){
        for (Iterator<Proiettile> it = smpr.getIterator(); it.hasNext(); ) {
            Proiettile p = it.next();
            if (Personaggio.getInstance().getIncensiere()) {
                if (p.getStato()) {
                    g.drawImage(Sprite.PROIETTILE2.getImage(), p.getX(), p.getY() + 10, 24, 24, null);
                } else {
                    g.drawImage(Sprite.PROIETTILEFERMO2.getImage(), p.getX(), p.getY(), 48, 48, null);
                }
            }
            else {
                if (p.getStato()) {
                    g.drawImage(Sprite.PROIETTILE.getImage(), p.getX(), p.getY() + 10, 24, 24, null);
                } else {
                    g.drawImage(Sprite.PROIETTILEFERMO.getImage(), p.getX(), p.getY(), 48, 48, null);
                }
            }
            }
    }

    public void disegnaLivello(Graphics2D g){
        if (Model.getInstance().getLivello() == null) return;
        Tile[][] mappa = Model.getInstance().getLivello().getMappa();
        for (int i = 0; i < 28; i++){
            for (int j = 0; j < 25; j++){
                g.drawImage(mappa[i][j].getTile().getImage(), j*48, i*24, 48, 24, null);
            }
        }
    }

    public void disegnaNemico(Graphics2D g){
        for (SpriteManagerNemico spriteManagerNemico : smn){
            Nemico n = spriteManagerNemico.getNemico();
            g.drawImage(spriteManagerNemico.getSpriteNemico().getImage(), n.getX(), n.getY(), 48, 48, null);
        }
    }

    public void disegnaPowerUp(Graphics2D g){
        for (SpriteManagerPowerUp spriteManagerPowerUp : smpu){
            PowerUp up = spriteManagerPowerUp.getPowerUp();
            g.drawImage(spriteManagerPowerUp.getSpritePowerUp().getImage(), up.getX(), up.getY(), 48, 48, null);
        }
    }







    @Override
    public void update(Object o) {
        if (o instanceof Proiettile p){
            if (smpr.checkInLista(p)){
                smpr.removeProiettile(p);
            } else {
                smpr.addProiettile(p);
            }
        }
        if (o instanceof Nemico nem){
            Iterator<SpriteManagerNemico> it = smn.iterator();
            while(it.hasNext()) {
                SpriteManagerNemico sm = it.next();
                if (sm.getNemico().equals(nem)) {
                    it.remove();
                    this.repaint();
                    return;
                }
            }
            smn.add(new SpriteManagerNemico(nem));
        }
        if (o instanceof PowerUp up){

            Iterator<SpriteManagerPowerUp> it = smpu.iterator();
            while(it.hasNext()) {
                SpriteManagerPowerUp sp = it.next();
                System.out.println(sp.getPowerUp().equals(up));
                if (sp.getPowerUp().equals(up)) {

                    it.remove();
                    this.repaint();

                    counter+=1;

                    return;
                }
            }
            smpu.add(new SpriteManagerPowerUp(up));

            counter+=1;


        }
        if (o == null){
            smpu.clear();
        }
        this.repaint();
    }


}
