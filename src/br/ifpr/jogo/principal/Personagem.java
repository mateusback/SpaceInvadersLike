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
        posicaoEmX += deslocamentoEmX;
        posicaoEmY += deslocamentoEmY;
    }

    public void keyPressed(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        if(codigo ==KeyEvent.VK_W || codigo == KeyEvent.VK_UP){
            deslocamentoEmY = - 3;
        }
        if(codigo ==KeyEvent.VK_S || codigo == KeyEvent.VK_DOWN){
            deslocamentoEmY = 3;
        }
        if(codigo ==KeyEvent.VK_A || codigo == KeyEvent.VK_LEFT){
            deslocamentoEmX = - 3;
        }
        if(codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT){
            deslocamentoEmX = 3;
        }
        if(codigo == KeyEvent.VK_SPACE){
            posicaoEmX += 100;
        }
    }

    public void keyReleased(KeyEvent tecla){
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
