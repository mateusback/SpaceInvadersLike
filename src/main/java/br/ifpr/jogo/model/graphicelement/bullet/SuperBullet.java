package br.ifpr.jogo.model.graphicelement.bullet;


import br.ifpr.jogo.controller.SuperBulletController;
import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.*;


@Entity
@Table(name = "tb_super_tiro")
public class SuperBullet extends GraphicElement {
    public static final int ALTURA_TIRO = 30;
    public static final int VELOCIDADE = 4;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Transient
    private long tempoInicial;

    @Transient
    private SuperBulletController superBulletController;

    public SuperBullet(int posicaoPersonagemEmX, int posicaoPersonagemEmY, Player player) {
        this.setSuperBulletController(new SuperBulletController((this)));
        super.setXPosition(posicaoPersonagemEmX);
        super.setYPosition(posicaoPersonagemEmY);
        super.setVisible(true);
        super.setSpeed(VELOCIDADE);
        this.setPlayer(player);
        this.setTempoInicial(System.currentTimeMillis());
    }

    public SuperBullet(){
        this.setSuperBulletController(new SuperBulletController((this)));
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(long tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    public SuperBulletController getSuperBulletController() {
        return superBulletController;
    }

    public void setSuperBulletController(SuperBulletController superBulletController) {
        this.superBulletController = superBulletController;
    }
}