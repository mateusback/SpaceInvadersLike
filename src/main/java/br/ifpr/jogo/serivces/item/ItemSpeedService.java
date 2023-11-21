package br.ifpr.jogo.serivces.item;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemSpeed;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.*;

import static br.ifpr.jogo.util.ItemsConstants.AUMENTO_VELOCIDADE_ITEM;

public class ItemSpeedService {
    private ItemSpeed itemSpeed;
    public ItemSpeedService(ItemSpeed itemSpeed) {
        this.setItemSpeed(itemSpeed);
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

}
