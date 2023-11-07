package br.ifpr.jogo.model.elementosgraficos.itens;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.elementosgraficos.Player;

import static br.ifpr.jogo.util.Constants.*;

@Entity
@Table(name = "tb_item_velocidade")
public class ItemVelocidade extends Item {


    public ItemVelocidade(int posicaoEmX, int posicaoEmY) {
        setXPosition(posicaoEmX);
        setYPosition(posicaoEmY);
        this.load();
    }

    @Override
    public void load() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemVelocidade.png"));
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void update() {
    }

    @Override
    public void aplicarEfeito(Player player) {
        if (player.getSpeed() < 7) {
            player.setSpeed(player.getSpeed() + AUMENTO_VELOCIDADE_ITEM);
        }
    }

}
