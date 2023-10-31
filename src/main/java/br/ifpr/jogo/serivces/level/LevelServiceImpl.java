package br.ifpr.jogo.serivces.level;

import br.ifpr.jogo.model.Fase;
import br.ifpr.jogo.model.LevelModel;
import br.ifpr.jogo.model.elementosgraficos.Inimigo;
import br.ifpr.jogo.model.elementosgraficos.Cloud;
import br.ifpr.jogo.model.elementosgraficos.itens.Item;
import br.ifpr.jogo.model.elementosgraficos.tiros.SuperTiro;
import br.ifpr.jogo.model.elementosgraficos.tiros.Tiro;
import br.ifpr.jogo.model.sprites.SpriteFundo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static br.ifpr.jogo.util.Constants.*;

public class LevelServiceImpl implements LevelService{
    private Fase level;
    private LevelModel levelModel;
    public LevelServiceImpl(Fase level, LevelModel levelModel){
        this.level = level;
        this.levelModel = levelModel;
    }

    @Override
    public void drawBullets(Graphics g) {
        ArrayList<Tiro> tiros = levelModel.getPlayer().getTiros();
        for (Tiro tiro : tiros) {
            tiro.carregar();
            g.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
        }

        //SuperTiros
        ArrayList<SuperTiro> superTiros = levelModel.getPlayer().getSuperTiros();
        for (SuperTiro superTiro : superTiros) {
            superTiro.carregar();
            g.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), null);
        }
    }

    @Override
    public void drawEnemies(Graphics g) {
        for (Inimigo inimigo : levelModel.getEnemies()) {
            g.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), null);
        }
    }

    @Override
    public void drawItems(Graphics g) {
        if (!(level.itemManager == null)) {
            for (Item item : level.itemManager.getItems()) {
                if (item.isVisivel()) {
                    g.drawImage(item.getImagem(), item.getPosicaoEmX(), item.getPosicaoEmY(), null);
                }
            }
        }
    }

    @Override
    public void drawClouds(Graphics g) {
        for (Cloud cloud : levelModel.getNuvens()) {
            g.drawImage(cloud.getImagem(), cloud.getPosicaoEmX(), cloud.getPosicaoEmY(), null);
        }
    }

    @Override
    public void drawPoints(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Pontuação: " + levelModel.getPlayer().getPontos(), LARGURA_DA_JANELA - 300, 30);
    }

    @Override
    public void drawShootDelay(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Delay Tiro: " + levelModel.getPlayer().getDelayTiro()/100, LARGURA_DA_JANELA - 300, 50);
    }

    @Override
    public void drawSpeed(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Velocidade: " + levelModel.getPlayer().getVelocidade(), LARGURA_DA_JANELA - 300, 70);
    }

    @Override
    public void drawGameOver(Graphics g) {
        if (level.gameOver) {
            Font fonteGameOver = new Font("Arial", Font.BOLD, 48);
            g.setFont(fonteGameOver);
            g.setColor(Color.RED);
            g.drawString("Game Over", LARGURA_DA_JANELA / 2 - 100, ALTURA_DA_JANELA / 2);
        }
    }
    @Override
    public void drawPlayerLives(Graphics2D g) {
        int coracaoLargura = CORACAO_CHEIO.getIconWidth();
        int vidaCheia = levelModel.getPlayer().getVida();

        for (int i = 0; i < VIDA_INICIAL_PERSONAGEM; i++) {
            ImageIcon icone = (i < vidaCheia) ? CORACAO_CHEIO : CORACAO_VAZIO;
            g.drawImage(icone.getImage(), 10 + (i * coracaoLargura), 10, null);
        }
    }

    @Override
    public void spawnEnemies() {
        levelModel.setEnemiesCounter(levelModel.getEnemiesCounter() + 1);

        if (levelModel.getEnemiesCounter() >= DELAY_INIMIGOS) {
            Random rn = new Random();
            int posicaoEmX = rn.nextInt(LARGURA_DA_JANELA - 1 ) + 1;
            int posicaoEmY = rn.nextInt(ALTURA_DA_JANELA - 1) + 1;
            if (!(posicaoEmX == levelModel.getPlayer().getPosicaoEmX() && posicaoEmX == levelModel.getPlayer().getPosicaoEmY())) {
                Inimigo novoInimigo = new Inimigo(posicaoEmX, posicaoEmY, levelModel.getPlayer());
                novoInimigo.carregar();
                levelModel.getEnemies().add(novoInimigo);
            }
            levelModel.setEnemiesCounter(0);
        }
    }

    @Override
    public void checkItemCollision() {
        if (!(level.itemManager == null)) {
            for (Item item : level.itemManager.getItems()) {
                if (item.isVisivel() && levelModel.getPlayer().getRetangulo().intersects(item.getRetangulo())) {
                    System.out.println("Colisão com item: " + item.getClass().getSimpleName());
                    item.aplicarEfeito(levelModel.getPlayer());
                    item.setVisivel(false);
                }
            }
        }
    }

    @Override
    public void bulletsRemover() {
        Iterator<Tiro> iteratorTiro = levelModel.getPlayer().getTiros().iterator();

        while (iteratorTiro.hasNext()) {
            Tiro tiro = iteratorTiro.next();
            if (tiro.isVisivel() && tiro.verificarVisibilidade()) {
                tiro.atualizar();
            } else {
                iteratorTiro.remove();
                System.out.println("Tiro Removido");
            }
        }

        Iterator<SuperTiro> iteratorSuperTiro = levelModel.getPlayer().getSuperTiros().iterator();
        while (iteratorSuperTiro.hasNext()) {
            SuperTiro superTiro = iteratorSuperTiro.next();
            if (superTiro.isVisivel() && superTiro.verificarVisibilidade()) {
                superTiro.atualizar();
            } else {
                iteratorSuperTiro.remove();
                System.out.println("Tiro Removido");

            }
        }
    }

    @Override
    public void enemiesRemover() {
        Iterator<Inimigo> iteratorInimigo = levelModel.getEnemies().iterator();
        while (iteratorInimigo.hasNext()) {
            Inimigo inimigo = iteratorInimigo.next();
            if (inimigo.isVisivel()) {
                inimigo.atualizar();

                if (levelModel.getPlayer().getRetangulo().intersects(inimigo.getRetangulo())) {
                    System.out.println("Colisão com o personagem");
                    iteratorInimigo.remove(); // Remover o inimigo da lista
                    inimigo.setVisivel(false); // Marcar o inimigo como invisível
                    levelModel.getPlayer().sofrerDano(1);
                }

                for (Tiro tiro : levelModel.getPlayer().getTiros()) {
                    if (tiro.getRetangulo().intersects(inimigo.getRetangulo())) {
                        System.out.println("Colisão com o tiro");
                        // Aqui ele remove o tiro também.
                        inimigo.dropItem(level.itemManager);
                        levelModel.getPlayer().adicionarPontos(100);
                        levelModel.getPlayer().getTiros().remove(tiro);
                        inimigo.setVisivel(false);
                        iteratorInimigo.remove();
                        break;
                    }
                }

                int contHits = 0;
                for (Iterator<SuperTiro> iteratorSuperTiro2 = levelModel.getPlayer().getSuperTiros().iterator(); iteratorSuperTiro2.hasNext();) {
                    SuperTiro superTiro = iteratorSuperTiro2.next();
                    if (superTiro.getRetangulo().intersects(inimigo.getRetangulo())) {
                        System.out.println("Colisão com o tiro");
                        levelModel.getPlayer().adicionarPontos(100);
                        inimigo.dropItem(level.itemManager);
                        if (contHits == 3) {
                            iteratorSuperTiro2.remove();
                        }

                        inimigo.setVisivel(false);

                        break;
                    }
                }
            } else {
                iteratorInimigo.remove();
            }
        }
    }

    @Override
    public void InitializeAdditionalGraphics() {
        levelModel.setNuvens(new ArrayList<Cloud>());
        for (int i = 0; i < QTDE_DE_NUVENS; i++) {
            int x = (int) (Math.random() * LARGURA_DA_JANELA);
            int y = (int) (Math.random() * ALTURA_DA_JANELA);
            Cloud cloud = new Cloud(x, y);
            levelModel.getNuvens().add(cloud);
        }
    }
}
