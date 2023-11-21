package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.Cloud;

import javax.swing.*;

import static br.ifpr.jogo.util.ScreenConstants.ALTURA_DA_JANELA;
import static br.ifpr.jogo.util.ScreenConstants.LARGURA_DA_JANELA;

public class CloudController extends GraphicElementController {
    private static int SPEED = 3;

    private Cloud cloud;

    public CloudController(Cloud cloud){
        this.setCloud(cloud);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Nuvem.png"));
        cloud.setBaseSprite(loading.getImage());
    }

    @Override
    public void update() {
        if (cloud.getXPosition() > LARGURA_DA_JANELA) {
            int y = (int) (Math.random() * ALTURA_DA_JANELA);
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
