package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.item.ItemHeal;
import br.ifpr.jogo.serivces.item.ItemHealService;

public class ItemHealController{
    private ItemHeal itemHeal;
    private ItemHealService itemHealService;
    public ItemHealController(ItemHeal itemHeal) {
        this.setItemHealService(new ItemHealService(itemHeal));
        this.setItemHeal(itemHeal);
    }

    public void load() {
        itemHealService.load();
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
