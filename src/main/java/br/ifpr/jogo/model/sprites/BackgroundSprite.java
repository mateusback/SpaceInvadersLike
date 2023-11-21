package br.ifpr.jogo.model.sprites;

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

import br.ifpr.jogo.model.graphicelement.Player;

import static br.ifpr.jogo.util.ScreenConstants.*;

public class BackgroundSprite implements ImageObserver {
        private int offsetX; // Deslocamento horizontal da câmera
        private int offsetY; // Deslocamento vertical da câmera
        private BufferedImage[] tile;
        private static final int LARGURA_BLOCO_BACKGROUND = 64;
        private static final int ALTURA_BLOCO_BACKGROUND = 64;
        private int xInitial;
        private int yInitial;

        public BackgroundSprite() {
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

        public void loadLevel1(Graphics g) {
                try {
                        int[][] mapa = readMap("src\\main\\resources\\map.txt");

                        Graphics2D graficos = (Graphics2D) g;
                        // Tamanho da fase
                        int larguraFase = 50 * LARGURA_BLOCO_BACKGROUND;
                        int alturaFase = 32 * ALTURA_BLOCO_BACKGROUND;

                        // Posição inicial dos blocos para centralizar na tela
                        xInitial = (LARGURA_DA_JANELA - larguraFase) / 2;
                        yInitial = (ALTURA_DA_JANELA - alturaFase) / 2;
                        for (int yPosition = 0; yPosition < mapa.length; yPosition++) {
                                for (int xPosition = 0; xPosition < mapa[yPosition].length; xPosition++) {
                                        int tileNum = mapa[yPosition][xPosition];
                                        int xBlock = xInitial + xPosition * LARGURA_BLOCO_BACKGROUND - offsetX;
                                        int yBlock = yInitial + yPosition * ALTURA_BLOCO_BACKGROUND - offsetY;

                                        if (tileNum >= 0 && tileNum < tile.length) {
                                                graficos.drawImage(tile[tileNum], xBlock, yBlock, this);
                                        }
                                }
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        public void updateOffset(Player player) {
                offsetX = player.getXPosition() - LARGURA_DA_JANELA / 2;
                offsetY = player.getYPosition() - ALTURA_DA_JANELA / 2;
        }

        public static int[][] readMap(String filePath) throws IOException {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                int lines = 0;
                int columns = 0;
                while ((line = reader.readLine()) != null) {
                        lines++;
                        columns = line.trim().split(" ").length;
                }

                reader.close();

                int[][] map = new int[lines][columns];
                reader = new BufferedReader(new FileReader(filePath));

                int i = 0;
                while ((line = reader.readLine()) != null) {
                        String[] blockCode = line.trim().split("\\s+");

                        for (int j = 0; j < columns; j++) {
                                map[i][j] = Integer.parseInt(blockCode[j]);
                        }

                        i++;
                }

                reader.close();

                return map;
        }

        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                throw new UnsupportedOperationException("Unimplemented method 'imageUpdate'");
        }
}
