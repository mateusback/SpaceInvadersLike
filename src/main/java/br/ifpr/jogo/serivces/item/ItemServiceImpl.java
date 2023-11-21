package br.ifpr.jogo.serivces.item;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.*;

import static br.ifpr.jogo.util.ItemsConstants.*;

public class ItemServiceImpl {
    private Item item;
    public ItemServiceImpl(Item item){
        this.setItem(item);
    }
    public void checkPlayerColision(Player player) {
        if (item.isVisible() && player.getRectangle().intersects(item.getRectangle())) {
            item.setVisible(false);
            System.out.println("Colisão com item");
            this.applyEffect(player);
            if (!item.isVisible()) {
                item.setCollected(true);
            }
        }
    }

    public void applyEffect(Player player) {
        if(item.getClass() == ItemSpeed.class){
            if (player.getSpeed() < 7) {
                player.setSpeed(player.getSpeed() + AUMENTO_VELOCIDADE_ITEM);
            }
        }
        if(item.getClass() == ItemHeal.class){
            if (player.getHitPoints() < 3) {
                player.setHitPoints(player.getHitPoints() + AUMENTO_VIDA_ITEM);
            }
        }
        if(item.getClass() == ItemAttackSpeed.class){
            if (player.getBulletDelay() > 0) {
                player.setBulletDelay(player.getBulletDelay() - REDUCAO_DELAY_ITEM);
            }
        }
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
