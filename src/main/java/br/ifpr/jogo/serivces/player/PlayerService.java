package br.ifpr.jogo.serivces.player;

import br.ifpr.jogo.model.graphicelement.item.Item;

import java.awt.event.KeyEvent;

public interface PlayerService {
    void move(KeyEvent key);
    void stop(KeyEvent key);
    void shoot(KeyEvent key);
    void checkBoundsCollision();
    void equipItem(Item item);
    void takeDamage();
}
