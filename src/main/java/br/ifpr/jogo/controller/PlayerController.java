package br.ifpr.jogo.controller;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.serivces.player.PlayerService;
import br.ifpr.jogo.serivces.player.PlayerServiceImpl;

import javax.swing.*;
import java.awt.event.KeyEvent;


public class PlayerController {
    public static final int VIDA_INICIAL_PERSONAGEM = 3;
    public static final int DELAY_INICIAL_TIRO = 500;
    public static final int VELOCIDADE_INICIAL_PERSONAGEM = 3;

    private PlayerService playerService;
    private Player player;

    public PlayerController(Player player) {
        this.setPlayerService(new PlayerServiceImpl(this));
        this.setPlayer(player);
    }

    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
        player.setBaseSprite(loading.getImage());
        player.setImageHeight(player.getBaseSprite().getWidth(null));
        player.setImageWidth(player.getBaseSprite().getHeight(null));
    }

    public void update() {
        player.setXPosition(player.getXPosition() + player.getXDisplacement());
        player.setYPosition(player.getYPosition() + player.getYDisplacement());
    }

    public void move(KeyEvent key) {
        playerService.move(key);
    }

    public void dash(KeyEvent key){
        playerService.dash(key);
    }

    public void stop(KeyEvent key) {
        playerService.stop(key);
    }

    public void shoot(KeyEvent key) {
        getPlayerService().shoot(key);
    }

    public void checkBoundsCollision() {
        playerService.checkBoundsCollision();
    }

    public void takeDamage(int damage, LevelController levelController) {
        playerService.takeDamage(damage, levelController);
    }

    public void addPoints(int quantity) {
        playerService.addPoints(quantity);
    }
    public PlayerService getPlayerService() {
        return playerService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}