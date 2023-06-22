package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.principal.Personagem;

public class Fase extends JPanel implements KeyListener, ActionListener {

    //Atributos da Fase
    private Image fundo;
    private Personagem personagem;
    private Timer timer;

    //Constante para controlar a velocidade de uma fase  
    private static final int DELAY = 5;
    private static final int LARGURA_DA_JANELA = 1600;
    private static final int ALTURA_DA_JANELA = 1600;

    //Construtor da fase
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        //Carregando a imagem de fundo.
        ImageIcon carregando = new ImageIcon("recursos\\Fundo.png");
        this.fundo = carregando.getImage();

        //Istanciando o jogador e carregando sua imagem.
        personagem = new Personagem(); 
        personagem.carregar();

        //Instanciando o teclado.
        addKeyListener(this); 

        //Definindo a velocidade do jogo.
        timer = new Timer(DELAY, this);
        timer.start();
    }

    //Metodo utilizado para desenhar os elementos no painel.
    //O fundo está sendo desenhado e da mesma forma o jogador.
    //Já o os tiros, por serem vários, estão sendo instanciados como forma de array.
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null); //Carrega o Fundo.
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null); //Carrega o Persosangem.
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this); //Carrega um tiro.
        }

        //Solta os recursso na tela
        g.dispose();
    }


    //Puxa metodos para quando as teclas são pressionadas.
    @Override
    public void keyPressed(KeyEvent e) {
            personagem.atirar(e);
            personagem.mover(e);
    }

    //Puxa metodos para quando soltamos as teclas qu estavam pressionadas.
    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //vazio
    }

    //Puxa os metoods quando acóes especificas são realizadas.
    //Nesse caso, está puxando os metodos de atualização do personagem e dos tiros.
    //Há uma condição para atualizar os tiros, eles devem estar na tela. Se estiverem fora, serão removidos para economizar recursos.
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        ArrayList<Tiro> tiros = personagem.getTiros();

        
        Iterator<Tiro> iterator = tiros.iterator();     //O Iterator percorre os elementos da lista tiros
        while (iterator.hasNext()) {                    //O método hasNext() verifica se ainda há elementos na lista.
            Tiro tiro = iterator.next();                //O método next() retorna o próximo elemento.
            if (tiro.getPosicaoEmX() > LARGURA_DA_JANELA || tiro.getPosicaoEmY() > ALTURA_DA_JANELA || tiro.getPosicaoEmX() < 0 || tiro.getPosicaoEmY() < 0) {
                iterator.remove();                      //Se estiver fora da Janela, o iterator remove o tiro da lista.
            } else {
                tiro.atualizar();                       //Se não, o elemento é atualizado
            }
        }

        //Serve para Repintar o componente.
        repaint();
    }
}