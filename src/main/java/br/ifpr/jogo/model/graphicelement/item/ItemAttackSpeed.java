package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ifpr.jogo.controller.ItemAttackSpeedController;

@Entity
@Table(name = "tb_item_tiro_rapido")
public class ItemAttackSpeed extends Item {
    @Transient
    private ItemAttackSpeedController ItemAttackSpeedController;
    public ItemAttackSpeed(int xPosition, int yPosition) {
        this.setItemAttackSpeedController(new ItemAttackSpeedController(this));
        setXPosition(xPosition);
        setYPosition(yPosition);
        ItemAttackSpeedController.load();
    }

    public ItemAttackSpeedController getItemAttackSpeedController() {
        return ItemAttackSpeedController;
    }

    public void setItemAttackSpeedController(ItemAttackSpeedController itemAttackSpeedController) {
        ItemAttackSpeedController = itemAttackSpeedController;
    }
}