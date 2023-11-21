package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.GraphicElement;
import br.ifpr.jogo.model.graphicelement.Player;

@Entity
@Table(name="tb_item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends GraphicElement {
    @Column(name="coletado")
    private boolean collected;

    public Item() {
        this.collected = false;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemBase.png"));
        super.setVisible(true);
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    public void checkPlayerColision(Player player) {
        if (isVisible() && player.getRectangle().intersects(getRectangle())) {
            setVisible(false);
            System.out.println("Colisão com item");
            applyEffect(player);
            if (!isVisible()) {
                this.setCollected(true);
            }
        }

    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

}
