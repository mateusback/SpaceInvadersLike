package br.ifpr.jogo.model.graphicelement.bullet;

import br.ifpr.jogo.controller.BulletController;
import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.*;

@Entity
@Table(name = "tb_tiro")
public class Bullet extends GraphicElement {
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @Transient
    private BulletController bulletController;
    private static final int SPEED = 4;

    public Bullet(){
    }

    public Bullet(int playerPositionX, int PlayerPositionY, String direction, Player player) {
        super.setXPosition(playerPositionX);
        super.setYPosition(PlayerPositionY);
        super.setDirection(direction);
        super.setSpeed(SPEED);
        this.setPlayer(player);
        this.setBulletController(new BulletController(this));
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public void setBulletController(BulletController bulletController) {
        this.bulletController = bulletController;
    }
}