package br.ifpr.jogo.model.elementosgraficos.itens;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.elementosgraficos.Player;

import static br.ifpr.jogo.util.Constants.*;

@Entity
@Table(name = "tb_item_vida")
public class ItemVida extends Item {


    public ItemVida(int posicaoEmX, int posicaoEmY) {
        setXPosition(posicaoEmX);
        setYPosition(posicaoEmY);
        this.load();
    }

    @Override
    public void load() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemVida.png"));
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void aplicarEfeito(Player player) {
        if (player.getHitPoints() < 3) {
            player.setHitPoints(player.getHitPoints() + AUMENTO_VIDA_ITEM);
        }
    }

}
