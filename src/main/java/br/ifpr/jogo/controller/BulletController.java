package br.ifpr.jogo.controller;

import br.ifpr.jogo.dao.BulletDAO;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.serivces.bullet.BulletService;

import java.util.List;

public class BulletController {
    private Bullet bullet;
    private BulletDAO bulletDAO;
    private BulletService bulletService;

    public BulletController(Bullet bullet){
        this.setBullet(bullet);
        this.setBulletService(new BulletService(bullet));
        this.setBulletDAO(new BulletDAO());
    }

    public void load() {
        bulletService.load();
    }

    public void update() {
        bulletService.update();;
    }

    public boolean checkVisibility() {
        return bulletService.checkVisibility();
    }

    public List<Bullet> getAllBullets(int playerId) {
        return bulletDAO.getAllBullets(playerId);
    }
    public Bullet getBullet(Integer id) {
        return bulletDAO.getBullet(id);
    }
    public void saveOrUpdateBullet(Bullet bullet) {
        bulletDAO.saveOrUpdateBullet(bullet);
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public BulletDAO getBulletDAO() {
        return bulletDAO;
    }

    public void setBulletDAO(BulletDAO bulletDAO) {
        this.bulletDAO = bulletDAO;
    }

    public BulletService getBulletService() {
        return bulletService;
    }

    public void setBulletService(BulletService bulletService) {
        this.bulletService = bulletService;
    }
}