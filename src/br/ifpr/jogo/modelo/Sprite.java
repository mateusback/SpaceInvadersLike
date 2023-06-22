package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class Sprite {

    //Atributos do Sprite
    private Map<String, Image> imagens;

    //Construtor
    public Sprite() {
        imagens = new HashMap<>();
    }

    public void carregar() {
        // Carregar a imagem padrão
        ImageIcon icon = new ImageIcon("recursos\\Tiro.png");
        Image imagemPadrao = icon.getImage();
        imagens.put("padrao", imagemPadrao);

        // Carregar as imagens para cada direção
        ImageIcon iconDireita = new ImageIcon("recursos\\Tiro_Direita.gif");
        Image imagemDireita = iconDireita.getImage();
        imagens.put("direita", imagemDireita);

        ImageIcon iconEsquerda = new ImageIcon("recursos\\Tiro_Esquerda.gif");
        Image imagemEsquerda = iconEsquerda.getImage();
        imagens.put("esquerda", imagemEsquerda);

        ImageIcon iconCima = new ImageIcon("recursos\\Tiro_Cima.gif");
        Image imagemCima = iconCima.getImage();
        imagens.put("cima", imagemCima);

        ImageIcon iconBaixo = new ImageIcon("recursos\\Tiro_Baixo.gif");
        Image imagemBaixo = iconBaixo.getImage();
        imagens.put("baixo", imagemBaixo);
    }

    //método para pegar a imagem com relação a key, se for nula, será carregada a imagem padrão
    public Image getImagem(String direcao) {
        Image imagem = imagens.get(direcao);
        if (imagem == null) {
            // Se não houver uma imagem específica para a direção, retorna a imagem padrão
            imagem = imagens.get("padrao");
        }
        return imagem;
    }
}
