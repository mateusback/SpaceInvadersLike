package br.ifpr.jogo.modelo.itens;

import java.util.List;
import java.util.Random;

import br.ifpr.jogo.modelo.Personagem;

public class ItemAleatorio extends Item {
    private List<Item> itensPossiveis;

    public ItemAleatorio(List<Item> itensPossiveis) {
        this.itensPossiveis = itensPossiveis;
    }

    @Override
    public void aplicarEfeito(Personagem personagem) {
        if (!itensPossiveis.isEmpty()) {
            // Gera um número aleatório para selecionar um item da lista de itens possíveis
            Random random = new Random();
            int index = random.nextInt(itensPossiveis.size());
            Item itemSelecionado = itensPossiveis.get(index);

            // Aplica o efeito do item selecionado no personagem
            itemSelecionado.aplicarEfeito(personagem);
        }
    }
}
