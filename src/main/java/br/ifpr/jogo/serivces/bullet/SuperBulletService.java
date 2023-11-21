package br.ifpr.jogo.serivces.bullet;


import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;

import javax.swing.*;


public class SuperBulletService {
    private static final int ALTURA_TIRO = 30;
    private SuperBullet superBullet;
    public SuperBulletService(SuperBullet superBullet){
        this.setSuperBullet(superBullet);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/SuperTiro.png"));
        superBullet.setBaseSprite(loading.getImage());
        superBullet.setImageHeight(ALTURA_TIRO);
        superBullet.setImageWidth(ALTURA_TIRO);
    }

    public void update() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - superBullet.getTempoInicial();

        if (tempoDecorrido >= 5000) {
            superBullet.setVisible(false);
        } else {
            double angle = (tempoDecorrido / 1000.0) * Math.PI * 2;
            int radius = 100;

            int posX = (int) (Math.cos(angle) * radius) + superBullet.getPlayer().getXPosition();
            int posY = (int) (Math.sin(angle) * radius) + superBullet.getPlayer().getYPosition();

            superBullet.setXPosition(posX);
            superBullet.setYPosition(posY);
        }
    }

    public boolean checkVisibility() {
        boolean visivel = superBullet.isVisible();
        if (superBullet.getXPosition() > 1600 || superBullet.getXPosition() < 0 || superBullet.getYPosition() < 0
                || superBullet.getYPosition() > 960) {
            visivel = false;
        }
        return visivel;
    }

    public SuperBullet getSuperBullet() {
        return superBullet;
    }

    public void setSuperBullet(SuperBullet superBullet) {
        this.superBullet = superBullet;
    }
}