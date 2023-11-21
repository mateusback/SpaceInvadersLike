package br.ifpr.jogo.controller;

import br.ifpr.jogo.dao.EnemyDAO;
import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.model.graphicelement.item.*;
import br.ifpr.jogo.serivces.enemy.EnemyService;


public class EnemyController{
    public static final int VELOCIDADE_INIMIGO = 2;
    private EnemyDAO enemyDAO;
    private EnemyService enemyService;
    private Enemy enemy;

    public EnemyController(Enemy enemy) {
        this.setEnemy(enemy);
        this.setEnemyService(new EnemyService(enemy));
        this.setEnemyDAO(new EnemyDAO());
    }

    public void load() {
        enemyService.load();
    }

    public void update() {
        enemyService.update();
    }

    public void dropItem(ItemManager itemManager) {
        enemyService.dropItem(itemManager);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public EnemyDAO getEnemyDAO() {
        return enemyDAO;
    }

    public void setEnemyDAO(EnemyDAO enemyDAO) {
        this.enemyDAO = enemyDAO;
    }

    public EnemyService getEnemyService() {
        return enemyService;
    }

    public void setEnemyService(EnemyService enemyService) {
        this.enemyService = enemyService;
    }
}