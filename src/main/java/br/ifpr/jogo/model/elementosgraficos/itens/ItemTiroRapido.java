package br.ifpr.jogo.model.elementosgraficos.itens;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.elementosgraficos.Player;

import static br.ifpr.jogo.util.Constants.*;

@Entity
@Table(name = "tb_item_tiro_rapido")
public class ItemTiroRapido extends Item {

    public ItemTiroRapido(int posicaoEmX, int posicaoEmY) {
        setXPosition(posicaoEmX);
        setYPosition(posicaoEmY);
        this.load();
    }

    @Override
    public void load() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemTiroRapido.png"));
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void aplicarEfeito(Player player) {
        if (player.getDelayTiro() > 0) {
            player.setDelayTiro(player.getDelayTiro() - REDUCAO_DELAY_ITEM);
        }
    }
}