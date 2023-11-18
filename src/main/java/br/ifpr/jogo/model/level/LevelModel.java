package br.ifpr.jogo.model.level;

import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.Player;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LevelModel {
    @Transient
    private Player player;
    @Transient
    private List<Enemy> enemies;
    @Column(name="contador_inimigos")
    private int enemiesCounter;
    @Transient
    private ArrayList<Cloud> clouds;
    private JPanel pauseMenuPanel = new JPanel();

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
    public ArrayList<Cloud> getClouds() {
        return clouds;
    }
    public void setClouds(ArrayList<Cloud> clouds) {
        this.clouds = clouds;
    }

    public JPanel getPauseMenuPanel() {
        return pauseMenuPanel;
    }
    public void setPauseMenuPanel(JPanel pauseMenuPanel) {
        this.pauseMenuPanel = pauseMenuPanel;
    }
}
