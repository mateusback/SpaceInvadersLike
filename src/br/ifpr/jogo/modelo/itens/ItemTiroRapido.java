package br.ifpr.jogo.modelo.itens;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class ItemTiroRapido extends Item {
    private static final int REDUCAO_DELAY = 30;

    public ItemTiroRapido(int posicaoEmX, int posicaoEmY) {
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\ItemTiroRapido.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void aplicarEfeito(Personagem personagem) {
        personagem.setDelayTiro(personagem.getDelayTiro() - REDUCAO_DELAY);
    }
}