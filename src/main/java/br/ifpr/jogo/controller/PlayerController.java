package br.ifpr.jogo.controller;

import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.PlayerDAO;
import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.serivces.player.PlayerService;
import br.ifpr.jogo.serivces.player.PlayerServiceImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;


public class PlayerController {
    public static final int VIDA_INICIAL_PERSONAGEM = 3;
    public static final int DELAY_INICIAL_TIRO = 500;
    public static final int VELOCIDADE_INICIAL_PERSONAGEM = 3;

    private PlayerService playerService;
    private Player player;
    private PlayerDAO playerDAO;

    public PlayerController(Player player) {
        this.setPlayerService(new PlayerServiceImpl(this));
        this.setPlayer(player);
        this.playerDAO = new PlayerDAO();
    }

    public void load() {
        playerService.load();
    }

    public void update() {
        playerService.update();
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

    public void saveOrUpdatePlayer(Player player) {
        playerDAO.saveOrUpdatePlayer(player);
    }

    public Player getPlayer(Integer id) {
        return playerDAO.getPlayer(id);
    }
    public List<Integer> getAvailableSaveIds() {
        return playerDAO.getAvailableSaveIds();
    }
}