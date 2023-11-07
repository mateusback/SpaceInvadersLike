package br.ifpr.jogo.model.elementosgraficos.tiros;

import br.ifpr.jogo.model.elementosgraficos.GraphicElement;
import br.ifpr.jogo.model.sprites.BulletSprite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_tiro")
public class Tiro extends GraphicElement {
    @Transient
    private BulletSprite sprite;
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;
    public static final int VELOCIDADE = 4;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, BulletSprite sprite, String direcao) {
        super.setXPosition(posicaoPersonagemEmX);
        super.setYPosition(posicaoPersonagemEmY);
        this.sprite = sprite;
        super.setDirection(direcao);
        super.setVisible(true);
        super.setSpeed(VELOCIDADE);
    }

    @Override
    public void load() {
        super.setBaseSprite(sprite.getImagem(super.getDirection()));
        super.setImageHeight(ALTURA_TIRO);
        super.setImageWidth(ALTURA_TIRO);
    }

    @Override
    public void update() {

        if (super.getDirection().equals("direita")) {
            super.setXPosition(super.getXPosition() + VELOCIDADE);
        } else if (super.getDirection().equals("cima")) {
            super.setYPosition(super.getYPosition() - VELOCIDADE);
        } else if (super.getDirection().equals("esquerda")) {
            super.setXPosition(super.getXPosition() - VELOCIDADE);
        } else if (super.getDirection().equals("baixo")) {
            super.setYPosition(super.getYPosition() + VELOCIDADE);
        }
    }

    // Corrigir este método
    public boolean verificarVisibilidade() {
        boolean visivel;
        if (super.getXPosition() > 1600 || super.getXPosition() < 0 || super.getYPosition() < 0
                || super.getYPosition() > 960) {
            visivel = false;
            super.setVisible(visivel);
        } else {
            visivel = true;
            super.setVisible(visivel);
        }
        return visivel;
    }

}