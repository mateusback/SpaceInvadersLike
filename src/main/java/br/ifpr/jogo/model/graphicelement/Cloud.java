package br.ifpr.jogo.model.graphicelement;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import static br.ifpr.jogo.util.Constants.*;

//MODEL
@Entity
@Table(name = "tb_nuvem")
public class Cloud extends GraphicElement {
    private static int SPEED = 3;

    public Cloud(int xRandom, int yRandom){
        this.load();
        super.setXPosition(xRandom);
        super.setYPosition(yRandom);
    }

    public Cloud() {

    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Nuvem.png"));
        super.setBaseSprite(loading.getImage());
    }

    @Override
    public void update() {
        if (super.getXPosition() > LARGURA_DA_JANELA) {
            int y = (int) (Math.random() * ALTURA_DA_JANELA);
            super.setXPosition(-super.getBaseSprite().getWidth(null));
            super.setYPosition(y);
        } else {
            super.setXPosition(super.getXPosition() + SPEED);
        }
    }
}
