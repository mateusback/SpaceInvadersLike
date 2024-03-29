package br.ifpr.jogo.serivces.bullet;

import br.ifpr.jogo.dao.BulletDAO;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;

import javax.swing.*;
import java.util.List;

import static br.ifpr.jogo.util.ScreenConstants.WINDOW_HEIGHT;
import static br.ifpr.jogo.util.ScreenConstants.WINDOW_WIDTH;

public class BulletService {
    private Bullet bullet;
    public static final int ALTURA_TIRO = 30;
    public static final int SPEED = 4;
    public BulletService(Bullet bullet){
        this.setBullet(bullet);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Tiro2.gif"));
        bullet.setBaseSprite(loading.getImage());
        bullet.setImageHeight(ALTURA_TIRO);
        bullet.setImageWidth(ALTURA_TIRO);
    }

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
        boolean cond1 = bullet.getXPosition() > WINDOW_WIDTH;
        boolean cond2 = bullet.getXPosition() < 0;
        boolean cond3 = bullet.getYPosition() < 0;
        boolean cond4 = bullet.getYPosition() > WINDOW_HEIGHT;
        visible = !cond1 && !cond2 && !cond3 && !cond4;
        return visible;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
}