package br.ifpr.jogo.model.sprites;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

// A clase Sprite está sendo utilizada somente para o tiro no momento,
// Mas eu gostaria de implementa-la nas demais entidades no futuro.
public class BulletSprite {

    //Atributos do Sprite
    private Map<String, Image> images;

    //Construtor
    public BulletSprite() {
        images = new HashMap<>();
    }

    public void carregar() {
        // Carregar a imagem padrão
        ImageIcon icon = new ImageIcon(getClass().getResource("/Tiro.png"));
        Image defaultSprite = icon.getImage();
        images.put("default", defaultSprite);

        // Carregar as imagens para cada direção
        ImageIcon rightIcon = new ImageIcon(getClass().getResource("/Tiro_Direita.gif"));
        Image rightSprite = rightIcon.getImage();
        images.put("right", rightSprite);

        ImageIcon leftIcon = new ImageIcon(getClass().getResource("/Tiro_Esquerda.gif"));
        Image leftSprite = leftIcon.getImage();
        images.put("left", leftSprite);

        ImageIcon topIcon = new ImageIcon(getClass().getResource("/Tiro_Cima.gif"));
        Image topSprite = topIcon.getImage();
        images.put("top", topSprite);

        ImageIcon bottomIcon = new ImageIcon(getClass().getResource("/Tiro_Baixo.gif"));
        Image bottomSprite = bottomIcon.getImage();
        images.put("bottom", bottomSprite);

        ImageIcon superIcon = new ImageIcon(getClass().getResource("/SuperTiro.png"));
        Image superSprite = superIcon.getImage();
        images.put("super", superSprite);
    }

    //método para pegar a imagem com relação a key, se for nula, será carregada a imagem padrão
    public Image getImagem(String direction) {
        Image imagem = images.get(direction);
        if (imagem == null) {
            // Se não houver uma imagem específica para a direção, retorna a imagem padrão
            imagem = images.get("default");
        }
        return imagem;
    }
}
