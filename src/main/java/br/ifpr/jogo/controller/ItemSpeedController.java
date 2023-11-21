package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.item.ItemSpeed;
import br.ifpr.jogo.serivces.item.ItemSpeedService;

public class ItemSpeedController{
    public ItemSpeed itemSpeed;
    public ItemSpeedService itemSpeedService;
    public ItemSpeedController(ItemSpeed itemSpeed) {
        this.setItemSpeedService(new ItemSpeedService(itemSpeed));
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
