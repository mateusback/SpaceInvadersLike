package br.ifpr.jogo.modelo;

import java.util.Random;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.itens.GerenciadorItem;
import br.ifpr.jogo.modelo.itens.Item;
import br.ifpr.jogo.modelo.itens.ItemTiroRapido;
import br.ifpr.jogo.modelo.itens.ItemVelocidade;

public class Inimigo extends Entidade {
    private Personagem personagem;

    private static final int VELOCIDADE = 2;

    public Inimigo(int posicaoEmX, int posicaoEmY, Personagem personagem) {
        super();
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        this.personagem = personagem;
        super.setVelocidade(VELOCIDADE);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Inimigo.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    // Movimento do inimigo
    @Override
    public void atualizar() {
        int deltaX = personagem.getPosicaoEmX() - super.getPosicaoEmX();
        int deltaY = personagem.getPosicaoEmY() - super.getPosicaoEmY();

        // Atualiza a posição do inimigo com base na direção.
        if (deltaX > 0) {
            super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
        } else if (deltaX < 0) {
            super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE);
        }

        if (deltaY > 0) {
            super.setPosicaoEmY(super.getPosicaoEmY() + VELOCIDADE);
        } else if (deltaY < 0) {
            super.setPosicaoEmY(super.getPosicaoEmY() - VELOCIDADE);
        }
    }

    public void dropItem(GerenciadorItem gerenciadorItem) {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 30) { 
            Item itemDropado;
            int tipoItem = rand.nextInt(2) + 1;
            if (tipoItem == 1) {
                itemDropado = new ItemTiroRapido(getPosicaoEmX(), getPosicaoEmY());
            } else {
                itemDropado = new ItemVelocidade(getPosicaoEmX(), getPosicaoEmY());
            }
            gerenciadorItem.adicionarItem(itemDropado);
        }
    }
}