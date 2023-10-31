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
        setPosicaoEmX(posicaoEmX);
        setPosicaoEmY(posicaoEmY);
        this.carregar();
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemVelocidade.png"));
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void aplicarEfeito(Player player) {
        if (player.getVelocidade() < 7) {
            player.setVelocidade(player.getVelocidade() + AUMENTO_VELOCIDADE_ITEM);
        }
    }

}
