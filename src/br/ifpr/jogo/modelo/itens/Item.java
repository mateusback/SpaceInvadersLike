package br.ifpr.jogo.modelo.itens;

import br.ifpr.jogo.modelo.Entidade;
import br.ifpr.jogo.modelo.Personagem;

public abstract class Item extends Entidade {
    // Atributo para identificar se o item foi coletado ou não.
    public boolean coletado;

    // Construtor, informando que os itens não começam coletados.
    // > Nota: Isso pode dar problema caso eu queria criar novas fases, revisar esse
    // código depois.
    public Item() {
        this.coletado = false;
    }

    // Método abistrado para aplicar o efeito do item no personagem.
    public abstract void aplicarEfeito(Personagem personagem);

    // Getters e Setters
    public boolean isColetado() {
        return coletado;
    }

    public void setColetado(boolean coletado) {
        this.coletado = coletado;
    }

}
