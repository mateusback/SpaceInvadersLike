package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ifpr.jogo.controller.ItemSpeedController;

@Entity
@Table(name = "tb_item_velocidade")
public class ItemSpeed extends Item {
    @Transient
    private ItemSpeedController itemSpeedController;
    public ItemSpeed(int xPosition, int yPosition) {
        this.setItemSpeedController(new ItemSpeedController(this));
        setXPosition(xPosition);
        setYPosition(yPosition);
        itemSpeedController.load();
    }

    public ItemSpeedController getItemSpeedController() {
        return itemSpeedController;
    }

    public void setItemSpeedController(ItemSpeedController itemSpeedController) {
        this.itemSpeedController = itemSpeedController;
    }
}
