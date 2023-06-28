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
        private int offsetX; // Deslocamento horizontal da câmera
        private int offsetY; // Deslocamento vertical da câmera
        private BufferedImage[] tile;
        private int[][] tiles;
        private int mapTileNum[][] = {
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 0, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        9, 3, 3, 3, 3, 3, 3, 3, 10, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        4, 1, 7, 7, 7, 7, 7, 7, 2, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        4, 7, 7, 7, 7, 7, 1, 7, 2, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        4, 7, 7, 1, 7, 7, 7, 7, 2, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        4, 7, 7, 7, 7, 1, 7, 7, 2, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        11, 5, 5, 5, 5, 5, 5, 5, 12, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 9, 3, 3, 3, 10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 4, 7, 7, 7, 7, 10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 4, 7, 1, 7, 7, 7, 3, 3, 10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 4, 7, 7, 7, 7, 1, 7, 7, 2, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 11, 7, 7, 1, 7, 7, 7, 7, 2, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 11, 1, 7, 7, 7, 7, 7, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 11, 5, 5, 5, 5, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 0, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 6,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
                        { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
                                        8, 8, 8, 8,
                                        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 } };

        private static int LARGURA_TELA = 1600;
        private static int ALTURA_TELA = 960;

        // Dimensões dos blocos
        private static int LARGURA_BLOCO = 64;
        private static int ALTURA_BLOCO = 64;

        // Cálculo do número de blocos necessários

        // Posição inicial dos blocos para centralizar na tela
        private int xInicial;
        private int yInicial;

        public SpriteFundo() {
                tiles = new int[50][32];
                tile = new BufferedImage[13];
                try {
                        BufferedImage spriteSheet = ImageIO.read(new File("recursos\\SpriteSheet.png"));
                        tile[0] = spriteSheet.getSubimage(0, 0, 64, 64);
                        tile[1] = spriteSheet.getSubimage(64, 0, 64, 64);
                        tile[2] = spriteSheet.getSubimage(128, 0, 64, 64);
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
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void carregarFase1(Graphics g, Personagem personagem) {
                Graphics2D graficos = (Graphics2D) g;
                // Tamanho da fase
                int larguraFase = 50 * LARGURA_BLOCO;
                int alturaFase = 32 * ALTURA_BLOCO;

                // Número de blocos horizontal e vertical

                // Posição inicial dos blocos para centralizar na tela
                xInicial = (LARGURA_TELA - larguraFase) / 2;
                yInicial = (ALTURA_TELA - alturaFase) / 2;
                for (int i = 0; i < mapTileNum.length; i++) {
                        for (int j = 0; j < mapTileNum[i].length; j++) {
                                int tileNum = mapTileNum[i][j];
                                int tipoTile = getTile(j, i);
                                int xBloco = xInicial + j * LARGURA_BLOCO - offsetX;
                                int yBloco = yInicial + i * ALTURA_BLOCO - offsetY;

                                graficos.drawImage(tile[tileNum], xBloco, yBloco, this);

                        }
                }
        }

        public void atualizarJogo(Personagem personagem) {
                // Atualiza o deslocamento da câmera com base na posição do personagem
                offsetX = personagem.getPosicaoEmX() - LARGURA_TELA / 2;
                offsetY = personagem.getPosicaoEmY() - ALTURA_TELA / 2;
        }

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                throw new UnsupportedOperationException("Unimplemented method 'imageUpdate'");
        }

        public int getTile(int x, int y) {
                return tiles[x][y];
        }
        public void setTile(int x, int y, int tipoTile) {
                tiles[x][y] = tipoTile;
        }
}
