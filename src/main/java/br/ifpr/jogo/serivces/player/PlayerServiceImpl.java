package br.ifpr.jogo.serivces.player;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.controller.PlayerController;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.controller.LevelController;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static br.ifpr.jogo.util.ScreenConstants.*;

public class PlayerServiceImpl implements PlayerService{
    private PlayerController playerController;
    private boolean keysReleased[] = new boolean[4];
    public PlayerServiceImpl(PlayerController playerController){
        this.playerController = playerController;
    }
    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
        playerController.getPlayer().setBaseSprite(loading.getImage());
        playerController.getPlayer().setImageHeight(playerController.getPlayer().getBaseSprite().getWidth(null));
        playerController.getPlayer().setImageWidth(playerController.getPlayer().getBaseSprite().getHeight(null));
    }

    @Override
    public void update() {
        playerController.getPlayer().setXPosition(playerController.getPlayer().getXPosition() + playerController.getPlayer().getXDisplacement());
        playerController.getPlayer().setYPosition(playerController.getPlayer().getYPosition() + playerController.getPlayer().getYDisplacement());
    }

    @Override
    public void move(KeyEvent key) {
        Player player =  playerController.getPlayer();
        int code = key.getKeyCode();

        if (code == KeyEvent.VK_W) {
            player.setYDisplacement(player.getYDisplacement() - player.getSpeed());
            player.setDirection("top");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Cima.gif"));
            player.setBaseSprite(loading.getImage());
            this.keysReleased[0] = false;
        }

        if (code == KeyEvent.VK_S) {
            player.setYDisplacement(player.getSpeed());
            player.setDirection("bottom");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Baixo.gif"));
            player.setBaseSprite(loading.getImage());
            this.keysReleased[1] = false;
        }

        if (code == KeyEvent.VK_A) {
            player.setXDisplacement(player.getXDisplacement() - player.getSpeed());
            player.setDirection("left");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Esquerda.gif"));
            player.setBaseSprite(loading.getImage());
            this.keysReleased[2] = false;

        }
        if (code == KeyEvent.VK_D) {
            player.setXDisplacement(player.getSpeed());
            player.setDirection("right");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Direita.gif"));
            player.setBaseSprite(loading.getImage());
            this.keysReleased[3] = false;
        }
    }

    @Override
    public void dash(KeyEvent key){
        Player player =  playerController.getPlayer();
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_SPACE && player.getDirection() == "left") {
            player.setXPosition(player.getXPosition() - 100);
        }
        if (code == KeyEvent.VK_SPACE && player.getDirection() == "right") {
            player.setXPosition(player.getXPosition() + 100);
        }
        if (code == KeyEvent.VK_SPACE && player.getDirection() == "top") {
            player.setYPosition(player.getYPosition() - 100);
        }
        if (code == KeyEvent.VK_SPACE && player.getDirection() == "bottom") {
            player.setYPosition(player.getYPosition() + 100);
        }
    }

    @Override
    public void stop(KeyEvent key) {
        Player player =  playerController.getPlayer();
            int code = key.getKeyCode();
            if (code == KeyEvent.VK_W) {
                player.setYDisplacement(0);
                keysReleased[0] = true;
            }
            if (code == KeyEvent.VK_S) {
                player.setYDisplacement(0);
                keysReleased[1] = true;
            }
            if (code == KeyEvent.VK_A) {
                player.setXDisplacement(0);
                keysReleased[2] = true;
            }
            if (code == KeyEvent.VK_D) {
                player.setXDisplacement(0);
                keysReleased[3] = true;
            }

            if (keysReleased[0] == true && keysReleased[1] == true && keysReleased[2] == true && keysReleased[3] == true) {
                ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
                player.setBaseSprite(loading.getImage());
            }
    }

    @Override
    public void shoot(KeyEvent key) {
        Player player =  playerController.getPlayer();
        player.setTimeMark(System.currentTimeMillis());
        if (player.getTimeMark() - player.getLastShootMark() < player.getBulletDelay()) {
            return;
        }

        int playerCenterX = player.getXPosition() + (player.getImageWidth() / 2);
        int playerCenterY = player.getYPosition() + (player.getImageHeight() / 2);

        if (key.getKeyCode() == KeyEvent.VK_RIGHT || key.getKeyCode() == KeyEvent.VK_L) {
            Bullet bullet = new Bullet(player.getXPosition(), playerCenterY, "right", player);
            player.getBullets().add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_I) {
            Bullet bullet = new Bullet(player.getXPosition(), (playerCenterY - player.getImageHeight()), "top", player);
            player.getBullets().add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT || key.getKeyCode() == KeyEvent.VK_J) {
            Bullet bullet = new Bullet((playerCenterX - player.getImageWidth()), playerCenterY, "left", player);
            player.getBullets().add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_K) {
            Bullet bullet = new Bullet(player.getXPosition(), playerCenterY, "bottom", player);
            player.getBullets().add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_F && player.getScore() >= 500) {
            SuperBullet superBullet = new SuperBullet(playerCenterX, playerCenterY, player);
            player.getSuperBullets().add(superBullet);
            player.setScore(player.getScore() - 500);
        }
        player.setLastShootMark(player.getTimeMark());
    }

    @Override
    public void checkBoundsCollision() {
        Player player =  playerController.getPlayer();
        if (player.getXPosition() < 0) {
            player.setXPosition(0);
        } else if (player.getXPosition() + player.getImageWidth() > WINDOW_WIDTH) {
            int xMaximum = WINDOW_WIDTH - player.getImageWidth();
            player.setXPosition(xMaximum);
        }

        if (player.getYPosition() < 0) {
            player.setYPosition(0);
        } else if (player.getYPosition() + player.getImageHeight() >= WINDOW_HEIGHT - PIXELS_SCREEN_HEIGHT_ADJUSTMENT) {
            int yMaximum = WINDOW_HEIGHT - PIXELS_SCREEN_HEIGHT_ADJUSTMENT - player.getImageHeight();
            player.setYPosition(yMaximum);
        }
    }

    @Override
    public void takeDamage(int damage, LevelController levelController) {
        playerController.getPlayer().setHitPoints(playerController.getPlayer().getHitPoints() - damage);
        if (playerController.getPlayer().getHitPoints() <= 0) {
            levelController.gameOver = true;
        }
    }

    @Override
    public void addPoints(int quantity) {
        playerController.getPlayer().setScore(playerController.getPlayer().getScore()+ quantity); ;
    }
}
