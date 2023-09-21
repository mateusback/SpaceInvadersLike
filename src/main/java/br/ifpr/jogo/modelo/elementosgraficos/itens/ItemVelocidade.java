package br.ifpr.jogo.modelo.elementosgraficos.itens;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.elementosgraficos.Personagem;

public class ItemVelocidade extends Item {
    private static final int AUMENTO_VELOCIDADE = 1;

    public ItemVelocidade(int posicaoEmX, int posicaoEmY) {
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemVelocidade.png"));
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void aplicarEfeito(Personagem personagem) {
        if (personagem.getVelocidade() < 7) {
            personagem.setVelocidade(personagem.getVelocidade() + AUMENTO_VELOCIDADE);
        }
    }

}