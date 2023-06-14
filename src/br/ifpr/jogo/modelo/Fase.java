package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.principal.Personagem;

public class Fase extends JPanel implements KeyListener, ActionListener {
    private Image fundo;
    private Personagem personagem;
    private Timer timer;

    //Constante para controlar a velocidade de uma fase  
    private static final int DELAY = 5;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);


        ImageIcon carregando = new ImageIcon("recursos\\Fundo.jpg");
        this.fundo = carregando.getImage();

        personagem = new Personagem(); //Istanciando o jogador
        personagem.carregar(); //Carregadno a imagem do jogador

        addKeyListener(this); //Instanciando o teclado

        timer = new Timer(DELAY, this); //Velocidade do jogo
        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0,0,null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //vazio
    }

    @Override
    public void actionPerformed(ActionEvent e){
        personagem.atualizar();
        repaint(); //Não vai deixar a imagem aparecer várias vezes na tela
    }
}
