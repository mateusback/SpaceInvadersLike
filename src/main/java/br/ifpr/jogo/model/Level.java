package br.ifpr.jogo.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.model.elementosgraficos.Cloud;
import br.ifpr.jogo.model.elementosgraficos.Player;
import br.ifpr.jogo.model.elementosgraficos.itens.ItemManager;
import br.ifpr.jogo.model.elementosgraficos.tiros.SuperTiro;
import br.ifpr.jogo.model.elementosgraficos.tiros.Tiro;
import br.ifpr.jogo.model.sprites.BackgroundSprite;
import br.ifpr.jogo.serivces.level.LevelServiceImpl;

import static br.ifpr.jogo.util.Constants.*;

public class Level extends JPanel implements KeyListener, ActionListener {
    private LevelModel levelModel;
    private LevelServiceImpl levelService;
    private Timer timer;
    private BackgroundSprite backgroundSprite;
    public ItemManager itemManager;
    public boolean gameOver = false;

    // Construtor da fase
    public Level() {
        levelModel = new LevelModel();
        levelService = new LevelServiceImpl(this, this.levelModel);
        setFocusable(true);
        setDoubleBuffered(true);
        // Instanciando o teclado.
        addKeyListener(this);
        backgroundSprite = new BackgroundSprite();
        // Istanciando o jogador e carregando sua imagem.
        levelModel.setPlayer(new Player());
        levelModel.getPlayer().load();

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
        backgroundSprite.loadLevel1(g);

        graphics2D.drawImage(player.getBaseSprite(), player.getXPosition(), player.getYPosition(), null);


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
        player.update();
        this.backgroundSprite.updateOffset(player);
        for (Cloud cloud : levelModel.getNuvens()) {
            cloud.update();
        }
        List<SuperTiro> superTiros = player.getSuperTiros();
        List<Tiro> tiros = player.getTiros();

        levelService.spawnEnemies();
        levelService.checkItemCollision();
        levelService.bulletsRemover();
        levelService.enemiesRemover();

        backgroundSprite.updateOffset(player);
        player.verificarColisaoBorda();

        if (player.getHitPoints() <= 0) {
            gameOver = true;
            return;
        }
        repaint();

    }

}