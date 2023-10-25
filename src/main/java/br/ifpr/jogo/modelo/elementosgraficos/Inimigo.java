package br.ifpr.jogo.modelo.elementosgraficos;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.elementosgraficos.itens.GerenciadorItem;
import br.ifpr.jogo.modelo.elementosgraficos.itens.Item;
import br.ifpr.jogo.modelo.elementosgraficos.itens.ItemTiroRapido;
import br.ifpr.jogo.modelo.elementosgraficos.itens.ItemVelocidade;
import br.ifpr.jogo.modelo.elementosgraficos.itens.ItemVida;

import static br.ifpr.jogo.util.Constants.*;

//MODEL
@Entity
@Table(name = "tb_inimigo")
public class Inimigo extends ElementoGrafico {
    @Transient
    private Personagem personagem;


    public Inimigo(int posicaoEmX, int posicaoEmY, Personagem personagem) {
        super();
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        this.personagem = personagem;
        super.setVelocidade(VELOCIDADE_INIMIGO);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/Inimigo.png"));
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
            super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE_INIMIGO);
        } else if (deltaX < 0) {
            super.setPosicaoEmX(super.getPosicaoEmX() - VELOCIDADE_INIMIGO);
        }

        if (deltaY > 0) {
            super.setPosicaoEmY(super.getPosicaoEmY() + VELOCIDADE_INIMIGO);
        } else if (deltaY < 0) {
            super.setPosicaoEmY(super.getPosicaoEmY() - VELOCIDADE_INIMIGO);
        }
    }

    public void dropItem(GerenciadorItem gerenciadorItem) {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 25) { 
            Item itemDropado;
            int tipoItem = rand.nextInt(3) + 1;
            if (tipoItem == 1) {
                itemDropado = new ItemTiroRapido(super.getPosicaoEmX(), super.getPosicaoEmY());
            } else if(tipoItem == 2) {
                itemDropado = new ItemVelocidade(super.getPosicaoEmX(), super.getPosicaoEmY());
            } else{
                itemDropado = new ItemVida(super.getPosicaoEmX(), super.getPosicaoEmY());
            }
            gerenciadorItem.adicionarItem(itemDropado);
        }
    }
}