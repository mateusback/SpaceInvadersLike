package br.ifpr.jogo.modelo.itens;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class ItemVelocidade extends Item {
    private static final int AUMENTO_VELOCIDADE = 3;

    public ItemVelocidade(int posicaoEmX, int posicaoEmY) {
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\ItemVelocidade.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void aplicarEfeito(Personagem personagem) {
        personagem.setVelocidade(personagem.getVelocidade() + AUMENTO_VELOCIDADE);
    }

}
