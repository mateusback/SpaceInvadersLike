package br.ifpr.jogo.model.graphicelement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.serivces.player.PlayerServiceImpl;

import static br.ifpr.jogo.util.ScreenConstants.*;


@Entity
@Table(name = "tb_personagem")
public class Player extends GraphicElement {
    public static final int VIDA_INICIAL_PERSONAGEM = 3;
    private static final int DELAY_INICIAL_TIRO = 500;
    private static final int VELOCIDADE_INICIAL_PERSONAGEM = 3;
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
    private PlayerServiceImpl playerService;

    public Player() {
        super.setXPosition(POSICAO_INICIAL_EM_X_PERSONAGEM);
        super.setYPosition(POSICAO_INICIAL_EM_Y_PERSONAGEM);
        this.bullets = new ArrayList<Bullet>();
        this.superBullets = new ArrayList<SuperBullet>();
        this.bulletDelay = DELAY_INICIAL_TIRO;
        super.setSpeed(VELOCIDADE_INICIAL_PERSONAGEM);
        super.setHitPoints(VIDA_INICIAL_PERSONAGEM);
        this.setScore(0);
        playerService = new PlayerServiceImpl(this);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
        super.setBaseSprite(loading.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void update() {
        super.setXPosition(super.getXPosition() + super.getXDisplacement());
        super.setYPosition(super.getYPosition() + super.getYDisplacement());
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

    public PlayerServiceImpl getPlayerService() {
        return playerService;
    }

    public void setPlayerService(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }
}