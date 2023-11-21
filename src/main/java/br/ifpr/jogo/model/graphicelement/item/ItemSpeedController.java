package br.ifpr.jogo.model.graphicelement.item;

import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.AUMENTO_VELOCIDADE_ITEM;

public class ItemSpeedController{
    public ItemSpeed itemSpeed;
    public ItemSpeedService itemSpeedService;
    public ItemSpeedController(ItemSpeed itemSpeed) {
        this.setItemSpeedService(new ItemSpeedService(itemSpeed,this));
        this.setItemSpeed(itemSpeed);
    }
    public void load() {
        itemSpeedService.load();
    }

    public void update() {
        itemSpeedService.update();
    }

    public ItemSpeed getItemSpeed() {
        return itemSpeed;
    }

    public void setItemSpeed(ItemSpeed itemSpeed) {
        this.itemSpeed = itemSpeed;
    }

    public ItemSpeedService getItemSpeedService() {
        return itemSpeedService;
    }

    public void setItemSpeedService(ItemSpeedService itemSpeedService) {
        this.itemSpeedService = itemSpeedService;
    }
}
