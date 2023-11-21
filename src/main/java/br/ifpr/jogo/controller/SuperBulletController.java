package br.ifpr.jogo.controller;


import br.ifpr.jogo.dao.SuperBulletDAO;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.serivces.bullet.SuperBulletService;

import java.util.List;

public class SuperBulletController {
    private SuperBullet superBullet;
    private SuperBulletService superBulletService;
    private SuperBulletDAO superBulletDAO;

    public SuperBulletController(SuperBullet superBullet) {
        this.setSuperBullet(superBullet);
        this.superBulletService = new SuperBulletService(superBullet);
        superBulletDAO = new SuperBulletDAO();
    }

    public void load() {
        superBulletService.load();
    }

    public void update() {
        superBulletService.update();
    }

    public boolean checkVisibility() {
        return superBulletService.checkVisibility();
    }

    public SuperBullet getSuperBullet() {
        return superBullet;
    }

    public void setSuperBullet(SuperBullet superBullet) {
        this.superBullet = superBullet;
    }

    public void saveOrUpdateSuperBullet(SuperBullet superBullet) {
        superBulletDAO.saveOrUpdateSuperBullet(superBullet);
    }

    public SuperBullet getSuperBullet(Integer id) {
        return superBulletDAO.getSuperBullet(id);
    }

    public List<SuperBullet> getAllSuperBullets(int playerId) {
        return superBulletDAO.getAllSuperBullets(playerId);
    }
}