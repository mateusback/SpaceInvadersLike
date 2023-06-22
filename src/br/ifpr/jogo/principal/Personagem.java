package br.ifpr.jogo.principal;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Sprite;
import br.ifpr.jogo.modelo.Tiro;

public class Personagem {

    //Atributos do Personagem
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;
    private String direcao; //Atributo para direcionar o dash
    private ArrayList<Tiro> tiros;
    

    //Constantes
    private static int POSICAO_INICIAL_EM_X = 500;
    private static int POSICAO_INICIAL_EM_Y = 500;
    private static int DESLOCAMENTO = 3;

    //Construtor, já carrega a sua posição inicial e inicializa um array de tiros
    public Personagem(){
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
    }

    //Metodo para carregar o jogador na tela
    public void carregar(){
        ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    //Método responsavel por atulalizar o jogador, movimentando caso o deslocamento não seja = 0
    public void atualizar(){
        posicaoEmX += deslocamentoEmX;
        posicaoEmY += deslocamentoEmY;
    }

    //Metodo responsavel pelo moviemnto do personagem, alterando o deslocamento e setando as direções.
    //Existe uma atualização do icone do jogador nas condições: baixo, direta e esquerda.
    //Há um dash ao pressionar o a tecla espaço
    public void mover(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        //Cima
        if(codigo ==KeyEvent.VK_W){
            deslocamentoEmY = - DESLOCAMENTO;
            this.setDirecao("cima");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Cima.gif");
            this.imagem = carregando.getImage();
        }

        //Baixo
        if(codigo ==KeyEvent.VK_S){
            deslocamentoEmY = DESLOCAMENTO;
            this.setDirecao("baixo");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Baixo.gif");
            this.imagem = carregando.getImage();
        }

        //Esquerda
        if(codigo ==KeyEvent.VK_A){
            deslocamentoEmX = - DESLOCAMENTO;
            this.setDirecao("esquerda");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Esquerda.gif");
            this.imagem = carregando.getImage();

        //Direita
        }
        if(codigo == KeyEvent.VK_D){
            deslocamentoEmX = DESLOCAMENTO;
            this.setDirecao("direita");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Direita.gif");
            this.imagem = carregando.getImage();
        }

        //Dash
        if(codigo == KeyEvent.VK_SPACE && direcao == "esquerda"){
            posicaoEmX -= 100;
        }
        if(codigo == KeyEvent.VK_SPACE && direcao == "direita"){ 
            posicaoEmX += 100;
        }
    }

    //Metodo responsavel por parar o personagem ao finalizar o movimento.
    //Este metodo está carregando a imagem padrão do personagem para que ela volte ao deixar o personagem imovél
    public void parar(KeyEvent tecla){
        int codigo = tecla.getKeyCode();

        //Cima
        if(codigo ==KeyEvent.VK_W ){
            deslocamentoEmY = 0;
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
            this.imagem = carregando.getImage();
        }

        //Baixo
        if(codigo ==KeyEvent.VK_S){
            deslocamentoEmY = 0;
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
            this.imagem = carregando.getImage();
        }

        //Esquerda
        if(codigo ==KeyEvent.VK_A){
            deslocamentoEmX = 0;
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
            this.imagem = carregando.getImage();
        }

        //Direita 
        if(codigo ==KeyEvent.VK_D){
            deslocamentoEmX = 0;
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
            this.imagem = carregando.getImage();
        }
    }

    //Este metodo é responsavel pela ação de tiro do jogador. Foi o metodo mais trabalhoso até agora
    //Ao atirar, a direção do tiro também é setada para que ele saia na direção desejada
    public void atirar(KeyEvent tecla) {
        // Criando uma instância de Sprite
        Sprite sprite = new Sprite();
        sprite.carregar();
        // Tiro para a Direita
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT || tecla.getKeyCode() == KeyEvent.VK_L) {
            int frenteDoPersonagem = this.posicaoEmX + this.larguraImagem;
            int meioDoPersonagem = this.posicaoEmY + (this.alturaImagem / 2);
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "direita");
            this.tiros.add(tiro);
        }

        // Tiro para Cima
        if (tecla.getKeyCode() == KeyEvent.VK_UP || tecla.getKeyCode() == KeyEvent.VK_I) {
            int frenteDoPersonagem = this.posicaoEmX + (this.larguraImagem / 2);
            int meioDoPersonagem = this.posicaoEmY + this.alturaImagem;
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "cima");
            this.tiros.add(tiro);
        }

        // Tiro para a Esquerda
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT || tecla.getKeyCode() == KeyEvent.VK_J) {
            int frenteDoPersonagem = this.posicaoEmX - Tiro.LARGURA_TIRO;
            int meioDoPersonagem = this.posicaoEmY + (this.alturaImagem / 2);
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "esquerda");
            this.tiros.add(tiro);
        }

        // Tiro para Baixo
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN || tecla.getKeyCode() == KeyEvent.VK_K) {
            int frenteDoPersonagem = this.posicaoEmX + (this.larguraImagem / 2);
            int meioDoPersonagem = this.posicaoEmY - Tiro.ALTURA_TIRO;
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "baixo");
            this.tiros.add(tiro);
        }

        //Super Tiro
        if (tecla.getKeyCode() == KeyEvent.VK_F) {
            int frenteDoPersonagem = this.posicaoEmX + (this.larguraImagem / 2);
            int meioDoPersonagem = this.posicaoEmY - Tiro.ALTURA_TIRO;
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "super");
            this.tiros.add(tiro);
        }
    }

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

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public void setDirecao(String direcao) {
    this.direcao = direcao;
    }
    
}