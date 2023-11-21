package br.ifpr.jogo.serivces.level;

import br.ifpr.jogo.dao.*;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.item.ItemManager;
import br.ifpr.jogo.model.level.Level;
import br.ifpr.jogo.model.level.LevelModel;
import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.item.Item;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.model.sprites.BackgroundSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static br.ifpr.jogo.util.ScreenConstants.*;

public class LevelServiceImpl implements LevelService{

    private static final ImageIcon FULL_HEART = new ImageIcon("src/main/resources/CoracaoCheio.png");
    private static final ImageIcon EMPTY_HEART = new ImageIcon("src/main/resources/CoracaoVazio.png");
    private static final int ENEMIES_DELAY = 50;
    private static final int CLOUDS_QUANTITY = 7;
    private Level level;
    private LevelModel levelModel;

    public LevelServiceImpl(Level level, LevelModel levelModel){
        this.level = level;
        this.levelModel = levelModel;
    }

    @Override
    public void drawBullets(Graphics g) {
        java.util.List<Bullet> bullets = levelModel.getPlayer().getBullets();
        for (Bullet bullet : bullets) {
            bullet.load();
            g.drawImage(bullet.getBaseSprite(), bullet.getXPosition(), bullet.getYPosition(), null);
        }

        //SuperTiros
        java.util.List<SuperBullet> superBullets = levelModel.getPlayer().getSuperBullets();
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
        g.drawString("Pontuação: " + levelModel.getPlayer().getScore(), LARGURA_DA_JANELA - 300, 30);
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
        int coracaoLargura = FULL_HEART.getIconWidth();
        int vidaCheia = levelModel.getPlayer().getHitPoints();

        for (int i = 0; i < Player.VIDA_INICIAL_PERSONAGEM; i++) {
            ImageIcon icone = (i < vidaCheia) ? FULL_HEART : EMPTY_HEART;
            g.drawImage(icone.getImage(), 10 + (i * coracaoLargura), 10, null);
        }
    }

    @Override
    public void spawnEnemies() {
        levelModel.setEnemiesCounter(levelModel.getEnemiesCounter() + 1);

        if (levelModel.getEnemiesCounter() >= ENEMIES_DELAY) {
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
        Iterator<Bullet> iteratorTiro = levelModel.getPlayer().getBullets().iterator();

        while (iteratorTiro.hasNext()) {
            Bullet bullet = iteratorTiro.next();
            bullet.setVisible(bullet.checkVisibility());
            if (bullet.isVisible()) {
                bullet.update();
            } else {
                iteratorTiro.remove();
                System.out.println("Tiro Removido");
            }
        }

        Iterator<SuperBullet> iteratorSuperTiro = levelModel.getPlayer().getSuperBullets().iterator();
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
                    levelModel.getPlayer().getPlayerService().takeDamage(1, level);
                }

                for (Bullet bullet : levelModel.getPlayer().getBullets()) {
                    if (bullet.getRectangle().intersects(enemy.getRectangle())) {
                        System.out.println("Colisão com o tiro");
                        // Aqui ele remove o tiro também.
                        enemy.dropItem(level.itemManager);
                        levelModel.getPlayer().setScore(levelModel.getPlayer().getScore() + 100);
                        levelModel.getPlayer().getBullets().remove(bullet);
                        enemy.setVisible(false);
                        iteratorInimigo.remove();
                        break;
                    }
                }

                int contHits = 0;
                for (Iterator<SuperBullet> iteratorSuperTiro2 = levelModel.getPlayer().getSuperBullets().iterator(); iteratorSuperTiro2.hasNext();) {
                    SuperBullet superBullet = iteratorSuperTiro2.next();
                    if (superBullet.getRectangle().intersects(enemy.getRectangle())) {
                        System.out.println("Colisão com o tiro");
                        levelModel.getPlayer().setScore(levelModel.getPlayer().getScore() + 100);
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
        for (int i = 0; i < CLOUDS_QUANTITY; i++) {
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

        JButton resumeBtn = new JButton("Retomar Jogo");
        resumeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                unpauseGame();
                System.out.println("Botão Retomar Jogo pressionado");
            }
        });

        JButton saveBtn = new JButton("Salvar Jogo");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
                System.out.println("Botão Salvar Jogo pressionado");
            }
        });

        JButton quitBtn = new JButton("Sair do Jogo");
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitGame();
            }
        });

        levelModel.getPauseMenuPanel().add(resumeBtn, BorderLayout.CENTER);
        levelModel.getPauseMenuPanel().add(quitBtn, BorderLayout.SOUTH);
        levelModel.getPauseMenuPanel().add(saveBtn, BorderLayout.NORTH);
        levelModel.getPauseMenuPanel().setVisible(false);
        level.add(levelModel.getPauseMenuPanel());
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

    @Override
    public void saveGame() {
        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.saveOrUpdatePlayer(levelModel.getPlayer());

        EnemyDAO enemyDAO = new EnemyDAO();
        for (Enemy enemy : levelModel.getEnemies()) {
            enemyDAO.saveOrUpdateEnemy(enemy);
        }

        CloudDAO cloudDAO = new CloudDAO();
        for (Cloud cloud : levelModel.getClouds()) {
            cloudDAO.saveOrUpdateCloud(cloud);
        }

        BulletDAO bulletDAO = new BulletDAO();
        for (Bullet bullet : levelModel.getPlayer().getBullets()) {
            bulletDAO.saveOrUpdateBullet(bullet);
        }

        SuperBulletDAO superBulletDAO = new SuperBulletDAO();
        for (SuperBullet superBullet : levelModel.getPlayer().getSuperBullets()) {
            superBulletDAO.saveOrUpdateSuperBullet(superBullet);
        }

        JOptionPane.showMessageDialog(level, "Jogo salvo com sucesso!");
        unpauseGame();
    }

    public void levelInit(boolean gameState){
        level.setFocusable(true);
        level.setDoubleBuffered(true);
        level.addKeyListener(level);
        level.setBackgroundSprite(new BackgroundSprite());
        if (gameState){
            levelModel.setPlayer(levelModel.getPlayer());
            levelModel.getPlayer().load();
            level.getLevelService().initializeAdditionalGraphics();
            levelModel.setEnemies(levelModel.getEnemies());
            level.getLevelService().initializePauseMenu();
        }else {
            levelModel.setPlayer(new Player());
            levelModel.getPlayer().load();
            level.getLevelService().initializeAdditionalGraphics();
            levelModel.setEnemies(new ArrayList<>());
            level.getLevelService().initializePauseMenu();
        }
        level.setTimer(new Timer(level.DELAY_JOGO, level));
        level.getTimer().start();
        level.setItemManager(new ItemManager());
    }
}
