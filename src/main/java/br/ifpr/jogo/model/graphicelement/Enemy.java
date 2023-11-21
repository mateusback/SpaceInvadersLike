package br.ifpr.jogo.model.graphicelement;

import javax.persistence.*;

import br.ifpr.jogo.controller.EnemyController;

//MODEL
@Entity
@Table(name = "tb_inimigo")
public class Enemy extends GraphicElement {
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    private EnemyController enemyController;
    public Enemy() {
    }
    public Enemy(int posicaoEmX, int posicaoEmY, Player player) {
        super();
        this.setEnemyController(new EnemyController(this));
        super.setXPosition(posicaoEmX);
        super.setYPosition(posicaoEmY);
        this.player = player;
        super.setSpeed(enemyController.VELOCIDADE_INIMIGO);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public void setEnemyController(EnemyController enemyController) {
        this.enemyController = enemyController;
    }
}