package br.ifpr.jogo.modelo.elementosgraficos.itens;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.elementosgraficos.Personagem;

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
    public void aplicarEfeito(Personagem personagem) {
        if (personagem.getVida() < 3) {
            personagem.setVida(personagem.getVida() + AUMENTO_VIDA_ITEM);
        }
    }

}
