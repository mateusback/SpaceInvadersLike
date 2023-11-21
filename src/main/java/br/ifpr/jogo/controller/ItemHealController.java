package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemHeal;
import br.ifpr.jogo.model.graphicelement.item.ItemHealService;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.AUMENTO_VIDA_ITEM;

public class ItemHealController{
    private ItemHeal itemHeal;
    private ItemHealService itemHealService;
    public ItemHealController(ItemHeal itemHeal) {
        this.setItemHealService(new ItemHealService(itemHeal, this));
        this.setItemHeal(itemHeal);
    }

    public void load() {
        itemHealService.load();
    }

    public void applyEffect(Player player) {
        itemHealService.applyEffect(player);
    }

    public ItemHeal getItemHeal() {
        return itemHeal;
    }

    public void setItemHeal(ItemHeal itemHeal) {
        this.itemHeal = itemHeal;
    }

    public ItemHealService getItemHealService() {
        return itemHealService;
    }

    public void setItemHealService(ItemHealService itemHealService) {
        this.itemHealService = itemHealService;
    }
}
