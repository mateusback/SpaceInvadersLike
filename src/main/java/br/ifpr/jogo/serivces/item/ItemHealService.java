package br.ifpr.jogo.serivces.item;

import br.ifpr.jogo.controller.ItemHealController;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemHeal;

import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.AUMENTO_VIDA_ITEM;


public class ItemHealService{

    private ItemHeal itemHeal;
    public ItemHealService(ItemHeal itemHeal) {
        this.setItemHeal(itemHeal);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemVida.png"));
        itemHeal.setBaseSprite(loading.getImage());
        itemHeal.setImageHeight(itemHeal.getBaseSprite().getWidth(null));
        itemHeal.setImageWidth(itemHeal.getBaseSprite().getHeight(null));
    }

    public ItemHeal getItemHeal() {
        return itemHeal;
    }

    public void setItemHeal(ItemHeal itemHeal) {
        this.itemHeal = itemHeal;
    }
}
