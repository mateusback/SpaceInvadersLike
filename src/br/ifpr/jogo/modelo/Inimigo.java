package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import br.ifpr.jogo.principal.Personagem;

public class Inimigo {

    //Atributos do Inimigo
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private String direcao;

    private boolean visivel;

    private Personagem personagem;

    private static int VELOCIDADE = 2;

    //Construtor
    public Inimigo(int posicaoEmX, int posicaoEmY, Personagem personagem) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.personagem = personagem;
        this.visivel = true;
    }

    //Carregando na tela com a imagem
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Inimigo.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    //Movimento do Inimigo
    public void atualizar() {
        // Calcula a direção em relação à posição do jogador
        int deltaX = personagem.getPosicaoEmX() - this.posicaoEmX;
        int deltaY = personagem.getPosicaoEmY() - this.posicaoEmY;

        // Atualiza a posição do inimigo com base na direção
        if (deltaX > 0) {
            this.posicaoEmX += VELOCIDADE;
        } else if (deltaX < 0) {
            this.posicaoEmX -= VELOCIDADE;
        }

        if (deltaY > 0) {
            this.posicaoEmY += VELOCIDADE;
        } else if (deltaY < 0) {
            this.posicaoEmY -= VELOCIDADE;
        }
    }

    //Caixa de colisão do tamanho da imagem.

    public Rectangle getRetangulo() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    //Getters e Setters
    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
    
    public boolean getVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }
    
}