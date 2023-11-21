package br.ifpr.jogo.model.graphicelement;

import java.util.Random;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.item.ItemManager;
import br.ifpr.jogo.model.graphicelement.item.Item;
import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeed;
import br.ifpr.jogo.model.graphicelement.item.ItemSpeed;
import br.ifpr.jogo.model.graphicelement.item.ItemHeal;

import static br.ifpr.jogo.util.ScreenConstants.*;

//MODEL
@Entity
@Table(name = "tb_inimigo")
public class Enemy extends GraphicElement {
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    private static final int VELOCIDADE_INIMIGO = 2;
    public Enemy() {
    }
    public Enemy(int posicaoEmX, int posicaoEmY, Player player) {
        super();
        super.setXPosition(posicaoEmX);
        super.setYPosition(posicaoEmY);
        this.player = player;
        super.setSpeed(VELOCIDADE_INIMIGO);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Inimigo.png"));
        super.setBaseSprite(loading.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    // Movimento do inimigo
    @Override
    public void update() {
        int deltaX = player.getXPosition() - super.getXPosition();
        int deltaY = player.getYPosition() - super.getYPosition();

        // Atualiza a posição do inimigo com base na direção.
        if (deltaX > 0) {
            super.setXPosition(super.getXPosition() + VELOCIDADE_INIMIGO);
        } else if (deltaX < 0) {
            super.setXPosition(super.getXPosition() - VELOCIDADE_INIMIGO);
        }

        if (deltaY > 0) {
            super.setYPosition(super.getYPosition() + VELOCIDADE_INIMIGO);
        } else if (deltaY < 0) {
            super.setYPosition(super.getYPosition() - VELOCIDADE_INIMIGO);
        }
    }

    public void dropItem(ItemManager itemManager) {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 25) { 
            Item itemDropado;
            int tipoItem = rand.nextInt(3) + 1;
            if (tipoItem == 1) {
                itemDropado = new ItemAttackSpeed(super.getXPosition(), super.getYPosition());
            } else if(tipoItem == 2) {
                itemDropado = new ItemSpeed(super.getXPosition(), super.getYPosition());
            } else{
                itemDropado = new ItemHeal(super.getXPosition(), super.getYPosition());
            }
            itemManager.addItem(itemDropado);
        }
    }
}