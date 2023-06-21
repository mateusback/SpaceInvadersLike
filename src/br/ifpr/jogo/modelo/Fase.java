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
    private static final int LARGURA_DA_JANELA = 1000;

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

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();
        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Tiro tiro : tiros) {
            // Carregando imagem do objeto tiro pelo método carregar.
            tiro.carregar();
            // Desenhar o tiro na nossa tela.
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
        // Recuperar a nossa lista de tiros (getTiros) e atribuímos para uma variável
        // local chamada tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();
        // Criando um laço de repetição (foreach). Iremos percorrer toda a lista.
        for (Tiro tiro : tiros) {
            // Verificar se (if) a posição do x (tiro.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (tiro.getPosicaoEmX() > LARGURA_DA_JANELA)
                // Remover da lista se estiver fora do campo de visão (LARGURA_DA_TELA)
                tiros.remove(tiro);
            else
                // Atualizar a posição do tiro.
                tiro.atualizar();
         }
    
         repaint();
    }
}
