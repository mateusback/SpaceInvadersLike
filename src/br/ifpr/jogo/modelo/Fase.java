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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.principal.Personagem;

public class Fase extends JPanel implements KeyListener, ActionListener {

    //Atributos da Fase
    private Image fundo;
    private Personagem personagem;
    private Timer timer;
    private List<Inimigo> inimigos;

    //Constante para controlar a velocidade de uma fase  
    private static final int DELAY = 5;

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

        //Adicionando inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(0, 0, personagem));
        inimigos.add(new Inimigo(400, 900, personagem));
        inimigos.add(new Inimigo(100, 300, personagem));
        inimigos.add(new Inimigo(200, 200, personagem));
        inimigos.add(new Inimigo(900, 510, personagem));
        inimigos.add(new Inimigo(600, 700, personagem));
        for (Inimigo inimigo : inimigos) {
            inimigo.carregar();
        }
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
        for (Inimigo inimigo : inimigos) {
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
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
        List<Tiro> tiros = personagem.getTiros();
        
        // Remover tiros que saem da tela
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            if (tiro.getVisivel()) {
                tiro.atualizar();
            } else {
                tiros.remove(i);
                i--; // Decrementa o índice para continuar verificando os próximos elementos corretamente
            }
        }
        
        Iterator<Inimigo> iterator = inimigos.iterator();
        while (iterator.hasNext()) {
            Inimigo inimigo = iterator.next();
            if (inimigo.getVisivel()) {
                inimigo.atualizar();
                
                // Verificar colisão com o personagem
                if (personagem.getRetangulo().intersects(inimigo.getRetangulo())) {
                    System.out.println("Colisão com o personagem");
                    iterator.remove(); // Remover o inimigo da lista
                    inimigo.setVisivel(false); // Marcar o inimigo como invisível
                }
                
                // Verificar colisão com os tiros
                for (Tiro tiro : tiros) {
                    if (tiro.getRetangulo().intersects(inimigo.getRetangulo())) {
                        System.out.println("Colisão com o tiro");
                        // Lógica para tratar a colisão do tiro com o inimigo (pontuação, remoção do inimigo, etc.)
                        tiros.remove(tiro);
                        inimigo.setVisivel(false);
                        iterator.remove();
                        break;
                    }
                }
            } else {
                iterator.remove();
            }
        }
        
        repaint();
    }
}