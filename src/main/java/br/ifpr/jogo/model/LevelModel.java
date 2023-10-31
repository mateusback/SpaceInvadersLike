package br.ifpr.jogo.model;

import br.ifpr.jogo.model.elementosgraficos.Cloud;
import br.ifpr.jogo.model.elementosgraficos.Inimigo;
import br.ifpr.jogo.model.elementosgraficos.Player;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

public class LevelModel {
    @Transient
    private Player player;
    @Transient
    private List<Inimigo> enemies;

    @Column(name="contador_inimigos")
    private int enemiesCounter;
    @Transient
    private ArrayList<Cloud> clouds;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Inimigo> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Inimigo> enemies) {
        this.enemies = enemies;
    }


    public int getEnemiesCounter() {
        return enemiesCounter;
    }

    public void setEnemiesCounter(int enemiesCounter) {
        this.enemiesCounter = enemiesCounter;
    }

    public ArrayList<Cloud> getNuvens() {
        return clouds;
    }

    public void setNuvens(ArrayList<Cloud> clouds) {
        this.clouds = clouds;
    }

}
