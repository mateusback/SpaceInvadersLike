package br.ifpr.jogo.model.level;

import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.swing.*;
import java.util.List;

public class LevelModel {
    @Transient
    private Player player;
    @Transient
    private List<Enemy> enemies;
    @Column(name="contador_inimigos")
    private int enemiesCounter;
    @Transient
    private List<Cloud> clouds;
    @Transient
    private JPanel pauseMenuPanel = new JPanel();

    public LevelModel(Player loadedPlayer, List<Enemy> loadedEnemies, List<Cloud> loadedClouds, List<Bullet> loadedBullets, List<SuperBullet> loadedSuperBullets){
        this.player = loadedPlayer;
        this.enemies = loadedEnemies;
        this.clouds = loadedClouds;
        this.player.setBullets(loadedBullets);
        this.player.setSuperBullets(loadedSuperBullets);
    }
    public LevelModel(){

    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    public int getEnemiesCounter() {
        return enemiesCounter;
    }
    public void setEnemiesCounter(int enemiesCounter) {
        this.enemiesCounter = enemiesCounter;
    }
    public List<Cloud> getClouds() {
        return clouds;
    }
    public void setClouds(List<Cloud> clouds) {
        this.clouds = clouds;
    }
    public JPanel getPauseMenuPanel() {
        return pauseMenuPanel;
    }
    public void setPauseMenuPanel(JPanel pauseMenuPanel) {
        this.pauseMenuPanel = pauseMenuPanel;
    }
}
