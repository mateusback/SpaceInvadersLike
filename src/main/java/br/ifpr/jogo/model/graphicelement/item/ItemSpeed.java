package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.Player;

import static br.ifpr.jogo.util.ItemsConstants.*;

@Entity
@Table(name = "tb_item_velocidade")
public class ItemSpeed extends Item {


    public ItemSpeed(int xPosition, int yPosition) {
        setXPosition(xPosition);
        setYPosition(yPosition);
        this.load();
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemVelocidade.png"));
        super.setBaseSprite(loading.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void update() {
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getSpeed() < 7) {
            player.setSpeed(player.getSpeed() + AUMENTO_VELOCIDADE_ITEM);
        }
    }

}
