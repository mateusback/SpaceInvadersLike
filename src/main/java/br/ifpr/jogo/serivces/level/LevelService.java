package br.ifpr.jogo.serivces.level;

import br.ifpr.jogo.model.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public interface LevelService {
    void drawBullets(Graphics g);
    void drawEnemies(Graphics g);
    void drawItems(Graphics g);
    void drawClouds(Graphics g);
    void drawPoints(Graphics g);
    void drawShootDelay(Graphics g);
    void drawSpeed(Graphics g);
    void drawGameOver(Graphics g);
    void drawPlayerLives(Graphics2D g);
    void spawnEnemies();
    void checkItemCollision();
    void bulletsRemover();
    void enemiesRemover();
    void initializeAdditionalGraphics();
    void checkGamePause(KeyEvent e);
    void pauseGame();
    void initializePauseMenu();
    void drawPauseMenu();
    void unpauseGame();
    void quitGame();
}
