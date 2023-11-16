package br.ifpr.jogo.model.level;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemManager;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.model.sprites.BackgroundSprite;
import br.ifpr.jogo.serivces.level.LevelServiceImpl;

public class Level extends JPanel implements KeyListener, ActionListener {
    public static final int DELAY_JOGO = 5;
    private LevelModel levelModel;
    private LevelServiceImpl levelService;
    private Timer timer;
    private BackgroundSprite backgroundSprite;
    public ItemManager itemManager;
    public boolean gameOver = false;
    public boolean gamePaused;


    // Construtor da fase
    public Level() {
        levelModel = new LevelModel();
        levelService = new LevelServiceImpl(this, this.levelModel);
        setFocusable(true);
        setDoubleBuffered(true);
        // Instanciando o teclado.
        addKeyListener(this);
        backgroundSprite = new BackgroundSprite();
        // Istanciando o jogador e carregando sua imagem.
        levelModel.setPlayer(new Player());
        levelModel.getPlayer().load();

        levelService.initializeAdditionalGraphics();
        // Definindo a velocidade do jogo.
        timer = new Timer(DELAY_JOGO, this);
        timer.start();

        //Renomear
        itemManager = new ItemManager();
        // Adicionando inimigos
        levelModel.setEnemies(new ArrayList<>());
        levelService.initializePauseMenu();
    }

    // Metodo utilizado para desenhar os elementos no painel.
    // O fundo está sendo desenhado e da mesma forma o jogador.
    // Já o os tiros, por serem vários, estão sendo instanciados
    // como forma de array.
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Player player = levelModel.getPlayer();
        super.paintComponent(g);
        backgroundSprite.loadLevel1(g);

        graphics2D.drawImage(player.getBaseSprite(), player.getXPosition(), player.getYPosition(), null);


        levelService.drawBullets(graphics2D);
        levelService.drawEnemies(graphics2D);
        levelService.drawItems(graphics2D);
        levelService.drawClouds(graphics2D);
        levelService.drawPlayerLives(graphics2D);
        levelService.drawGameOver(graphics2D);
        levelService.drawPoints(graphics2D);
        levelService.drawSpeed(graphics2D);
        levelService.drawShootDelay(graphics2D);


        repaint();
        revalidate();
    }

    // Puxa metodos para quando as teclas são pressionadas.
    @Override
    public void keyPressed(KeyEvent e) {
        if(gameOver){
            return;
        }
        if(gamePaused){
            return;
        }
        levelService.checkGamePause(e);
        levelModel.getPlayer().shoot(e);
        levelModel.getPlayer().mover(e);
    }

    // Puxa metodos para quando soltamos as teclas qu estavam pressionadas.
    @Override
    public void keyReleased(KeyEvent e) {
        levelModel.getPlayer().stop(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // vazio
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = levelModel.getPlayer();
        if(gameOver){
            return;
        }
        if(gamePaused){
            return;
        }
        player.update();
        this.backgroundSprite.updateOffset(player);
        for (Cloud cloud : levelModel.getClouds()) {
            cloud.update();
        }
        List<SuperBullet> superBullets = player.getSuperTiros();
        List<Bullet> bullets = player.getTiros();

        levelService.spawnEnemies();
        levelService.checkItemCollision();
        levelService.bulletsRemover();
        levelService.enemiesRemover();

        backgroundSprite.updateOffset(player);
        player.checkBoundsCollision();

        if (player.getHitPoints() <= 0) {
            gameOver = true;
            return;
        }
        repaint();

    }

}