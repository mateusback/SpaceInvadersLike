package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeed;
import br.ifpr.jogo.serivces.item.ItemAttackSpeedService;

public class ItemAttackSpeedController{
    ItemAttackSpeedService itemAttackSpeedService;

    ItemAttackSpeed itemAttackSpeed;
    public ItemAttackSpeedController(ItemAttackSpeed itemAttackSpeed) {
        this.setItemAttackSpeedService(new ItemAttackSpeedService(itemAttackSpeed));
        this.setItemAttackSpeed(itemAttackSpeed);
    }

    public void load() {
        itemAttackSpeedService.load();
    }

    public ItemAttackSpeedService getItemAttackSpeedService() {
        return itemAttackSpeedService;
    }

    public void setItemAttackSpeedService(ItemAttackSpeedService itemAttackSpeedService) {
        this.itemAttackSpeedService = itemAttackSpeedService;
    }

    public ItemAttackSpeed getItemAttackSpeed() {
        return itemAttackSpeed;
    }

    public void setItemAttackSpeed(ItemAttackSpeed itemAttackSpeed) {
        this.itemAttackSpeed = itemAttackSpeed;
    }
}