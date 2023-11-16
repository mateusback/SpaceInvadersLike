package br.ifpr.jogo.serivces.level;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.level.Level;
import br.ifpr.jogo.model.level.LevelModel;
import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.item.Item;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static br.ifpr.jogo.util.ScreenConstants.*;

public class LevelServiceImpl implements LevelService{

    private static final ImageIcon CORACAO_CHEIO = new ImageIcon("src/main/resources/CoracaoCheio.png");
    private static final ImageIcon CORACAO_VAZIO = new ImageIcon("src/main/resources/CoracaoVazio.png");
    private static final int DELAY_INIMIGOS = 50;
    private static final int QTDE_DE_NUVENS = 7;
    private Level level;
    private LevelModel levelModel;

    public LevelServiceImpl(Level level, LevelModel levelModel){
        this.level = level;
        this.levelModel = levelModel;
    }

    @Override
    public void drawBullets(Graphics g) {
        ArrayList<Bullet> bullets = levelModel.getPlayer().getTiros();
        for (Bullet bullet : bullets) {
            bullet.load();
            g.drawImage(bullet.getBaseSprite(), bullet.getXPosition(), bullet.getYPosition(), null);
        }

        //SuperTiros
        ArrayList<SuperBullet> superBullets = levelModel.getPlayer().getSuperTiros();
        for (SuperBullet superBullet : superBullets) {
            superBullet.load();
            g.drawImage(superBullet.getBaseSprite(), superBullet.getXPosition(), superBullet.getYPosition(), null);
        }
    }

    @Override
    public void drawEnemies(Graphics g) {
        for (Enemy enemy : levelModel.getEnemies()) {
            g.drawImage(enemy.getBaseSprite(), enemy.getXPosition(), enemy.getYPosition(), null);
        }
    }

    @Override
    public void drawItems(Graphics g) {
        if (!(level.itemManager == null)) {
            for (Item item : level.itemManager.getItems()) {
                if (item.isVisible()) {
                    g.drawImage(item.getBaseSprite(), item.getXPosition(), item.getYPosition(), null);
                }
            }
        }
    }

    @Override
    public void drawClouds(Graphics g) {
        for (Cloud cloud : levelModel.getClouds()) {
            g.drawImage(cloud.getBaseSprite(), cloud.getXPosition(), cloud.getYPosition(), null);
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
        g.drawString("Delay Tiro: " + levelModel.getPlayer().getBulletDelay()/100, LARGURA_DA_JANELA - 300, 50);
    }

    @Override
    public void drawSpeed(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Velocidade: " + levelModel.getPlayer().getSpeed(), LARGURA_DA_JANELA - 300, 70);
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
        int vidaCheia = levelModel.getPlayer().getHitPoints();

        for (int i = 0; i < Player.VIDA_INICIAL_PERSONAGEM; i++) {
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
            if (!(posicaoEmX == levelModel.getPlayer().getXPosition() && posicaoEmX == levelModel.getPlayer().getYPosition())) {
                Enemy novoEnemy = new Enemy(posicaoEmX, posicaoEmY, levelModel.getPlayer());
                novoEnemy.load();
                levelModel.getEnemies().add(novoEnemy);
            }
            levelModel.setEnemiesCounter(0);
        }
    }

    @Override
    public void checkItemCollision() {
        if (!(level.itemManager == null)) {
            for (Item item : level.itemManager.getItems()) {
                if (item.isVisible() && levelModel.getPlayer().getRectangle().intersects(item.getRectangle())) {
                    System.out.println("Colisão com item: " + item.getClass().getSimpleName());
                    item.applyEffect(levelModel.getPlayer());
                    item.setVisible(false);
                }
            }
        }
    }

    @Override
    public void bulletsRemover() {
        Iterator<Bullet> iteratorTiro = levelModel.getPlayer().getTiros().iterator();

        while (iteratorTiro.hasNext()) {
            Bullet bullet = iteratorTiro.next();
            if (bullet.isVisible() && bullet.checkVisibility()) {
                bullet.update();
            } else {
                iteratorTiro.remove();
                System.out.println("Tiro Removido");
            }
        }

        Iterator<SuperBullet> iteratorSuperTiro = levelModel.getPlayer().getSuperTiros().iterator();
        while (iteratorSuperTiro.hasNext()) {
            SuperBullet superBullet = iteratorSuperTiro.next();
            if (superBullet.isVisible() && superBullet.verificarVisibilidade()) {
                superBullet.update();
            } else {
                iteratorSuperTiro.remove();
                System.out.println("Tiro Removido");

            }
        }
    }

    @Override
    public void enemiesRemover() {
        Iterator<Enemy> iteratorInimigo = levelModel.getEnemies().iterator();
        while (iteratorInimigo.hasNext()) {
            Enemy enemy = iteratorInimigo.next();
            if (enemy.isVisible()) {
                enemy.update();

                if (levelModel.getPlayer().getRectangle().intersects(enemy.getRectangle())) {
                    System.out.println("Colisão com o personagem");
                    iteratorInimigo.remove(); // Remover o inimigo da lista
                    enemy.setVisible(false); // Marcar o inimigo como invisível
                    levelModel.getPlayer().sofrerDano(1);
                }

                for (Bullet bullet : levelModel.getPlayer().getTiros()) {
                    if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                        System.out.println("Colisão com o tiro");
                        // Aqui ele remove o tiro também.
                        enemy.dropItem(level.itemManager);
                        levelModel.getPlayer().adicionarPontos(100);
                        levelModel.getPlayer().getTiros().remove(bullet);
                        enemy.setVisible(false);
                        iteratorInimigo.remove();
                        break;
                    }
                }

                int contHits = 0;
                for (Iterator<SuperBullet> iteratorSuperTiro2 = levelModel.getPlayer().getSuperTiros().iterator(); iteratorSuperTiro2.hasNext();) {
                    SuperBullet superBullet = iteratorSuperTiro2.next();
                    if (superBullet.getRectangle().intersects(enemy.getRectangle())) {
                        System.out.println("Colisão com o tiro");
                        levelModel.getPlayer().adicionarPontos(100);
                        enemy.dropItem(level.itemManager);
                        if (contHits == 3) {
                            iteratorSuperTiro2.remove();
                        }

                        enemy.setVisible(false);

                        break;
                    }
                }
            } else {
                iteratorInimigo.remove();
            }
        }
    }

    @Override
    public void initializeAdditionalGraphics() {
        levelModel.setClouds(new ArrayList<Cloud>());
        for (int i = 0; i < QTDE_DE_NUVENS; i++) {
            int x = (int) (Math.random() * LARGURA_DA_JANELA);
            int y = (int) (Math.random() * ALTURA_DA_JANELA);
            Cloud cloud = new Cloud(x, y);
            levelModel.getClouds().add(cloud);
        }
    }

    @Override
    public void checkGamePause(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pauseGame();
        }
    }

    @Override
    public void pauseGame() {
        level.gamePaused = !level.gamePaused;
        if (level.gamePaused) {
            levelModel.getPauseMenuPanel().setVisible(true);
        }
    }

    @Override
    public void initializePauseMenu() {
        levelModel.getPauseMenuPanel().setLayout(new BorderLayout());
        JButton botaoRetomar = new JButton("Retomar Jogo");
        levelModel.getPauseMenuPanel().add(botaoRetomar, BorderLayout.CENTER);
        JButton botaoSair = new JButton("Sair do Jogo");
        levelModel.getPauseMenuPanel().add(botaoSair, BorderLayout.SOUTH);
        levelModel.getPauseMenuPanel().setVisible(false);
        level.add(levelModel.getPauseMenuPanel());
    }

    @Override
    public void drawPauseMenu() {
        if (level.gamePaused) {
            int choice = JOptionPane.showOptionDialog(level,
                    "Jogo Pausado",
                    "Pause Menu",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Retomar jogo", "Salvar Jogo", "Sair"},
                    "Retomar Jogo");
            switch (choice) {
                case 0:
                    unpauseGame();
                    break;
                case 2:
                    quitGame();
                    break;
            }
        }
    }
    @Override
    public void unpauseGame() {
        level.gamePaused = false;
        levelModel.getPauseMenuPanel().setVisible(false);
    }
    @Override
    public void quitGame() {
        JOptionPane.showMessageDialog(level, "Saindo do Jogo");
        System.exit(0);
    }
}
