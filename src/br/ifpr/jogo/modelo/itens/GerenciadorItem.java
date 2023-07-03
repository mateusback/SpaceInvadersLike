package br.ifpr.jogo.modelo.itens;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorItem {
    private List<Item> itens;

    public GerenciadorItem() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }
    
    public List<Item> getItens() {
        return itens;
    }

}
