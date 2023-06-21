package br.ifpr.jogo.modelo;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Tiro {
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private static int VELOCIDADE = 2; //Constante
    private static final int LARGURA_DA_JANELA = 1600;

    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
    }
    
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Tiro.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);;
        this.larguraImagem = this.imagem.getHeight(null);;
    }

    public void atualizar(ArrayList<Tiro> tiros) {
        this.posicaoEmX = this.posicaoEmX + VELOCIDADE;
       if (this.posicaoEmX > LARGURA_DA_JANELA) {
            // Remover o tiro da lista de tiros
            return;
        }
    }

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

}
