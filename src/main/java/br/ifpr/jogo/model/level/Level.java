package br.ifpr.jogo.model.level;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.ItemManager;
import br.ifpr.jogo.model.sprites.BackgroundSprite;
import br.ifpr.jogo.serivces.level.LevelServiceImpl;

public class Level extends JPanel implements KeyListener, ActionListener {
    public static final int DELAY_JOGO = 5;
    public static final boolean LOADED_GAME_START = true;
    public static final boolean NEW_GAME_START = false;
    private LevelModel levelModel;
    private LevelServiceImpl levelService;
    private Timer timer;
    private BackgroundSprite backgroundSprite;
    public ItemManager itemManager;
    public boolean gameOver = false;
    public boolean gamePaused;


    public Level(LevelModel levelModel) {
        this.levelModel = levelModel;
        this.levelService = new LevelServiceImpl(this, this.levelModel);
        levelService.levelInit(LOADED_GAME_START);
    }
    public Level() {
        levelModel = new LevelModel();
        this.levelService = new LevelServiceImpl(this, this.levelModel);
        levelService.levelInit(NEW_GAME_START);
    }

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
        levelModel.getPlayer().getPlayerService().shoot(e);
        levelModel.getPlayer().getPlayerService().move(e);
        levelModel.getPlayer().getPlayerService().dash(e);
    }

    // Puxa metodos para quando soltamos as teclas qu estavam pressionadas.
    @Override
    public void keyReleased(KeyEvent e) {
        levelModel.getPlayer().getPlayerService().stop(e);
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

        levelService.spawnEnemies();
        levelService.checkItemCollision();
        levelService.bulletsRemover();
        levelService.enemiesRemover();

        backgroundSprite.updateOffset(player);

        player.getPlayerService().checkBoundsCollision();

        repaint();

    }

    public LevelModel getLevelModel() {
        return levelModel;
    }

    public void setLevelModel(LevelModel levelModel) {
        this.levelModel = levelModel;
    }

    public LevelServiceImpl getLevelService() {
        return levelService;
    }

    public void setLevelService(LevelServiceImpl levelService) {
        this.levelService = levelService;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public BackgroundSprite getBackgroundSprite() {
        return backgroundSprite;
    }

    public void setBackgroundSprite(BackgroundSprite backgroundSprite) {
        this.backgroundSprite = backgroundSprite;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }
}