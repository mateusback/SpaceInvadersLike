package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeed;
import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeedService;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.REDUCAO_DELAY_ITEM;

public class ItemAttackSpeedController{
    ItemAttackSpeedService itemAttackSpeedService;

    ItemAttackSpeed itemAttackSpeed;
    public ItemAttackSpeedController(ItemAttackSpeed itemAttackSpeed) {
        this.setItemAttackSpeedService(new ItemAttackSpeedService(itemAttackSpeed, this));
        this.setItemAttackSpeed(itemAttackSpeed);
    }

    public void load() {
        itemAttackSpeedService.load();
    }

    public void applyEffect(Player player) {
        itemAttackSpeedService.applyEffect(player);
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