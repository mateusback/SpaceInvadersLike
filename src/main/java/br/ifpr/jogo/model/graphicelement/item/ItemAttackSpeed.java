package br.ifpr.jogo.model.graphicelement.item;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.Player;

import static br.ifpr.jogo.util.Constants.*;

@Entity
@Table(name = "tb_item_tiro_rapido")
public class ItemAttackSpeed extends Item {

    public ItemAttackSpeed(int xPosition, int yPosition) {
        //Spawn
        setXPosition(xPosition);
        setYPosition(yPosition);
        this.load();
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/ItemTiroRapido.png"));
        super.setBaseSprite(loading.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getBulletDelay() > 0) {
            player.setBulletDelay(player.getBulletDelay() - REDUCAO_DELAY_ITEM);
        }
    }
}