package br.ifpr.jogo.model.graphicelement;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.item.Item;
import br.ifpr.jogo.model.graphicelement.tiros.Bullet;
import br.ifpr.jogo.model.graphicelement.tiros.SuperBullet;
import br.ifpr.jogo.model.sprites.BulletSprite;
import jdk.jfr.Name;

import static br.ifpr.jogo.util.Constants.*;

//
@Entity
@Table(name = "tb_personagem")
public class Player extends GraphicElement {
    @Transient
    private ArrayList<Bullet> bullets;
    @Transient
    private ArrayList<SuperBullet> superBullets;
    @Column
    @Name("tempo_ultimo_tiro")
    private long tempoUltimoTiro;
    @Column
    @Name("delay_tiro")
    private long bulletDelay;
    @Transient
    private Item itemEquipado;
    @Transient
    private boolean keysReleased[] = new boolean[4];
    @Column(name="tempo_atual")
    private long tempoAtual;
    @Column(name="pontuacao")
    private int pontos;

    public Player() {
        super.setXPosition(POSICAO_INICIAL_EM_X_PERSONAGEM);
        super.setYPosition(POSICAO_INICIAL_EM_Y_PERSONAGEM);
        this.bullets = new ArrayList<Bullet>();
        this.superBullets = new ArrayList<SuperBullet>();
        this.bulletDelay = DELAY_INICIAL_TIRO;
        super.setSpeed(VELOCIDADE_INICIAL_PERSONAGEM);
        super.setHitPoints(VIDA_INICIAL_PERSONAGEM);
        this.pontos = 0;
    }

    @Override
    public void load() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
        super.setBaseSprite(carregando.getImage());
        super.setImageHeight(super.getBaseSprite().getWidth(null));
        super.setImageWidth(super.getBaseSprite().getHeight(null));
    }

    @Override
    public void update() {
        super.setXPosition(super.getXPosition() + super.getXDisplacement());
        super.setYPosition(super.getYPosition() + super.getYDisplacement());
    }

    public void mover(KeyEvent key) {
        int code = key.getKeyCode();

        if (code == KeyEvent.VK_W) {
            super.setYDisplacement(super.getYDisplacement() - super.getSpeed());
            super.setDirection("top");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Cima.gif"));
            super.setBaseSprite(loading.getImage());
            keysReleased[0] = false;
        }

        if (code == KeyEvent.VK_S) {
            super.setYDisplacement(super.getSpeed());
            this.setDirection("bottom");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Baixo.gif"));
            super.setBaseSprite(loading.getImage());
            keysReleased[1] = false;
        }

        if (code == KeyEvent.VK_A) {
            super.setXDisplacement(super.getXDisplacement() - super.getSpeed());
            this.setDirection("left");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Esquerda.gif"));
            super.setBaseSprite(loading.getImage());
            keysReleased[2] = false;

        }
        if (code == KeyEvent.VK_D) {
            super.setXDisplacement(super.getSpeed());
            this.setDirection("right");
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Direita.gif"));
            super.setBaseSprite(loading.getImage());
            keysReleased[3] = false;
        }

        // Dash
        if (code == KeyEvent.VK_SPACE && super.getDirection() == "left") {
            super.setXPosition(super.getXPosition() - 100);
        }
        if (code == KeyEvent.VK_SPACE && super.getDirection() == "right") {
            super.setXPosition(super.getXPosition() + 100);
        }
        if (code == KeyEvent.VK_SPACE && super.getDirection() == "top") {
            super.setYPosition(super.getYPosition() - 100);
        }
        if (code == KeyEvent.VK_SPACE && super.getDirection() == "bottom") {
            super.setYPosition(super.getYPosition() + 100);
        }
    }

    public void stop(KeyEvent key) {
        int code = key.getKeyCode();
        if (code == KeyEvent.VK_W) {
            super.setYDisplacement(0);
            keysReleased[0] = true;
        }
        if (code == KeyEvent.VK_S) {
            super.setYDisplacement(0);
            keysReleased[1] = true;
        }
        if (code == KeyEvent.VK_A) {
            super.setXDisplacement(0);
            keysReleased[2] = true;
        }
        if (code == KeyEvent.VK_D) {
            super.setXDisplacement(0);
            keysReleased[3] = true;
        }

        if (keysReleased[0] == true && keysReleased[1] == true && keysReleased[2] == true && keysReleased[3] == true) {
            ImageIcon loading = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
            super.setBaseSprite(loading.getImage());
        }
    }

    public void shoot(KeyEvent key) {
        tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro < bulletDelay) {
            return;
        }
        BulletSprite sprite = new BulletSprite();
        sprite.load();

        int playerCenterX = super.getXPosition() + (super.getImageWidth() / 2);
        int playerCenterY = super.getYPosition() + (super.getImageHeight() / 2);

        if (key.getKeyCode() == KeyEvent.VK_RIGHT || key.getKeyCode() == KeyEvent.VK_L) {
            Bullet bullet = new Bullet(playerCenterX, playerCenterY, sprite, "right");
            this.bullets.add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_UP || key.getKeyCode() == KeyEvent.VK_I) {
            Bullet bullet = new Bullet(playerCenterX, (playerCenterY - super.getImageHeight()), sprite, "top");
            this.bullets.add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_LEFT || key.getKeyCode() == KeyEvent.VK_J) {
            Bullet bullet = new Bullet((playerCenterX - super.getImageWidth()), playerCenterY, sprite, "left");
            this.bullets.add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyCode() == KeyEvent.VK_K) {
            Bullet bullet = new Bullet(playerCenterX, playerCenterY, sprite, "bottom");
            this.bullets.add(bullet);
        }

        if (key.getKeyCode() == KeyEvent.VK_F && this.getPontos() >= 500) {
            SuperBullet superBullet = new SuperBullet(playerCenterX, playerCenterY, sprite, "super", this);
            this.superBullets.add(superBullet);
            this.setPontos(this.getPontos() - 500);
        }
        tempoUltimoTiro = tempoAtual;
    }

    public void checkBoundsCollision() {
        if (super.getXPosition() < 0) {
            super.setXPosition(0);
        } else if (super.getXPosition() + super.getImageWidth() > LARGURA_DA_JANELA) {
            int xMaximum = LARGURA_DA_JANELA - super.getImageWidth();
            super.setXPosition(xMaximum);
        }

        if (super.getYPosition() < 0) {
            super.setYPosition(0);
        } else if (super.getYPosition() + super.getImageHeight() > ALTURA_DA_JANELA) {
            int yMaximum = ALTURA_DA_JANELA - super.getImageHeight();
            super.setYPosition(yMaximum);
        }
    }

    public void equiparItem(Item item) {
        itemEquipado = item;
    }

    public void usarItemEquipado() {
        if (itemEquipado != null) {
            itemEquipado.applyEffect(this);
        }
    }

    public void sofrerDano(int dano) {
        super.setHitPoints(super.getHitPoints() - dano);
        if (super.getHitPoints() <= 0) {

        }
    }

    public void adicionarPontos(int quantidade) {
        pontos += quantidade;
    }

    // Getters e Setters
    public ArrayList<Bullet> getTiros() {
        return bullets;
    }

    public void setTiros(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<SuperBullet> getSuperTiros() {
        return superBullets;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setSuperTiros(ArrayList<SuperBullet> superBullets) {
        this.superBullets = superBullets;
    }

    public long getTempoUltimoTiro() {
        return tempoUltimoTiro;
    }

    public void setTempoUltimoTiro(long tempoUltimoTiro) {
        this.tempoUltimoTiro = tempoUltimoTiro;
    }

    public long getBulletDelay() {
        return bulletDelay;
    }

    public void setBulletDelay(long bulletDelay) {
        this.bulletDelay = bulletDelay;
    }

    public Item getItemEquipado() {
        return itemEquipado;
    }

    public void setItemEquipado(Item itemEquipado) {
        this.itemEquipado = itemEquipado;
    }

}