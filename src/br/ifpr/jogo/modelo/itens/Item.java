package br.ifpr.jogo.modelo.itens;

import br.ifpr.jogo.modelo.Entidade;
import br.ifpr.jogo.modelo.Personagem;

public abstract class Item extends Entidade {
    private boolean coletado;

    public Item() {
        this.coletado = false;
        super.setVisivel(true);
    }

    public abstract void aplicarEfeito(Personagem personagem);

    public void verificarColisao(Personagem personagem) {
        if (isVisivel() && personagem.getRetangulo().intersects(getRetangulo())) {
            setVisivel(false);
            System.out.println("Colisão com item");
            aplicarEfeito(personagem);
            if (!isVisivel()) {
                this.setColetado(true);
            }
        }

    }

    // Getters e Setters
    public boolean isColetado() {
        return coletado;
    }

    public void setColetado(boolean coletado) {
        this.coletado = coletado;
    }

    @Override
    public void carregar() {
        throw new UnsupportedOperationException("Unimplemented method 'carregar'");
    }

    @Override
    public void atualizar() {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
}
