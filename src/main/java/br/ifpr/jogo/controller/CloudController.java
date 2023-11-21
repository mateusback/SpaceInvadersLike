package br.ifpr.jogo.controller;

import br.ifpr.jogo.dao.CloudDAO;
import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.serivces.cloud.CloudService;

import java.util.List;

public class CloudController {
    private CloudDAO cloudDAO;
    private Cloud cloud;
    private CloudService cloudService;

    public CloudController(Cloud cloud){
        this.setCloud(cloud);
        this.setCloudService(new CloudService(cloud));
        this.cloudDAO = new CloudDAO();
    }

    public void load() {
        cloudService.load();
    }

    public void update() {
        cloudService.update();
    }

    public List<Cloud> getAllClouds() {
        return cloudDAO.getAllClouds();
    }
    public Cloud getBullet(Integer id) {
        return cloudDAO.getCloud(id);
    }
    public void saveOrUpdateCloud(Cloud cloud) {
        cloudDAO.saveOrUpdateCloud(cloud);
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public CloudService getCloudService() {
        return cloudService;
    }

    public void setCloudService(CloudService cloudService) {
        this.cloudService = cloudService;
    }
}
