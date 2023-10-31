package br.ifpr.jogo.serivces.level;

import br.ifpr.jogo.model.sprites.SpriteFundo;

import javax.swing.*;
import java.awt.*;

import static br.ifpr.jogo.util.Constants.*;

public interface LevelService {
    public abstract void drawBullets(Graphics g);
    public abstract void drawEnemies(Graphics g);
    public abstract void drawItems(Graphics g);
    public abstract void drawClouds(Graphics g);
    public abstract void drawPoints(Graphics g);
    public abstract void drawShootDelay(Graphics g);
    public abstract void drawSpeed(Graphics g);
    public abstract void drawGameOver(Graphics g);
    public abstract void drawPlayerLives(Graphics2D g);
    public abstract void spawnEnemies();
    public abstract void checkItemCollision();
    public abstract void bulletsRemover();
    public abstract void enemiesRemover();
    public abstract void InitializeAdditionalGraphics();
}
