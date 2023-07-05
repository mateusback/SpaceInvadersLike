package br.ifpr.jogo.modelo.entidade.item;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.entidade.Personagem;

public class ItemVida extends Item {
    private final int AUMENTO_VIDA = 1;

    public ItemVida(int posicaoEmX, int posicaoEmY) {
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\ItemVida.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void aplicarEfeito(Personagem personagem) {
        if (personagem.getVida() < 3) {
            personagem.setVida(personagem.getVida() + AUMENTO_VIDA);
        }
    }

}
