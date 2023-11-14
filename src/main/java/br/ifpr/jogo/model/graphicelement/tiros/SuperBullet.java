package br.ifpr.jogo.model.graphicelement.tiros;


import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.sprites.BulletSprite;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "tb_super_tiro")
public class SuperBullet extends GraphicElement {
    @Transient
    private BulletSprite sprite;
    @Transient
    private Player player;
    private long tempoInicial;
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;
    public static final int VELOCIDADE = 4;

    public SuperBullet(int posicaoPersonagemEmX, int posicaoPersonagemEmY, BulletSprite sprite, String direcao, Player player) {
        super.setXPosition(posicaoPersonagemEmX);
        super.setYPosition(posicaoPersonagemEmY);
        this.sprite = sprite;
        super.setDirection(direcao);
        super.setVisible(true);
        super.setSpeed(VELOCIDADE);
        this.player = player;
        this.tempoInicial = System.currentTimeMillis();
    }

    @Override
    public void load() {
        super.setBaseSprite(sprite.getImagem(super.getDirection()));
        super.setImageHeight(ALTURA_TIRO);
        super.setImageWidth(ALTURA_TIRO);
    }

    @Override
    public void update() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoInicial;
        if (tempoDecorrido >= 5000) { // 5000 ms = 5 segundos
            super.setVisible(false);
        } else {
            double angulo = (tempoDecorrido / 1000.0) * Math.PI * 2;
            int raio = 100;

            int posX = (int) (Math.cos(angulo) * raio) + player.getXPosition();
            int posY = (int) (Math.sin(angulo) * raio) + player.getYPosition();

            super.setXPosition(posX);
            super.setYPosition(posY);
        }
    }

    // Corrigir este método
    public boolean verificarVisibilidade() {
        boolean visivel = super.isVisible(); // obtém o valor padrão de visibilidade da classe pai

        if (super.getXPosition() > 1600 || super.getXPosition() < 0 || super.getYPosition() < 0
                || super.getYPosition() > 960) {
            visivel = false;
        }

        return visivel;
    }

}