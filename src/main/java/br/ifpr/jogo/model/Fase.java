package br.ifpr.jogo.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.model.elementosgraficos.Inimigo;
import br.ifpr.jogo.model.elementosgraficos.Cloud;
import br.ifpr.jogo.model.elementosgraficos.Player;
import br.ifpr.jogo.model.elementosgraficos.itens.ItemManager;
import br.ifpr.jogo.model.elementosgraficos.itens.Item;
import br.ifpr.jogo.model.elementosgraficos.tiros.SuperTiro;
import br.ifpr.jogo.model.elementosgraficos.tiros.Tiro;
import br.ifpr.jogo.model.sprites.SpriteFundo;
import br.ifpr.jogo.serivces.level.LevelServiceImpl;

import static br.ifpr.jogo.util.Constants.*;

//provavelmente Controller

public class Fase extends JPanel implements KeyListener, ActionListener {
    private LevelModel levelModel;
    private LevelServiceImpl levelService;
    private Timer timer;
    private SpriteFundo spriteFundo;
    public ItemManager itemManager;
    public boolean gameOver = false;

    // Construtor da fase
    public Fase() {
        levelModel = new LevelModel();
        levelService = new LevelServiceImpl(this, this.levelModel);
        setFocusable(true);
        setDoubleBuffered(true);
        // Instanciando o teclado.
        addKeyListener(this);
        spriteFundo = new SpriteFundo();
        // Istanciando o jogador e carregando sua imagem.
        levelModel.setPlayer(new Player());
        levelModel.getPlayer().carregar();

        levelService.InitializeAdditionalGraphics();
        // Definindo a velocidade do jogo.
        timer = new Timer(DELAY_JOGO, this);
        timer.start();

        //Renomear
        itemManager = new ItemManager();
        // Adicionando inimigos
        levelModel.setEnemies(new ArrayList<>());

    }

    // Metodo utilizado para desenhar os elementos no painel.
    // O fundo está sendo desenhado e da mesma forma o jogador.
    // Já o os tiros, por serem vários, estão sendo instanciados
    // como forma de array.
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Player player = levelModel.getPlayer();
        super.paintComponent(g);
        spriteFundo.carregarFase1(g, player);

        graphics2D.drawImage(player.getImagem(), player.getPosicaoEmX(), player.getPosicaoEmY(), null);


        levelService.drawBullets(graphics2D);
        levelService.drawEnemies(graphics2D);
        levelService.drawItems(graphics2D);
        levelService.drawClouds(graphics2D);
        levelService.drawPlayerLives(graphics2D);
        levelService.drawGameOver(graphics2D);
        levelService.drawPoints(graphics2D);
        levelService.drawSpeed(graphics2D);
        levelService.drawShootDelay(graphics2D);

        repaint();
        g.dispose();
    }

    // Puxa metodos para quando as teclas são pressionadas.
    @Override
    public void keyPressed(KeyEvent e) {
        levelModel.getPlayer().atirar(e);
        levelModel.getPlayer().mover(e);
    }

    // Puxa metodos para quando soltamos as teclas qu estavam pressionadas.
    @Override
    public void keyReleased(KeyEvent e) {
        levelModel.getPlayer().parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // vazio
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Player player = levelModel.getPlayer();
        if (gameOver) {
            return;
        }
        player.atualizar();
        this.spriteFundo.atualizarJogo(player);
        for (Cloud cloud : levelModel.getNuvens()) {
            cloud.atualizar();
        }
        List<SuperTiro> superTiros = player.getSuperTiros();
        List<Tiro> tiros = player.getTiros();

        levelService.spawnEnemies();
        levelService.checkItemCollision();
        levelService.bulletsRemover();
        levelService.enemiesRemover();

        spriteFundo.atualizarJogo(player);
        player.verificarColisaoBorda();

        if (player.getVida() <= 0) {
            gameOver = true;
            return;
        }
        repaint();

    }

}