package br.ifpr.jogo.model.graphicelement.bullet;

import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.sprites.BulletSprite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import static br.ifpr.jogo.util.ScreenConstants.*;

@Entity
@Table(name = "tb_tiro")
public class Bullet extends GraphicElement {
    @Transient
    private BulletSprite sprite;
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;
    public static final int SPEED = 4;

    public Bullet(int playerPositionX, int PlayerPositionY, BulletSprite sprite, String direction) {
        super.setXPosition(playerPositionX);
        super.setYPosition(PlayerPositionY);
        this.sprite = sprite;
        super.setDirection(direction);
        super.setVisible(true);
        super.setSpeed(SPEED);
    }

    @Override
    public void load() {
        super.setBaseSprite(sprite.getImagem(super.getDirection()));
        super.setImageHeight(ALTURA_TIRO);
        super.setImageWidth(ALTURA_TIRO);
    }

    @Override
    public void update() {
        switch (super.getDirection()) {
            case "right" -> super.setXPosition(super.getXPosition() + SPEED);
            case "top" -> super.setYPosition(super.getYPosition() - SPEED);
            case "left" -> super.setXPosition(super.getXPosition() - SPEED);
            case "bottom" -> super.setYPosition(super.getYPosition() + SPEED);
        }
    }

    public boolean checkVisibility() {
        boolean visible;
        if (super.getXPosition() > ALTURA_DA_JANELA || super.getXPosition() < 0 || super.getYPosition() < 0
                || super.getYPosition() > LARGURA_DA_JANELA) {
            visible = false;
            super.setVisible(false);
        } else {
            visible = true;
            super.setVisible(true);
        }
        return visible;
    }

}