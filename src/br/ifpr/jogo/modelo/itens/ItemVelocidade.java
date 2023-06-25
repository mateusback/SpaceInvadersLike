package br.ifpr.jogo.modelo.itens;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Personagem;

public class ItemVelocidade extends Item{
    // Atributos que o item pode alterar do jogador.
    private int aumentoVelocidade;

    // Construtor informando a posição e definindo o acelerador.
    public ItemVelocidade(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.aumentoVelocidade = 3;
    }

    // Sobrescrita de método carregar, para carregar a imagem na tela.
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\ItemVelocidade.png");
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
        personagem.setVelocidade(personagem.getVelocidade() + aumentoVelocidade);
    }

    // Getters e Setters
    public int getAumentoVelocidade() {
        return aumentoVelocidade;
    }

    public void setAumentoVelocidade(int aumentoVelocidade) {
        this.aumentoVelocidade = aumentoVelocidade;
    }

}
