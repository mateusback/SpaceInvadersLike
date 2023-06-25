package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entidade {
    // Atributos de uma imagem.

    // Posição na tela:
    protected int posicaoEmX;
    protected int posicaoEmY;

    // Velocidade:
    protected int deslocamentoEmX;
    protected int deslocamentoEmY;

    // Visual Básico:
    protected Image imagem;
    protected boolean visivel;

    // Tamanho da imagem:
    protected int larguraImagem;
    protected int alturaImagem;

    // Para calculos mais especificos ou alteraçao de sprite:
    protected String direcao;
    protected int velocidade;

    // Construtor, uma entidade é construida como visivel.
    public Entidade() {
        this.visivel = true;
    }

    // Métodos básicos para load e update na tela de fase.
    public abstract void carregar();

    public abstract void atualizar();

    // Caixa de Colisão
    public Rectangle getRetangulo() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    // Getters e Setters de uma entidade.
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

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
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

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

}
