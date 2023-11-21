package br.ifpr.jogo.model.graphicelement.item;

import br.ifpr.jogo.controller.ItemAttackSpeedController;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.REDUCAO_DELAY_ITEM;

public class ItemAttackSpeedService {
    private ItemAttackSpeedController itemAttackSpeedController;
    private ItemAttackSpeed itemAttackSpeed;
    public ItemAttackSpeedService(ItemAttackSpeed itemAttackSpeed, ItemAttackSpeedController itemAttackSpeedController) {
        this.setItemAttackSpeed(itemAttackSpeed);
        this.setItemAttackSpeedController(itemAttackSpeedController);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemTiroRapido.png"));
        itemAttackSpeed.setBaseSprite(loading.getImage());
        itemAttackSpeed.setImageHeight(itemAttackSpeed.getBaseSprite().getWidth(null));
        itemAttackSpeed.setImageWidth(itemAttackSpeed.getBaseSprite().getHeight(null));
    }

    public void applyEffect(Player player) {
        if (player.getBulletDelay() > 0) {
            player.setBulletDelay(player.getBulletDelay() - REDUCAO_DELAY_ITEM);
        }
    }

    public ItemAttackSpeedController getItemAttackSpeedController() {
        return itemAttackSpeedController;
    }

    public void setItemAttackSpeedController(ItemAttackSpeedController itemAttackSpeedController) {
        this.itemAttackSpeedController = itemAttackSpeedController;
    }

    public ItemAttackSpeed getItemAttackSpeed() {
        return itemAttackSpeed;
    }

    public void setItemAttackSpeed(ItemAttackSpeed itemAttackSpeed) {
        this.itemAttackSpeed = itemAttackSpeed;
    }
}