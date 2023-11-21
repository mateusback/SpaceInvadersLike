package br.ifpr.jogo.model.graphicelement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.ifpr.jogo.controller.PlayerController;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;

import static br.ifpr.jogo.util.ScreenConstants.*;


@Entity
@Table(name = "tb_personagem")
public class Player extends GraphicElement {
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bullet> bullets;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuperBullet> superBullets;
    @Column(name = "tempo_ultimo_tiro")
    private long lastShootMark;
    @Column(name = "tempo_atual")
    private long timeMark;
    @Column(name = "delay_tiro")
    private long bulletDelay;
    @Column(name = "pontuacao")
    private int score;
    @Transient
    private PlayerController playerController;

    public Player() {
        this.setPlayerController(new PlayerController(this));
        super.setXPosition(INITIAL_POSITION_X_PLAYER);
        super.setYPosition(INITIAL_POSITION_Y_PLAYER);
        this.bullets = new ArrayList<Bullet>();
        this.superBullets = new ArrayList<SuperBullet>();
        this.bulletDelay = playerController.DELAY_INICIAL_TIRO;
        super.setSpeed(playerController.VELOCIDADE_INICIAL_PERSONAGEM);
        super.setHitPoints(playerController.VIDA_INICIAL_PERSONAGEM);
        this.setScore(0);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<SuperBullet> getSuperBullets() {
        return superBullets;
    }

    public void setSuperBullets(List<SuperBullet> superBullets) {
        this.superBullets = superBullets;
    }

    public long getLastShootMark() {
        return lastShootMark;
    }

    public void setLastShootMark(long lastShootMark) {
        this.lastShootMark = lastShootMark;
    }

    public long getTimeMark() {
        return timeMark;
    }

    public void setTimeMark(long timeMark) {
        this.timeMark = timeMark;
    }

    public long getBulletDelay() {
        return bulletDelay;
    }

    public void setBulletDelay(long bulletDelay) {
        this.bulletDelay = bulletDelay;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }
}