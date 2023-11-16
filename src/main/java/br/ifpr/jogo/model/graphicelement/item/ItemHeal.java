package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.Player;

import static br.ifpr.jogo.util.ItemsConstants.*;

@Entity
@Table(name = "tb_item_vida")
public class ItemHeal extends Item {


    public ItemHeal(int xPosition, int yPosition) {
        setXPosition(xPosition);
        setYPosition(yPosition);
        this.load();
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemVida.png"));
        super.setBaseSprite(loading.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    public void applyEffect(Player player) {
        if (player.getHitPoints() < 3) {
            player.setHitPoints(player.getHitPoints() + AUMENTO_VIDA_ITEM);
        }
    }

}
