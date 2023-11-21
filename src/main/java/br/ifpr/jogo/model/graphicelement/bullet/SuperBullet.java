package br.ifpr.jogo.model.graphicelement.bullet;


import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.*;
import javax.swing.*;


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

    public SuperBullet(){

    }
    public SuperBullet(int posicaoPersonagemEmX, int posicaoPersonagemEmY, Player player) {
        super.setXPosition(posicaoPersonagemEmX);
        super.setYPosition(posicaoPersonagemEmY);
        super.setVisible(true);
        super.setSpeed(VELOCIDADE);
        this.player = player;
        this.tempoInicial = System.currentTimeMillis();
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/SuperTiro.png"));
        super.setBaseSprite(loading.getImage());
        super.setImageHeight(ALTURA_TIRO);
        super.setImageWidth(ALTURA_TIRO);
    }

    @Override
    public void update() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoInicial;
        if (tempoDecorrido >= 5000) {
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