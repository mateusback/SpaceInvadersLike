package br.ifpr.jogo.model.graphicelement.bullet;

import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.*;
import javax.swing.*;

import static br.ifpr.jogo.util.ScreenConstants.*;

@Entity
@Table(name = "tb_tiro")
public class Bullet extends GraphicElement {

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    public static final int ALTURA_TIRO = 30;
    public static final int SPEED = 4;

    public Bullet(){
    }

    public Bullet(int playerPositionX, int PlayerPositionY, String direction, Player player) {
        super.setXPosition(playerPositionX);
        super.setYPosition(PlayerPositionY);
        super.setDirection(direction);
        super.setVisible(true);
        super.setSpeed(SPEED);
        this.player = player;
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Tiro2.gif"));
        super.setBaseSprite(loading.getImage());
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
        boolean cond1 = super.getXPosition() > LARGURA_DA_JANELA;
        boolean cond2 = super.getXPosition() < 0;
        boolean cond3 = super.getYPosition() < 0;
        boolean cond4 = super.getYPosition() > ALTURA_DA_JANELA;
        visible = !cond1 && !cond2 && !cond3 && !cond4;
        return visible;
    }

}