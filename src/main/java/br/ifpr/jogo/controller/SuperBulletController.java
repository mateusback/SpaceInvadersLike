package br.ifpr.jogo.controller;


import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;

import javax.swing.*;



public class SuperBulletController extends GraphicElementController {
    private static final int ALTURA_TIRO = 30;
    private SuperBullet superBullet;
    public SuperBulletController(SuperBullet superBullet){
        this.setSuperBullet(superBullet);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/SuperTiro.png"));
        superBullet.setBaseSprite(loading.getImage());
        superBullet.setImageHeight(ALTURA_TIRO);
        superBullet.setImageWidth(ALTURA_TIRO);
    }

    @Override
    public void update() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - superBullet.getTempoInicial();
        if (tempoDecorrido >= 5000) {
            superBullet.setVisible(false);
        } else {
            double angulo = (tempoDecorrido / 1000.0) * Math.PI * 2;
            int raio = 100;

            int posX = (int) (Math.cos(angulo) * raio) + superBullet.getXPosition();
            int posY = (int) (Math.sin(angulo) * raio) + superBullet.getYPosition();

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