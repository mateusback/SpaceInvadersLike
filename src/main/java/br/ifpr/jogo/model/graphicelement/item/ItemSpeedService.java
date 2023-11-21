package br.ifpr.jogo.model.graphicelement.item;

import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.AUMENTO_VELOCIDADE_ITEM;

public class ItemSpeedService {
    private ItemSpeed itemSpeed;
    private ItemSpeedController itemSpeedController;
    public ItemSpeedService(ItemSpeed itemSpeed, ItemSpeedController itemSpeedController) {
        this.setItemSpeed(itemSpeed);
        this.setItemSpeedController(itemSpeedController);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemVelocidade.png"));
        itemSpeed.setBaseSprite(loading.getImage());
        itemSpeed.setImageHeight(itemSpeed.getBaseSprite().getWidth(null));
        itemSpeed.setImageWidth(itemSpeed.getBaseSprite().getHeight(null));
    }

    public void update() {
    }

    public ItemSpeed getItemSpeed() {
        return itemSpeed;
    }

    public void setItemSpeed(ItemSpeed itemSpeed) {
        this.itemSpeed = itemSpeed;
    }

    public ItemSpeedController getItemSpeedController() {
        return itemSpeedController;
    }

    public void setItemSpeedController(ItemSpeedController itemSpeedController) {
        this.itemSpeedController = itemSpeedController;
    }
}
