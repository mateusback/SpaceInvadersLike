package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.controller.ItemController;
import br.ifpr.jogo.model.graphicelement.GraphicElement;

@Entity
@Table(name="tb_item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends GraphicElement {
    @Column(name="coletado")
    private boolean collected;

    @Transient
    private br.ifpr.jogo.controller.ItemController ItemController;

    public Item() {
        this.setItemController(new ItemController(this));
        this.collected = false;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemBase.png"));
        super.setVisible(true);
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public ItemController getItemController() {
        return ItemController;
    }

    public void setItemController(ItemController itemController) {
        ItemController = itemController;
    }
}
