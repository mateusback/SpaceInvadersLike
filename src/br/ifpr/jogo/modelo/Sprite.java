package br.ifpr.jogo.modelo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class Sprite {
    private Map<String, Image> imagens;

    public Sprite() {
        imagens = new HashMap<>();
    }

    public void carregar() {
        // Carregar a imagem padrão
        ImageIcon icon = new ImageIcon("recursos\\Tiro.png");
        Image imagemPadrao = icon.getImage();
        imagens.put("padrao", imagemPadrao);

        // Carregar as imagens para cada direção
        ImageIcon iconDireita = new ImageIcon("recursos\\Tiro_Direita.png");
        Image imagemDireita = iconDireita.getImage();
        imagens.put("direita", imagemDireita);

        ImageIcon iconEsquerda = new ImageIcon("recursos\\Tiro_Esquerda.png");
        Image imagemEsquerda = iconEsquerda.getImage();
        imagens.put("esquerda", imagemEsquerda);

        ImageIcon iconCima = new ImageIcon("recursos\\Tiro_Cima.png");
        Image imagemCima = iconCima.getImage();
        imagens.put("cima", imagemCima);

        ImageIcon iconBaixo = new ImageIcon("recursos\\Tiro_Baixo.png");
        Image imagemBaixo = iconBaixo.getImage();
        imagens.put("baixo", imagemBaixo);

        ImageIcon iconSuper = new ImageIcon("recursos\\SuperTiro.png");
        Image imagemSuper = iconSuper.getImage();
        imagens.put("super", imagemSuper);
    }

    public Image getImagem(String direcao) {
        Image imagem = imagens.get(direcao);
        if (imagem == null) {
            // Se não houver uma imagem específica para a direção, retorna a imagem padrão
            imagem = imagens.get("padrao");
        }
        return imagem;
    }
}
