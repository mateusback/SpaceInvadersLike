package br.ifpr.jogo.modelo.elementosgraficos.itens;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.elementosgraficos.Personagem;

public class ItemTiroRapido extends Item {
    private static final int REDUCAO_DELAY = 20;

    public ItemTiroRapido(int posicaoEmX, int posicaoEmY) {
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemTiroRapido.png"));
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void aplicarEfeito(Personagem personagem) {
        if (personagem.getDelayTiro() > 0) {
            personagem.setDelayTiro(personagem.getDelayTiro() - REDUCAO_DELAY);
        }
    }
}