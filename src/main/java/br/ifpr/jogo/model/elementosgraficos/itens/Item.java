package br.ifpr.jogo.model.elementosgraficos.itens;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.elementosgraficos.GraphicElement;
import br.ifpr.jogo.model.elementosgraficos.Player;

@Entity
@Table(name="tb_item")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends GraphicElement {
    @Column(name="coletado")
    private boolean coletado;

    public Item() {
        this.coletado = false;
        ImageIcon carregando = new ImageIcon(getClass().getResource("/ItemBase.png"));
        super.setVisible(true);
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    public abstract void aplicarEfeito(Player player);

    public void verificarColisao(Player player) {
        if (isVisible() && player.getRectangle().intersects(getRectangle())) {
            setVisible(false);
            System.out.println("Colisão com item");
            aplicarEfeito(player);
            if (!isVisible()) {
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
    public void load() {
        throw new UnsupportedOperationException("Unimplemented method 'carregar'");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
