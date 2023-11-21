package br.ifpr.jogo.serivces.item;

import br.ifpr.jogo.controller.ItemAttackSpeedController;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeed;

import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.REDUCAO_DELAY_ITEM;

public class ItemAttackSpeedService {
    private ItemAttackSpeed itemAttackSpeed;
    public ItemAttackSpeedService(ItemAttackSpeed itemAttackSpeed) {
        this.setItemAttackSpeed(itemAttackSpeed);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemTiroRapido.png"));
        itemAttackSpeed.setBaseSprite(loading.getImage());
        itemAttackSpeed.setImageHeight(itemAttackSpeed.getBaseSprite().getWidth(null));
        itemAttackSpeed.setImageWidth(itemAttackSpeed.getBaseSprite().getHeight(null));
    }

    public ItemAttackSpeed getItemAttackSpeed() {
        return itemAttackSpeed;
    }

    public void setItemAttackSpeed(ItemAttackSpeed itemAttackSpeed) {
        this.itemAttackSpeed = itemAttackSpeed;
    }
}