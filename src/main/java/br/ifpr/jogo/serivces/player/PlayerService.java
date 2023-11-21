package br.ifpr.jogo.serivces.player;

import br.ifpr.jogo.model.level.Level;

import java.awt.event.KeyEvent;

public interface PlayerService {
    void move(KeyEvent key);
    void dash(KeyEvent key);
    void stop(KeyEvent key);
    void shoot(KeyEvent key);
    void checkBoundsCollision();
    void takeDamage(int damage, Level level);
    void addPoints(int quantity);
}
