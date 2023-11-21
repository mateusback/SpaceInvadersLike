package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.bullet.Bullet;

import javax.swing.*;

import static br.ifpr.jogo.util.ScreenConstants.ALTURA_DA_JANELA;
import static br.ifpr.jogo.util.ScreenConstants.LARGURA_DA_JANELA;

public class BulletController extends GraphicElementController {
    private Bullet bullet;
    public static final int ALTURA_TIRO = 30;
    public static final int SPEED = 4;

    public BulletController(Bullet bullet){
        this.bullet = bullet;
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Tiro2.gif"));
        bullet.setBaseSprite(loading.getImage());
        bullet.setImageHeight(ALTURA_TIRO);
        bullet.setImageWidth(ALTURA_TIRO);
    }

    @Override
    public void update() {
        switch (bullet.getDirection()) {
            case "right" -> bullet.setXPosition(bullet.getXPosition() + SPEED);
            case "top" -> bullet.setYPosition(bullet.getYPosition() - SPEED);
            case "left" -> bullet.setXPosition(bullet.getXPosition() - SPEED);
            case "bottom" -> bullet.setYPosition(bullet.getYPosition() + SPEED);
        }
    }

    public boolean checkVisibility() {
        boolean visible;
        boolean cond1 = bullet.getXPosition() > LARGURA_DA_JANELA;
        boolean cond2 = bullet.getXPosition() < 0;
        boolean cond3 = bullet.getYPosition() < 0;
        boolean cond4 = bullet.getYPosition() > ALTURA_DA_JANELA;
        visible = !cond1 && !cond2 && !cond3 && !cond4;
        return visible;
    }

}