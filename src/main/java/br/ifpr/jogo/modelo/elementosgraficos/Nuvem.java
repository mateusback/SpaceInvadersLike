package br.ifpr.jogo.modelo.elementosgraficos;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import static br.ifpr.jogo.util.Constants.*;

//MODEL
@Entity
@Table(name = "tb_nuvem")
public class Nuvem extends ElementoGrafico{
    private static int VELOCIDADE = 3;

    public Nuvem(int xAleatorio, int yAleatorio){
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/Nuvem.png"));
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        if (super.getPosicaoEmX() > LARGURA_DA_JANELA) {
            int y = (int) (Math.random() * ALTURA_DA_JANELA);
            super.setPosicaoEmX(-super.getImagem().getWidth(null));
            super.setPosicaoEmY(y);
        } else {
            super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
        }
    }
}
