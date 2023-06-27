package br.ifpr.jogo.modelo.Sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.ifpr.jogo.modelo.Personagem;

public class SpriteFundo implements ImageObserver {
    private BufferedImage grama;
    private BufferedImage terra;
    private BufferedImage terraGramaDireita;
    private BufferedImage terraGramaCima;
    private BufferedImage terraGramaEsquerda;
    private BufferedImage terraGramaBaixo;
    private BufferedImage terraGramaCentro;
    private BufferedImage terraDiagonalEsquerdaCima;
    private BufferedImage terraDiagonalDireitaCima;
    private BufferedImage terraDiagonalDireitaBaixo;
    private BufferedImage terraDiagonalEsquerdaBaixo;
    private int offsetX; // Deslocamento horizontal da câmera
    private int offsetY; // Deslocamento vertical da câmera

    int LARGURA_TELA = 1600;
    int ALTURA_TELA = 960;

    // Dimensões dos blocos
    int LARGURA_BLOCO = 64;
    int ALTURA_BLOCO = 64;

    // Cálculo do número de blocos necessários
    int numBlocosHorizontal = LARGURA_TELA / LARGURA_BLOCO;
    int numBlocosVertical = ALTURA_TELA / ALTURA_BLOCO;

    // Posição inicial dos blocos para centralizar na tela
    int xInicial = (LARGURA_TELA - numBlocosHorizontal * LARGURA_BLOCO) / 2;
    int yInicial = (ALTURA_TELA - numBlocosVertical * ALTURA_BLOCO) / 2;

    public SpriteFundo() {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File("recursos\\SpriteSheet.png"));
            this.grama = spriteSheet.getSubimage(0, 0, 64, 64);
            this.terra = spriteSheet.getSubimage(64, 0, 64, 64);
            this.terraGramaDireita = spriteSheet.getSubimage(128, 0, 64, 64);
            this.terraGramaCima = spriteSheet.getSubimage(192, 0, 64, 64);
            this.terraGramaEsquerda = spriteSheet.getSubimage(256, 0, 64, 64);
            this.terraGramaBaixo = spriteSheet.getSubimage(320, 0, 64, 64);
            this.terraGramaCentro = spriteSheet.getSubimage(384, 0, 64, 64);
            this.terraDiagonalEsquerdaCima = spriteSheet.getSubimage(448, 0, 64, 64);
            this.terraDiagonalDireitaCima = spriteSheet.getSubimage(512, 0, 64, 64);
            this.terraDiagonalDireitaBaixo = spriteSheet.getSubimage(0, 64, 64, 64);
            this.terraDiagonalEsquerdaBaixo = spriteSheet.getSubimage(64, 64, 64, 64);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarFase1(Graphics g, Personagem personagem) {
        Graphics2D graficos = (Graphics2D) g;

        // Tamanho da fase
        int larguraFase = 200 * LARGURA_BLOCO;
        int alturaFase = 200 * ALTURA_BLOCO;

        // Número de blocos horizontal e vertical
        int numBlocosHorizontal = larguraFase / LARGURA_BLOCO;
        int numBlocosVertical = alturaFase / ALTURA_BLOCO;

        // Posição inicial dos blocos para centralizar na tela
        int xInicial = (LARGURA_TELA - larguraFase) / 2;
        int yInicial = (ALTURA_TELA - alturaFase) / 2;

        for (int i = 0; i < numBlocosHorizontal; i++) {
            for (int j = 0; j < numBlocosVertical; j++) {
                int xBloco = xInicial + i * LARGURA_BLOCO - offsetX;
                int yBloco = yInicial + j * ALTURA_BLOCO - offsetY;

                // Define o bloco de acordo com as coordenadas i e j
                Image bloco;
                if (j < 3 || j > 20 || i < 3 || i > 196) {
                    bloco = grama;
                } else if (j == 3 && (i >= 3 && i <= 197)) {
                    bloco = terraGramaCima;
                } else if ((j > 3 && j < 197) && (i == 3 || i == 196)) {
                    bloco = terraGramaEsquerda;
                } else if (j == 196 && (i >= 3 && i <= 197)) {
                    bloco = terraGramaBaixo;
                } else if ((j > 3 && j < 197) && (i == 195)) {
                    bloco = terraGramaDireita;
                } else if (j >= 5 && j <= 195 && i >= 4 && i <= 196) {
                    bloco = terraGramaCentro;
                } else {
                    bloco = grama;
                }

                // Desenha o bloco na posição (xBloco, yBloco)
                graficos.drawImage(bloco, xBloco, yBloco, this);
            }
        }
    }

    public void atualizarJogo(Personagem personagem) {
        // Restante do código de atualização do jogo

        // Atualiza o deslocamento da câmera com base na posição do personagem
        offsetX = personagem.getPosicaoEmX() - LARGURA_TELA / 2;
        offsetY = personagem.getPosicaoEmY() - ALTURA_TELA / 2;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Unimplemented method 'imageUpdate'");
    }
}
