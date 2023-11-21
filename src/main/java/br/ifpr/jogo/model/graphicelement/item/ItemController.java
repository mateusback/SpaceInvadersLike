package br.ifpr.jogo.model.graphicelement.item;

import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.serivces.item.ItemService;

import javax.persistence.*;
import javax.swing.*;

public abstract class ItemController {
    public Item item;
    public ItemService itemService;

    public ItemController(Item item) {
        this.setItemService(item, this);
        this.setItem(item);
    }

    public void checkPlayerColision(Player player) {
        itemService.checkPlayerColision(player);
    }

    public void applyEffect(Player player){
        itemService.applyEffect(player);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
}
