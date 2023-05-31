package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.principal.Personagem;

public class Fase extends JPanel implements ActionListener{
    private Image fundo;
    private Personagem personagem;
    private Timer timer;
    
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);


        ImageIcon carregando = new ImageIcon("recursos\\Fundo.jpg");
        this.fundo = carregando.getImage();

        personagem = new Personagem(); //Istanciando o jogador
        personagem.carregar(); //Carregadno a imagem do jogador

        addKeyListener(new KeyboardAdapter()); //Instanciando o teclado

        timer = new Timer(5, this); //Velocidade do jogo
        timer.start();
    }

    public void paint(Graphics g){
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0,0,null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        personagem.atualizar();
        repaint(); //Não vai deixar a imagem aparecer várias vezes na tela
    }

    private class KeyboardAdapter extends KeyAdapter{ //colocando os métodos do teclado na classe fase
        @Override
        public void keyPressed(KeyEvent e){
            personagem.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e){
            personagem.keyReleased(e);
        }
    }
}
