package br.ifpr.jogo.model.elementosgraficos.itens;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.elementosgraficos.ElementoGrafico;
import br.ifpr.jogo.model.elementosgraficos.Player;

@Entity
@Table(name="tb_item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends ElementoGrafico {
    @Column(name="coletado")
    private boolean coletado;

    public Item() {
        this.coletado = false;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemBase.png"));
        super.setVisivel(true);
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    public abstract void aplicarEfeito(Player player);

    public void verificarColisao(Player player) {
        if (isVisivel() && player.getRetangulo().intersects(getRetangulo())) {
            setVisivel(false);
            System.out.println("Colisão com item");
            aplicarEfeito(player);
            if (!isVisivel()) {
                this.setColetado(true);
            }
        }

    }

    // Getters e Setters
    public boolean isColetado() {
        return coletado;
    }

    public void setColetado(boolean coletado) {
        this.coletado = coletado;
    }

    @Override
    public void carregar() {
        throw new UnsupportedOperationException("Unimplemented method 'carregar'");
    }

    @Override
    public void atualizar() {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
