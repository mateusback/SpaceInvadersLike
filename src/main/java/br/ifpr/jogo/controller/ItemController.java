package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.Item;
import br.ifpr.jogo.serivces.item.ItemService;
import br.ifpr.jogo.serivces.item.ItemServiceImpl;

import javax.persistence.*;
import javax.swing.*;

public class ItemController {
    public Item item;
    public ItemServiceImpl itemServiceImpl;

    public ItemController(Item item) {
        this.setItemServiceImpl(new ItemServiceImpl(item));
        this.setItem(item);
    }

    public void checkPlayerColision(Player player) {
        itemServiceImpl.checkPlayerColision(player);
    }

    public void applyEffect(Player player){
        itemServiceImpl.applyEffect(player);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ItemServiceImpl getItemServiceImpl() {
        return itemServiceImpl;
    }

    public void setItemServiceImpl(ItemServiceImpl itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }
}
