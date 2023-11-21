package br.ifpr.jogo.serivces.cloud;

import br.ifpr.jogo.model.graphicelement.Cloud;

import javax.persistence.Transient;
import javax.swing.*;

import static br.ifpr.jogo.util.ScreenConstants.*;

public class CloudService {
    private static final int SPEED = 3;
    @Transient
    private Cloud cloud;

    public CloudService(Cloud cloud){
        this.setCloud(cloud);
        this.load();
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Nuvem.png"));
        cloud.setBaseSprite(loading.getImage());
    }

    public void update() {
        if (cloud.getXPosition() > WINDOW_WIDTH) {
            int y = (int) (Math.random() * WINDOW_HEIGHT);
            cloud.setXPosition(-cloud.getBaseSprite().getWidth(null));
            cloud.setYPosition(y);
        } else {
            cloud.setXPosition(cloud.getXPosition() + SPEED);
        }
    }
    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }
}
