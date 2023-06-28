package br.ifpr.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.modelo.Sprites.SpriteFundo;
import br.ifpr.jogo.modelo.itens.ItemTiroRapido;
import br.ifpr.jogo.modelo.itens.ItemVelocidade;

public class Fase extends JPanel implements KeyListener, ActionListener {
    // Atributos da Fase
    private Personagem personagem;
    private Timer timer;
    private List<Inimigo> inimigos;
    private ItemTiroRapido itemTiroRapido;
    private ItemVelocidade itemVelocidade;
    private SpriteFundo spriteFundo;

    private int offsetX = 0;
    private int offsetY = 0;
    private ArrayList<Entidade> listaEntidades;

    // Constante para controlar a velocidade de uma fase
    private static final int DELAY = 5;

    // Construtor da fase
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        listaEntidades = new ArrayList<>();
        // Istanciando o jogador e carregando sua imagem.
        personagem = new Personagem();
        personagem.carregar();
        spriteFundo = new SpriteFundo();
        // Instanciando o teclado.
        addKeyListener(this);

        // Definindo a velocidade do jogo.
        timer = new Timer(DELAY, this);
        timer.start();

        // Adicionando inimigos
        inimigos = new ArrayList<>();
        inimigos.add(new Inimigo(0, 0, personagem));
        inimigos.add(new Inimigo(400, 900, personagem));
        inimigos.add(new Inimigo(100, 300, personagem));
        inimigos.add(new Inimigo(200, 200, personagem));
        inimigos.add(new Inimigo(1040, 510, personagem));
        inimigos.add(new Inimigo(600, 900, personagem));
        for (Inimigo inimigo : inimigos) {
            inimigo.carregar();
        }

        // Adicionando o item de tiro rápido a fase.
        itemTiroRapido = new ItemTiroRapido(1293, 434);
        itemTiroRapido.carregar();
        itemVelocidade = new ItemVelocidade(805, 130);
        itemVelocidade.carregar();
        listaEntidades.add(itemTiroRapido);
        listaEntidades.add(itemVelocidade);

    }

    // Metodo utilizado para desenhar os elementos no painel.
    // O fundo está sendo desenhado e da mesma forma o jogador.
    // Já o os tiros, por serem vários, estão sendo instanciados
    // como forma de array.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;
        graficos.translate(offsetX, offsetY);

        spriteFundo.carregarFase1(g, personagem);

        // Carrega o persosangem.
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);

        // Cria um ArrayList para armazenar os tiros.
        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            // Enquanto tiver tiros na lista de ArrayList, eles serão desenahdos na tela.
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
        }

        for (Inimigo inimigo : inimigos) {
            // Enquanto tiverem inimigos no ArrayList do construtor, eles serão desenhados.
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
        }

        // Carrega o item se ele não for nulo e for visível
        if (itemTiroRapido != null && itemTiroRapido.isVisivel()) {
            graficos.drawImage(itemTiroRapido.getImagem(), itemTiroRapido.getPosicaoEmX() - offsetX,
                            itemTiroRapido.getPosicaoEmY() - offsetY, this);
        }
        if (itemVelocidade != null && itemVelocidade.isVisivel()) {
            graficos.drawImage(itemVelocidade.getImagem(), itemVelocidade.getPosicaoEmX() - offsetX,
                            itemVelocidade.getPosicaoEmY() - offsetY, this);
        }
        // Solta os elementos na tela
        g.dispose();
    }

    // Puxa metodos para quando as teclas são pressionadas.
    @Override
    public void keyPressed(KeyEvent e) {
        personagem.atirar(e);
        personagem.mover(e);
    }

    // Puxa metodos para quando soltamos as teclas qu estavam pressionadas.
    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // vazio
    }

    // Puxa os metoods quando acóes especificas são realizadas.
    // Nesse caso, está puxando os metodos de atualização do personagem e dos tiros.
    // Há uma condição para atualizar os tiros, eles devem estar na tela. Se
    // estiverem fora, serão removidos para economizar recursos.
    public void atualizarEntidades(SpriteFundo spriteFundo, Personagem personagem) {
        // Atualiza a posição das entidades em relação ao deslocamento da câmera
        for (Entidade entidade : listaEntidades) {
            entidade.setPosicao(entidade.getPosicaoEmX() - spriteFundo.offsetX,
                                entidade.getPosicaoEmY() - spriteFundo.offsetY);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        

        // O Personagem sempre será atualizado
        personagem.atualizar();

        spriteFundo.atualizarJogo(personagem);
        List<Tiro> tiros = personagem.getTiros();

        // Remover tiros que saem da tela, se eles não forem mais visíveis.

        Iterator<Tiro> iteratorTiro = tiros.iterator();

        //Enquanto houver outro item na lista.
        while (iteratorTiro.hasNext()) {
            Tiro tiro = iteratorTiro.next();
            // Se forem visíveis, eles serão atualizados.
            if (tiro.isVisivel()) {
                tiro.atualizar();
            // Se não, serão removidos.
            } else {
                iteratorTiro.remove();
                System.out.println("Tiro Removido");
            }
        }

        // Lógica para remover os inimigos, é um pouco mais complexa.
        Iterator<Inimigo> iteratorInimigo = inimigos.iterator();
        while (iteratorInimigo.hasNext()) {
            Inimigo inimigo = iteratorInimigo.next();
            if (inimigo.isVisivel()) {
                inimigo.atualizar();

                // Verificar colisão com o personagem
                if (personagem.getRetangulo().intersects(inimigo.getRetangulo())) {
                    System.out.println("Colisão com o personagem");
                    iteratorInimigo.remove(); // Remover o inimigo da lista
                    inimigo.setVisivel(false); // Marcar o inimigo como invisível
                }

                // Verificar colisão com os tiros
                for (Tiro tiro : tiros) {
                    if (tiro.getRetangulo().intersects(inimigo.getRetangulo())) {
                        System.out.println("Colisão com o tiro");
                        //Aqui ele remove o tiro também.
                        tiros.remove(tiro);
                        inimigo.setVisivel(false);
                        iteratorInimigo.remove();
                        break;
                    }
                }
            } else {
                iteratorInimigo.remove();
            }
        }

        // Lógica do Item de velocidade de ataque.
        // Se ele for visível e nõa for nulo e o jogador encostar nele.
        if (itemTiroRapido != null) {
            itemTiroRapido.verificarColisao(personagem);

            if (itemTiroRapido.isColetado()) {
                itemTiroRapido = null;
            }
        }

        // Lógica do Item de velocidade.
        if (itemVelocidade != null) {
            itemVelocidade.verificarColisao(personagem);

            if (itemVelocidade.isColetado()) {
                itemVelocidade = null;
            }
        }
        personagem.colisaoBorda();
        atualizarEntidades(spriteFundo, personagem);
        repaint();
    }
}