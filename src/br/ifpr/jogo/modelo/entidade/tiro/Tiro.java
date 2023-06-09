package br.ifpr.jogo.modelo.entidade.tiro;

import br.ifpr.jogo.modelo.entidade.Entidade;
import br.ifpr.jogo.modelo.sprite.SpriteTiro;

public class Tiro extends Entidade {

    private SpriteTiro sprite;
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;
    public static final int VELOCIDADE = 4;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, SpriteTiro sprite, String direcao) {
        super.setPosicaoEmX(posicaoPersonagemEmX);
        super.setPosicaoEmY(posicaoPersonagemEmY);
        this.sprite = sprite;
        super.setDirecao(direcao);
        super.setVisivel(true);
        super.setVelocidade(VELOCIDADE);
    }

    @Override
    public void carregar() {
        super.setImagem(sprite.getImagem(super.getDirecao()));
        super.setAlturaImagem(ALTURA_TIRO);
        super.setLarguraImagem(ALTURA_TIRO);
    }

    @Override
    public void atualizar() {

        if (super.getDirecao().equals("direita")) {
            super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
        } else if (super.getDirecao().equals("cima")) {
            super.setPosicaoEmY(super.getPosicaoEmY() - VELOCIDADE);
        } else if (super.getDirecao().equals("esquerda")) {
            super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        } else if (super.getDirecao().equals("baixo")) {
            super.setPosicaoEmY(super.getPosicaoEmY() + VELOCIDADE);
        }
    }

    // Corrigir este método
    public boolean verificarVisibilidade() {
        boolean visivel;
        if (super.getPosicaoEmX() > 1600 || super.getPosicaoEmX() < 0 || super.getPosicaoEmY() < 0
                || super.getPosicaoEmY() > 960) {
            visivel = false;
            super.setVisivel(visivel);
        } else {
            visivel = true;
            super.setVisivel(visivel);
        }
        return visivel;
    }

}