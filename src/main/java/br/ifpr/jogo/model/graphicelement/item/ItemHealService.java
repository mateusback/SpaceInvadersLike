package br.ifpr.jogo.model.graphicelement.item;

import br.ifpr.jogo.controller.ItemHealController;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.AUMENTO_VIDA_ITEM;


public class ItemHealService{

    private ItemHealController itemHealController;
    private ItemHeal itemHeal;
    public ItemHealService(ItemHeal itemHeal, ItemHealController itemHealController) {
        this.setItemHeal(itemHeal);
        this.setItemHealController(itemHealController);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemVida.png"));
        itemHeal.setBaseSprite(loading.getImage());
        itemHeal.setImageHeight(itemHeal.getBaseSprite().getWidth(null));
        itemHeal.setImageWidth(itemHeal.getBaseSprite().getHeight(null));
    }

    public ItemHealController getItemHealController() {
        return itemHealController;
    }

    public void setItemHealController(ItemHealController itemHealController) {
        this.itemHealController = itemHealController;
    }

    public ItemHeal getItemHeal() {
        return itemHeal;
    }

    public void setItemHeal(ItemHeal itemHeal) {
        this.itemHeal = itemHeal;
    }
}
