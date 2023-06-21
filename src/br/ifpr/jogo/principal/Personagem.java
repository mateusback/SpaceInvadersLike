package br.ifpr.jogo.principal;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Tiro;

public class Personagem {
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private String ultimoMovimento; //Atributo para direcionar o dash
    private ArrayList<Tiro> tiros;

    //Constantes
    private static int POSICAO_INICIAL_EM_X = 500;
    private static int POSICAO_INICIAL_EM_Y = 500;
    private static int DESLOCAMENTO = 3;


    public Personagem(){
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
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

    public void atirar(){
        int frentedoPersonagem = this.posicaoEmX + this.larguraImagem;
        int meiodoPersonagem = this.posicaoEmY + (this.alturaImagem / 2);
        Tiro tiro = new Tiro(frentedoPersonagem, meiodoPersonagem);
        this.tiros.add(tiro);
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
            ultimoMovimento = "esquerda";
        }
        if(codigo == KeyEvent.VK_D || codigo == KeyEvent.VK_RIGHT){
            deslocamentoEmX = DESLOCAMENTO;
            ultimoMovimento = "direita";
        }

        //dash
        if(codigo == KeyEvent.VK_SPACE && ultimoMovimento == "esquerda"){
            posicaoEmX -= 100;
        }
        if(codigo == KeyEvent.VK_SPACE && ultimoMovimento == "direita"){ 
            posicaoEmX += 100;
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

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }
    
}
