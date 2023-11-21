package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.ifpr.jogo.controller.ItemHealController;

@Entity
@Table(name = "tb_item_vida")
public class ItemHeal extends Item {
    @Transient
    private ItemHealController ItemHealController;
    public ItemHeal(int xPosition, int yPosition) {
        this.setItemHealController(new ItemHealController(this));
        setXPosition(xPosition);
        setYPosition(yPosition);
        ItemHealController.load();
    }

    public br.ifpr.jogo.controller.ItemHealController getItemHealController() {
        return ItemHealController;
    }

    public void setItemHealController(br.ifpr.jogo.controller.ItemHealController itemHealController) {
        ItemHealController = itemHealController;
    }
}
