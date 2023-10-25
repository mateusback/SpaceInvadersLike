package br.ifpr.jogo.modelo.sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import br.ifpr.jogo.modelo.elementosgraficos.Personagem;

import static br.ifpr.jogo.util.Constants.*;

public class SpriteFundo implements ImageObserver {
        private int offsetX; // Deslocamento horizontal da câmera
        private int offsetY; // Deslocamento vertical da câmera
        private BufferedImage[] tile;

        // Posição inicial dos blocos para centralizar na tela
        private int xInicial;
        private int yInicial;

        public SpriteFundo() {
                tile = new BufferedImage[19];
                try {
                        BufferedImage spriteSheet = ImageIO.read(new File("src\\main\\resources\\SpriteSheet.png"));
                        tile[0] = spriteSheet.getSubimage(0, 0, 64, 64); // Grama com efeitos
                        tile[1] = spriteSheet.getSubimage(64, 0, 64, 64); // Terra Com efeitos
                        tile[2] = spriteSheet.getSubimage(128, 0, 64, 64); //
                        tile[3] = spriteSheet.getSubimage(192, 0, 64, 64);
                        tile[4] = spriteSheet.getSubimage(256, 0, 64, 64);
                        tile[5] = spriteSheet.getSubimage(320, 0, 64, 64);
                        tile[6] = spriteSheet.getSubimage(384, 0, 64, 64);
                        tile[7] = spriteSheet.getSubimage(448, 0, 64, 64);
                        tile[8] = spriteSheet.getSubimage(512, 0, 64, 64);
                        tile[9] = spriteSheet.getSubimage(0, 64, 64, 64);
                        tile[10] = spriteSheet.getSubimage(64, 64, 64, 64);
                        tile[11] = spriteSheet.getSubimage(0, 128, 64, 64);
                        tile[12] = spriteSheet.getSubimage(64, 128, 64, 64);
                        tile[13] = spriteSheet.getSubimage(128, 64, 64, 64);
                        tile[14] = spriteSheet.getSubimage(128, 128, 64, 64);
                        tile[15] = spriteSheet.getSubimage(192, 64, 64, 64);
                        tile[16] = spriteSheet.getSubimage(192, 128, 64, 64);
                        tile[17] = spriteSheet.getSubimage(256, 64, 64, 64);
                        tile[18] = spriteSheet.getSubimage(256, 128, 64, 64);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void carregarFase1(Graphics g, Personagem personagem) {
                try {
                        int[][] mapa = carregarMapa("src\\main\\resources\\map.txt");

                        Graphics2D graficos = (Graphics2D) g;
                        // Tamanho da fase
                        int larguraFase = 50 * LARGURA_BLOCO_BACKGROUND;
                        int alturaFase = 32 * ALTURA_BLOCO_BACKGROUND;

                        // Posição inicial dos blocos para centralizar na tela
                        xInicial = (LARGURA_DA_JANELA - larguraFase) / 2;
                        yInicial = (ALTURA_DA_JANELA - alturaFase) / 2;
                        for (int posY = 0; posY < mapa.length; posY++) {
                                for (int posX = 0; posX < mapa[posY].length; posX++) {
                                        int tileNum = mapa[posY][posX];
                                        int xBloco = xInicial + posX * LARGURA_BLOCO_BACKGROUND - offsetX;
                                        int yBloco = yInicial + posY * ALTURA_BLOCO_BACKGROUND - offsetY;

                                        if (tileNum >= 0 && tileNum < tile.length) {
                                                graficos.drawImage(tile[tileNum], xBloco, yBloco, this);
                                        }
                                }
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void atualizarJogo(Personagem personagem) {
                // Atualiza o deslocamento da câmera com base na posição do personagem
                offsetX = personagem.getPosicaoEmX() - LARGURA_DA_JANELA / 2;
                offsetY = personagem.getPosicaoEmY() - ALTURA_DA_JANELA / 2;
        }

        public static int[][] carregarMapa(String arquivo) throws IOException {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                String linha;
                int linhas = 0;
                int colunas = 0;
                // Primeira passagem para obter o tamanho do mapa
                while ((linha = reader.readLine()) != null) {
                        linhas++;
                        colunas = linha.trim().split(" ").length;
                }

                reader.close();

                // Segunda passagem para carregar os dados do mapa
                int[][] mapa = new int[linhas][colunas];
                reader = new BufferedReader(new FileReader(arquivo));

                int i = 0;
                while ((linha = reader.readLine()) != null) {
                        String[] numeros = linha.trim().split("\\s+");

                        for (int j = 0; j < colunas; j++) {
                                mapa[i][j] = Integer.parseInt(numeros[j]);
                        }

                        i++;
                }

                reader.close();

                return mapa;
        }

        public int getOffsetX() {
                return offsetX;
        }

        public void setOffsetX(int offsetX) {
                this.offsetX = offsetX;
        }

        public int getOffsetY() {
                return offsetY;
        }

        public void setOffsetY(int offsetY) {
                this.offsetY = offsetY;
        }

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                throw new UnsupportedOperationException("Unimplemented method 'imageUpdate'");
        }
}
