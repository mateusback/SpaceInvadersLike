package br.ifpr.jogo.modelo;

import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

public class Tiro extends Entidade{

    //Atributos do Tiro
    private Sprite sprite;
    private boolean visivel;

    //Constantes do Tiro
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;

    //Construtor com porâmetros de movimento, e de direção para mudar os sprites.
    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, Sprite sprite, String direcao) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
        this.sprite = sprite;
        this.direcao = direcao;
        this.visivel = true;
        this.velocidade = 4;
    }
    
    //Metodo que carrega a imagem do tiro na tela.
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Tiro.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = ALTURA_TIRO;
        this.larguraImagem = LARGURA_TIRO;
        this.imagem = sprite.getImagem(direcao);
    }

    //Metodo para atulaizar um tiro com base na direção escolhida pelo jogador(personagem)
    @Override
    public void atualizar() {
        if (direcao.equals("direita")) {
            posicaoEmX += velocidade;
        } else if (direcao.equals("cima")) {
            posicaoEmY -= velocidade;
        } else if (direcao.equals("esquerda")) {
            posicaoEmX -= velocidade;
        } else if (direcao.equals("baixo")) {
            posicaoEmY += velocidade;
        } else if (direcao.equals("super")) {
            posicaoEmX += velocidade;
        }
    }

    //Getters e Setters
    @Override
    public boolean isVisivel() {
        if(this.posicaoEmX>1600 || this.posicaoEmX<0 || this.posicaoEmY<0 || this.posicaoEmY>1000){
            this.visivel = false;
        } else{
            this.visivel = true;
        }
        return this.visivel;
    }
}