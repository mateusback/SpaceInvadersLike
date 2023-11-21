package br.ifpr.jogo.controller;

import br.ifpr.jogo.dao.EnemyDAO;
import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.item.*;

import javax.swing.*;
import java.util.Random;


public class EnemyService {
    public static final int VELOCIDADE_INIMIGO = 2;
    private EnemyDAO enemyDAO;
    private Enemy enemy;

    public EnemyService(Enemy enemy) {
        this.setEnemy(enemy);
        this.load();
    }
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Inimigo.png"));
        enemy.setBaseSprite(loading.getImage());
        enemy.setImageHeight(enemy.getBaseSprite().getWidth(null));
        enemy.setImageWidth(enemy.getBaseSprite().getHeight(null));
    }

    public void update() {
        int deltaX = enemy.getPlayer().getXPosition() - enemy.getXPosition();
        int deltaY = enemy.getPlayer().getYPosition() - enemy.getYPosition();

        if (deltaX > 0) {
            enemy.setXPosition(enemy.getXPosition() + VELOCIDADE_INIMIGO);
        } else if (deltaX < 0) {
            enemy.setXPosition(enemy.getXPosition() - VELOCIDADE_INIMIGO);
        }

        if (deltaY > 0) {
            enemy.setYPosition(enemy.getYPosition() + VELOCIDADE_INIMIGO);
        } else if (deltaY < 0) {
            enemy.setYPosition(enemy.getYPosition() - VELOCIDADE_INIMIGO);
        }
    }

    public void dropItem(ItemManager itemManager) {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance <= 25) { 
            Item itemDropado;
            int tipoItem = rand.nextInt(3) + 1;
            if (tipoItem == 1) {
                itemDropado = new ItemAttackSpeed(enemy.getXPosition(), enemy.getYPosition());
            } else if(tipoItem == 2) {
                itemDropado = new ItemSpeed(enemy.getXPosition(), enemy.getYPosition());
            } else{
                itemDropado = new ItemHeal(enemy.getXPosition(), enemy.getYPosition());
            }
            itemManager.addItem(itemDropado);
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

}