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
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemVida.png"));
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void aplicarEfeito(Player player) {
        if (player.getVida() < 3) {
            player.setVida(player.getVida() + AUMENTO_VIDA_ITEM);
        }
    }

}
