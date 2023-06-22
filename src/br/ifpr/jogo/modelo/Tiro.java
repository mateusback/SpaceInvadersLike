package br.ifpr.jogo.modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tiro {

    //Atributos do Tiro
    private int posicaoEmX;
    private int posicaoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private String direcao;
    private Sprite sprite;

    //Constantes do Tiro
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;
    private static int VELOCIDADE = 4; //Velocidade de deslocamento

    //Construtor com porâmetros de movimento, e de direção para mudar os sprites.
    public Tiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, Sprite sprite, String direcao) {
        this.posicaoEmX = posicaoPersonagemEmX;
        this.posicaoEmY = posicaoPersonagemEmY;
        this.sprite = sprite;
        this.direcao = direcao;
    }
    
    //Metodo que carrega a imagem do tiro na tela.
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Tiro_Direita.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = ALTURA_TIRO;
        this.larguraImagem = LARGURA_TIRO;
        this.imagem = sprite.getImagem(direcao);
    }

    //Metodo para atulaizar um tiro com base na direção escolhida pelo jogador(personagem)
    public void atualizar() {
        if (direcao.equals("direita")) {
            posicaoEmX += VELOCIDADE;
        } else if (direcao.equals("cima")) {
            posicaoEmY -= VELOCIDADE;
        } else if (direcao.equals("esquerda")) {
            posicaoEmX -= VELOCIDADE;
        } else if (direcao.equals("baixo")) {
            posicaoEmY += VELOCIDADE;
        } else if (direcao.equals("super")) {
            posicaoEmX += VELOCIDADE;
        }
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

}