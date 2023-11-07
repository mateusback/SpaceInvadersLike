package br.ifpr.jogo.model.elementosgraficos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.persistence.*;
import javax.swing.ImageIcon;

import br.ifpr.jogo.model.elementosgraficos.itens.Item;
import br.ifpr.jogo.model.elementosgraficos.tiros.SuperTiro;
import br.ifpr.jogo.model.elementosgraficos.tiros.Tiro;
import br.ifpr.jogo.model.sprites.BulletSprite;
import jdk.jfr.Name;

import static br.ifpr.jogo.util.Constants.*;

//
@Entity
@Table(name = "tb_personagem")
public class Player extends GraphicElement {
    @Transient
    private ArrayList<Tiro> tiros;
    @Transient
    private ArrayList<SuperTiro> superTiros;
    @Column
    @Name("tempo_ultimo_tiro")
    private long tempoUltimoTiro;
    @Column
    @Name("delay_tiro")
    private long delayTiro;
    @Transient
    private Item itemEquipado;
    @Transient
    private boolean tudoSolto[] = new boolean[4];
    @Column(name="tempo_atual")
    private long tempoAtual;
    @Column(name="pontuacao")
    private int pontos;

    public Player() {
        super.setXPosition(POSICAO_INICIAL_EM_X_PERSONAGEM);
        super.setYPosition(POSICAO_INICIAL_EM_Y_PERSONAGEM);
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.delayTiro = DELAY_INICIAL_TIRO;
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

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            super.setYDisplacement(super.getYDisplacement() - super.getSpeed());
            super.setDirection("cima");
            ImageIcon carregando = new ImageIcon(getClass().getResource("/Personagem_Cima.gif"));
            super.setBaseSprite(carregando.getImage());
            tudoSolto[0] = false;
        }

        if (codigo == KeyEvent.VK_S) {
            super.setYDisplacement(super.getSpeed());
            this.setDirection("baixo");
            ImageIcon carregando = new ImageIcon(getClass().getResource("/Personagem_Baixo.gif"));
            super.setBaseSprite(carregando.getImage());
            tudoSolto[1] = false;
        }

        if (codigo == KeyEvent.VK_A) {
            super.setXDisplacement(super.getXDisplacement() - super.getSpeed());
            this.setDirection("esquerda");
            ImageIcon carregando = new ImageIcon(getClass().getResource("/Personagem_Esquerda.gif"));
            super.setBaseSprite(carregando.getImage());
            tudoSolto[2] = false;

        }
        if (codigo == KeyEvent.VK_D) {
            super.setXDisplacement(super.getSpeed());
            this.setDirection("direita");
            ImageIcon carregando = new ImageIcon(getClass().getResource("/Personagem_Direita.gif"));
            super.setBaseSprite(carregando.getImage());
            tudoSolto[3] = false;
        }

        // Dash
        if (codigo == KeyEvent.VK_SPACE && super.getDirection() == "esquerda") {
            super.setXPosition(super.getXPosition() - 100);
        }
        if (codigo == KeyEvent.VK_SPACE && super.getDirection() == "direita") {
            super.setXPosition(super.getXPosition() + 100);
        }
        if (codigo == KeyEvent.VK_SPACE && super.getDirection() == "cima") {
            super.setYPosition(super.getYPosition() - 100);
        }
        if (codigo == KeyEvent.VK_SPACE && super.getDirection() == "baixo") {
            super.setYPosition(super.getYPosition() + 100);
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_W) {
            super.setYDisplacement(0);
            tudoSolto[0] = true;
        }
        if (codigo == KeyEvent.VK_S) {
            super.setYDisplacement(0);
            tudoSolto[1] = true;
        }
        if (codigo == KeyEvent.VK_A) {
            super.setXDisplacement(0);
            tudoSolto[2] = true;
        }
        if (codigo == KeyEvent.VK_D) {
            super.setXDisplacement(0);
            tudoSolto[3] = true;
        }

        if (tudoSolto[0] == true && tudoSolto[1] == true && tudoSolto[2] == true && tudoSolto[3] == true) {
            ImageIcon carregando = new ImageIcon(getClass().getResource("/Personagem_Parado.png"));
            super.setBaseSprite(carregando.getImage());
        }
    }

    public void atirar(KeyEvent tecla) {
        tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro < delayTiro) {
            return;
        }
        BulletSprite sprite = new BulletSprite();
        sprite.carregar();

        int centroPersonagemX = super.getXPosition() + (super.getImageWidth() / 2);
        int centroPersonagemY = super.getYPosition() + (super.getImageHeight() / 2);

        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT || tecla.getKeyCode() == KeyEvent.VK_L) {
            Tiro tiro = new Tiro(centroPersonagemX, centroPersonagemY, sprite, "direita");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_UP || tecla.getKeyCode() == KeyEvent.VK_I) {
            Tiro tiro = new Tiro(centroPersonagemX, (centroPersonagemY - super.getImageHeight()), sprite, "cima");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_LEFT || tecla.getKeyCode() == KeyEvent.VK_J) {
            Tiro tiro = new Tiro((centroPersonagemX - super.getImageWidth()), centroPersonagemY, sprite, "esquerda");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_DOWN || tecla.getKeyCode() == KeyEvent.VK_K) {
            Tiro tiro = new Tiro(centroPersonagemX, centroPersonagemY, sprite, "baixo");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_F && this.getPontos() >= 500) {
            SuperTiro tiro = new SuperTiro(centroPersonagemX, centroPersonagemY, sprite, "super", this);
            this.superTiros.add(tiro);
            this.setPontos(this.getPontos() - 500);
        }
        tempoUltimoTiro = tempoAtual;
    }

    public void verificarColisaoBorda() {
        if (super.getXPosition() < 0) {
            super.setXPosition(0);
        } else if (super.getXPosition() + super.getImageWidth() > 1600) {
            int maximoEmX = 1600 - super.getImageWidth(); // CALCULA A POSIÇÃO MÁXIMA
            super.setXPosition(maximoEmX);
        }

        if (super.getYPosition() < 0) {
            super.setYPosition(0);
        } else if (super.getYPosition() + super.getImageHeight() > 960) {
            int maximoEmY = 960 - super.getImageHeight();
            super.setYPosition(maximoEmY);
        }
    }

    public void equiparItem(Item item) {
        itemEquipado = item;
    }

    public void usarItemEquipado() {
        if (itemEquipado != null) {
            itemEquipado.aplicarEfeito(this);
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
    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return superTiros;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public long getTempoUltimoTiro() {
        return tempoUltimoTiro;
    }

    public void setTempoUltimoTiro(long tempoUltimoTiro) {
        this.tempoUltimoTiro = tempoUltimoTiro;
    }

    public long getDelayTiro() {
        return delayTiro;
    }

    public void setDelayTiro(long delayTiro) {
        this.delayTiro = delayTiro;
    }

    public Item getItemEquipado() {
        return itemEquipado;
    }

    public void setItemEquipado(Item itemEquipado) {
        this.itemEquipado = itemEquipado;
    }

}