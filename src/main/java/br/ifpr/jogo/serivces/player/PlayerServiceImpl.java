package br.ifpr.jogo.serivces.player;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.model.graphicelement.item.Item;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PlayerServiceImpl implements PlayerService{
    private Player player;

    public PlayerServiceImpl(Player player){
        this.player = player;
    }
    @Override
    public void move(KeyEvent key) {
        int code = key.getKeyCode();

        if (code == KeyEvent.VK_W) {
            player.setYDisplacement(player.getYDisplacement() - player.getSpeed());
            player.setDirection("top");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Cima.gif"));
            player.setBaseSprite(loading.getImage());
            player.setKeysReleased([false]);
        }

        if (code == KeyEvent.VK_S) {
            player.setYDisplacement(player.getSpeed());
            player.setDirection("bottom");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Baixo.gif"));
            player.setBaseSprite(loading.getImage());
            keysReleased[1] = false;
        }

        if (code == KeyEvent.VK_A) {
            player.setXDisplacement(player.getXDisplacement() - player.getSpeed());
            player.setDirection("left");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Esquerda.gif"));
            player.setBaseSprite(loading.getImage());
            keysReleased[2] = false;

        }
        if (code == KeyEvent.VK_D) {
            player.setXDisplacement(player.getSpeed());
            player.setDirection("right");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Direita.gif"));
            player.setBaseSprite(loading.getImage());
            keysReleased[3] = false;
        }

        // Dash
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

    }

    @Override
    public void shoot(KeyEvent key) {

    }

    @Override
    public void checkBoundsCollision() {

    }

    @Override
    public void equipItem(Item item) {

    }

    @Override
    public void takeDamage() {

    }
}
