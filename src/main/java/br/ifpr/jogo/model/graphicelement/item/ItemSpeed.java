package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.Player;

import static br.ifpr.jogo.util.ItemsConstants.*;

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
