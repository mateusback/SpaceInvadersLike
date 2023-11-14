package br.ifpr.jogo.model.graphicelement.item;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private List<Item> items;

    public ItemManager() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }
    
    public List<Item> getItems() {
        return items;
    }

}
