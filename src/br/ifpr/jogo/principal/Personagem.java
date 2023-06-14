package br.ifpr.jogo.principal;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private String ultimaAcao;

    //Constantes, note que estão em caixa alta.
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;

    public Personagem(){
        this.posicaoEmX = 100;
        this.posicaoEmY = 100;
    }

    public void carregar(){
        ImageIcon carregando = new ImageIcon("recursos\\Personagem.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar(){
        this.posicaoEmX += deslocamentoEmX;
        this.posicaoEmY += deslocamentoEmY;
    }

    public void mover(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo ==KeyEvent.VK_W || codigo == KeyEvent.VK_UP){
            deslocamentoEmY = - DESLOCAMENTO;
        }
        if(codigo ==KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN){
            deslocamentoEmY = DESLOCAMENTO;
        }
        if(codigo ==KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT){
            deslocamentoEmX = - DESLOCAMENTO;
            ultimaAcao = "esquerda";
        }
        if(codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT){
            deslocamentoEmX = DESLOCAMENTO;
            ultimaAcao = "direita";
        }

        //Dash
        if(codigo == KeyEvent.VK_SPACE && ultimaAcao == "direita"){
            posicaoEmX += 100;
        }
        if(codigo == KeyEvent.VK_SPACE && ultimaAcao == "esquerda"){
            posicaoEmX -= 100;
        }
    }

    public void parar(KeyEvent tecla){
        int codigo = tecla.getKeyCode();
    
        if(codigo ==KeyEvent.VK_W || codigo == KeyEvent.VK_UP){
            deslocamentoEmY = 0;
        }
        if(codigo ==KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN){
            deslocamentoEmY = 0;
        }
        if(codigo ==KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT){
            deslocamentoEmX = 0;
        }
        if(codigo ==KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT){
            deslocamentoEmX = 0;
        }
        if(codigo == KeyEvent.VK_SPACE){
            deslocamentoEmX = 0;
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

    public Image getImagem() {
        return imagem;
    }
}
