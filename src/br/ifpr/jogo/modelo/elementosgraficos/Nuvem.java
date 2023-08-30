package br.ifpr.jogo.modelo.elementosgraficos;

import javax.swing.ImageIcon;

import br.ifpr.jogo.principal.Principal;

public class Nuvem extends ElementoGrafico{
    private static int VELOCIDADE = 3;

    public Nuvem(int xAleatorio, int yAleatorio){
        this.carregar();
        super.setPosicaoEmX(xAleatorio);
        super.setPosicaoEmY(yAleatorio);
    }
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Nuvem.png");
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        if (super.getPosicaoEmX() > Principal.LARGURA_DA_JANELA) {
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            super.setPosicaoEmX(-super.getImagem().getWidth(null));
            super.setPosicaoEmY(y);
        } else {
            super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
        }
    }
}
