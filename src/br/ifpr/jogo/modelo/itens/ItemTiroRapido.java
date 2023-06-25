package br.ifpr.jogo.modelo.itens;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class ItemTiroRapido extends Item {
    // Atributos que o item pode alterar do jogador.
    private int reducaoDelay;

    // Construtor informando a posicão e definindo o delay.
    public ItemTiroRapido(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.reducaoDelay = 400;
    }

    // Sobrescrita de método carregar, para carregar a imagem na tela.
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\ItemTiroRapido.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // Atualizar pode ser vazio, pois o item ficará parado na tela.
    @Override
    public void atualizar() {
    }

    // Metodo para aplicar o efeito do item no jogador.
    @Override
    public void aplicarEfeito(Personagem personagem) {
        personagem.setDelayTiro(personagem.getDelayTiro() - reducaoDelay);
    }

    // Getters e Setters
    public int getReducaoDelay() {
        return this.reducaoDelay;
    }

    public void setReducaoDelay(int reducaoDelay) {
        this.reducaoDelay = reducaoDelay;
    }

}