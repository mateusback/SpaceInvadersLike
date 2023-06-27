package br.ifpr.jogo.modelo;

import java.util.Iterator;

import javax.swing.ImageIcon;

public class Inimigo extends Entidade {

    // Atributos do Inimigo
    private Personagem personagem;


    // Construtor
    public Inimigo(int posicaoEmX, int posicaoEmY, Personagem personagem) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.personagem = personagem;
        this.velocidade = 2;
    }

    // Carregando na tela com a imagem
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Inimigo.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // Movimento do Inimigo

    @Override
    public void atualizar() {
        // Calcula a direção em relação a posição do jogador.
        int deltaX = personagem.getPosicaoEmX() - this.posicaoEmX;
        int deltaY = personagem.getPosicaoEmY() - this.posicaoEmY;

        // Atualiza a posição do inimigo com base na direção.
        if (deltaX > 0) {
            this.posicaoEmX += velocidade;
        } else if (deltaX < 0) {
            this.posicaoEmX -= velocidade;
        }

        if (deltaY > 0) {
            this.posicaoEmY += velocidade;
        } else if (deltaY < 0) {
            this.posicaoEmY -= velocidade;
        }
    }

    // Getters e Setters

}