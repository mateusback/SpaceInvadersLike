package br.ifpr.jogo.model.graphicelement;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.graphicelement.item.Item;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.model.sprites.BulletSprite;
import br.ifpr.jogo.serivces.player.PlayerServiceImpl;
import jdk.jfr.Name;

import static br.ifpr.jogo.util.ScreenConstants.*;


@Entity
@Table(name = "tb_personagem")
public class Player extends GraphicElement {
    public static final int VIDA_INICIAL_PERSONAGEM = 3;
    private static final int DELAY_INICIAL_TIRO = 500;
    private static final int VELOCIDADE_INICIAL_PERSONAGEM = 3;
    private static final int PIXELS_AJUSTE_ALTURA_TELA = 48;
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
    private PlayerServiceImpl playerService;

    public Player() {
        super.setXPosition(POSICAO_INICIAL_EM_X_PERSONAGEM);
        super.setYPosition(POSICAO_INICIAL_EM_Y_PERSONAGEM);
        this.bullets = new ArrayList<Bullet>();
        this.superBullets = new ArrayList<SuperBullet>();
        this.bulletDelay = DELAY_INICIAL_TIRO;
        super.setSpeed(VELOCIDADE_INICIAL_PERSONAGEM);
        super.setHitPoints(VIDA_INICIAL_PERSONAGEM);
        this.pontos = 0;
        playerService = new PlayerServiceImpl(this);
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
            Bullet bullet = new Bullet(super.getXPosition(), super.getYPosition(), sprite, "right");
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
        } else if (super.getYPosition() + super.getImageHeight() >= ALTURA_DA_JANELA - PIXELS_AJUSTE_ALTURA_TELA) {
            int yMaximum = ALTURA_DA_JANELA - PIXELS_AJUSTE_ALTURA_TELA - super.getImageHeight();
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

    public boolean[] getKeysReleased() {
        return keysReleased;
    }

    public void setKeysReleased(boolean[] keysReleased) {
        this.keysReleased = keysReleased;
    }
}