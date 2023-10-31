package br.ifpr.jogo.model.elementosgraficos;

import javax.persistence.*;
import java.awt.Image;
import java.awt.Rectangle;

//MODEL
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementoGrafico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_elemeto_grafico")
    private Integer idElemetoGrafico;

    @Column(name = "posicao_x")
    private int posicaoEmX;
    @Column(name = "posicao_y")
    private int posicaoEmY;
    @Column(name = "deslocamento_x")
    private int deslocamentoEmX;
    @Column(name = "deslocamento_y")
    private int deslocamentoEmY;
    @Transient
    private Image imagem;
    @Column(name = "visivel")
    private boolean visivel;
    @Column(name = "largura_imagem")
    private int larguraImagem;
    @Column(name = "altura_imagem")
    private int alturaImagem;

    @Column(name = "direcao")
    private String direcao;
    @Column(name = "velocidade")
    private int velocidade;
    @Column(name = "vida")
    private int vida;

    public ElementoGrafico() {
        this.visivel = true;
    }

    public abstract void carregar();

    public abstract void atualizar();

    public Rectangle getRetangulo() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Integer getIdElemetoGrafico() {
        return idElemetoGrafico;
    }

    public void setIdElemetoGrafico(Integer idElemetoGrafico) {
        this.idElemetoGrafico = idElemetoGrafico;
    }
}
