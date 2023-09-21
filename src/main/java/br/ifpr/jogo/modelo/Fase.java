package br.ifpr.jogo.modelo;

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

import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.jogo.modelo.elementosgraficos.Inimigo;
import br.ifpr.jogo.modelo.elementosgraficos.Nuvem;
import br.ifpr.jogo.modelo.elementosgraficos.Personagem;
import br.ifpr.jogo.modelo.elementosgraficos.itens.GerenciadorItem;
import br.ifpr.jogo.modelo.elementosgraficos.itens.Item;
import br.ifpr.jogo.modelo.elementosgraficos.tiros.SuperTiro;
import br.ifpr.jogo.modelo.elementosgraficos.tiros.Tiro;
import br.ifpr.jogo.modelo.sprites.SpriteFundo;
import br.ifpr.jogo.principal.Principal;

public class Fase extends JPanel implements KeyListener, ActionListener {
    // Atributos da Fase
    private Personagem personagem;
    private Timer timer;
    private List<Inimigo> inimigos;
    private SpriteFundo spriteFundo;

    private GerenciadorItem gerenciadorItem;
    private int contadorInimigos;
    private ArrayList<Nuvem> nuvens;
    private boolean jogoAcabou = false;

    private final int LIMITE_INIMIGOS = 50;
    private static final int QTDE_DE_NUVENS = 7;
    // Constante para controlar a velocidade de uma fase
    private static final int DELAY = 5;

    // Construtor da fase
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        // Instanciando o teclado.
        addKeyListener(this);
        spriteFundo = new SpriteFundo();
        // Istanciando o jogador e carregando sua imagem.
        personagem = new Personagem();
        personagem.carregar();

        this.inicializaElementosGraficosAdicionais();
        // Definindo a velocidade do jogo.
        timer = new Timer(DELAY, this);
        timer.start();
        gerenciadorItem = new GerenciadorItem();
        // Adicionando inimigos
        inimigos = new ArrayList<>();

    }

    // Metodo utilizado para desenhar os elementos no painel.
    // O fundo está sendo desenhado e da mesma forma o jogador.
    // Já o os tiros, por serem vários, estão sendo instanciados
    // como forma de array.
    public void paintComponent(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        super.paintComponent(g);
        spriteFundo.carregarFase1(g, personagem);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);

        ArrayList<Tiro> tiros = personagem.getTiros();
        for (Tiro tiro : tiros) {
            tiro.carregar();
            graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
        }

        ArrayList<SuperTiro> superTiros = personagem.getSuperTiros();
        for (SuperTiro superTiro : superTiros) {
            superTiro.carregar();
            graficos.drawImage(superTiro.getImagem(), superTiro.getPosicaoEmX(), superTiro.getPosicaoEmY(), null);
        }

        for (Inimigo inimigo : inimigos) {
            // Enquanto tiverem inimigos no ArrayList do construtor, eles serão desenhados.
            graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), null);
        }

        if (!(gerenciadorItem == null)) {
            for (Item item : gerenciadorItem.getItens()) {
                if (item.isVisivel()) {
                    graficos.drawImage(item.getImagem(), item.getPosicaoEmX(), item.getPosicaoEmY(), null);
                }
            }
        }

        for (Nuvem nuvem : nuvens) {
            graficos.drawImage(nuvem.getImagem(), nuvem.getPosicaoEmX(), nuvem.getPosicaoEmY(), this);
        }

        personagem.desenharVida(graficos);

        desenharGameOver(graficos);
        desenharPontos(graficos);
        desenharVelocidiade(graficos);
        desenharDelay(graficos);

        repaint();
        g.dispose();
    }

    public void desenharPontos(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Pontuação: " + personagem.getPontos(), getWidth() - 300, 30);
    }

    public void desenharDelay(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Delay Tiro: " + personagem.getDelayTiro(), getWidth() - 300, 50);
    }

    public void desenharVelocidiade(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Velocidade: " + personagem.getVelocidade(), getWidth() - 300, 70);
    }

    public void desenharGameOver(Graphics g) {
        if (jogoAcabou) {
            Font fonteGameOver = new Font("Arial", Font.BOLD, 48);
            g.setFont(fonteGameOver);
            g.setColor(Color.RED);
            g.drawString("Game Over", Principal.LARGURA_DA_JANELA / 2 - 100, Principal.ALTURA_DA_JANELA / 2);
        }
    }

    public void inicializaElementosGraficosAdicionais() {
        this.nuvens = new ArrayList<Nuvem>();
        for (int i = 0; i < QTDE_DE_NUVENS; i++) {
            int x = (int) (Math.random() * Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Nuvem nuvem = new Nuvem(x, y);
            this.nuvens.add(nuvem);
        }
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

    // Puxa os metodos quando acóes especificas são realizadas.
    // Nesse caso, está puxando os metodos de atualização do personagem e dos tiros.
    // Há uma condição para atualizar os tiros, eles devem estar na tela. Se
    // estiverem fora, serão removidos para economizar recursos.

    @Override
    public void actionPerformed(ActionEvent e) {
        // O Personagem sempre será atualizado
        if (jogoAcabou) {
            return;
        }
        this.personagem.atualizar();
        this.spriteFundo.atualizarJogo(personagem);
        for (Nuvem nuvem : this.nuvens) {
            nuvem.atualizar();
        }
        this.contadorInimigos++;

        if (this.contadorInimigos >= this.LIMITE_INIMIGOS) {
            Random rn = new Random();
            int posicaoEmX = rn.nextInt(1599) + 1;
            int posicaoEmY = rn.nextInt(959) + 1;
            if (!(posicaoEmX == personagem.getPosicaoEmX() && posicaoEmX == personagem.getPosicaoEmY())) {
                Inimigo novoInimigo = new Inimigo(posicaoEmX, posicaoEmY, this.personagem);
                novoInimigo.carregar();
                inimigos.add(novoInimigo);
            }
            this.contadorInimigos = 0;
        }
        if (!(gerenciadorItem == null)) {
            for (Item item : gerenciadorItem.getItens()) {
                if (item.isVisivel() && personagem.getRetangulo().intersects(item.getRetangulo())) {
                    System.out.println("Colisão com item: " + item.getClass().getSimpleName());
                    item.aplicarEfeito(personagem);
                    item.setVisivel(false);
                }
            }
        }
        List<Tiro> tiros = this.personagem.getTiros();

        // Remover tiros que saem da tela, se eles não forem mais visíveis.

        Iterator<Tiro> iteratorTiro = tiros.iterator();

        // Enquanto houver outro item na lista.
        while (iteratorTiro.hasNext()) {
            Tiro tiro = iteratorTiro.next();
            // Se forem visíveis, eles serão atualizados.

            if (tiro.isVisivel() && tiro.verificarVisibilidade()) {
                tiro.atualizar();
                // Se não, serão removidos.
            } else {
                iteratorTiro.remove();
                System.out.println("Tiro Removido");
            }
        }

        List<SuperTiro> superTiros = this.personagem.getSuperTiros();

        Iterator<SuperTiro> iteratorSuperTiro = superTiros.iterator();
        while (iteratorSuperTiro.hasNext()) {
            SuperTiro superTiro = iteratorSuperTiro.next();
            // Se forem visíveis, eles serão atualizados.

            if (superTiro.isVisivel() && superTiro.verificarVisibilidade()) {
                superTiro.atualizar();
                // Se não, serão removidos.
            } else {
                iteratorSuperTiro.remove();
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
                    personagem.sofrerDano(1);
                }

                // Verificar colisão com os tiros
                for (Tiro tiro : tiros) {
                    if (tiro.getRetangulo().intersects(inimigo.getRetangulo())) {
                        System.out.println("Colisão com o tiro");
                        // Aqui ele remove o tiro também.
                        inimigo.dropItem(gerenciadorItem);
                        personagem.adicionarPontos(100);
                        tiros.remove(tiro);
                        inimigo.setVisivel(false);
                        iteratorInimigo.remove();
                        break;
                    }
                }

                int contHits = 0;
                for (Iterator<SuperTiro> iteratorSuperTiro2 = superTiros.iterator(); iteratorSuperTiro2.hasNext();) {
                    SuperTiro superTiro = iteratorSuperTiro2.next();
                    if (superTiro.getRetangulo().intersects(inimigo.getRetangulo())) {
                        System.out.println("Colisão com o tiro");
                        personagem.adicionarPontos(100);
                        inimigo.dropItem(gerenciadorItem);
                        if (contHits == 3) {
                            iteratorSuperTiro2.remove(); // Remover o superTiro após 4 acertos (0, 1, 2, 3 = 4 acertos)
                        }

                        inimigo.setVisivel(false);
                        contHits++; // Incrementar a contagem dos acertos após a verificação

                        break; // Sair do loop após acertar o inimigo
                    }
                }
            } else {
                iteratorInimigo.remove();
            }
        }

        // Lógica do Item de velocidade de ataque.
        // Se ele for visível e nõa for nulo e o jogador encostar nele.
        spriteFundo.atualizarJogo(personagem);
        personagem.verificarColisaoBorda();

        if (personagem.getVida() <= 0) {
            jogoAcabou = true;
            return;
        }
        repaint();

    }

}