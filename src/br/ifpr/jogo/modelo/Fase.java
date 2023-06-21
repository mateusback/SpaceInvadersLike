package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
    private static final int LARGURA_DA_JANELA = 1600;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);


        ImageIcon carregando = new ImageIcon("recursos\\Fundo.png");
        this.fundo = carregando.getImage();

        personagem = new Personagem(); //Istanciando o jogador
        personagem.carregar(); //Carregadno a imagem do jogador

        addKeyListener(this); //Instanciando o teclado

        timer = new Timer(DELAY, this); //Velocidade do jogo
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }
    
        g.dispose();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_L)
            personagem.atirar();
        else
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
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            tiro.atualizar(tiros);
        }
        tiros.removeIf(tiro -> tiro.getPosicaoEmX() > LARGURA_DA_JANELA);
         repaint();
    }
}
