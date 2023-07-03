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

import br.ifpr.jogo.modelo.Sprites.SpriteFundo;
import br.ifpr.jogo.modelo.itens.GerenciadorItem;
import br.ifpr.jogo.modelo.itens.Item;
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
    private int offsetX;
    private int offsetY;
    private List<Item> itensDropados;
    private GerenciadorItem gerenciadorItem;
    private int contadorInimigos;

    private boolean jogoAcabou = false;

    private final int LARGURA_TELA = 1600;
    private final int ALTURA_TELA = 960;
    private final int LIMITE_INIMIGOS = 50;
    // Constante para controlar a velocidade de uma fase
    private static final int DELAY = 5;

    // Construtor da fase
    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        // Instanciando o teclado.
        addKeyListener(this);
        offsetX = 0;
        offsetY = 0;
        // Istanciando o jogador e carregando sua imagem.
        personagem = new Personagem();
        personagem.carregar();
        spriteFundo = new SpriteFundo();

        // Definindo a velocidade do jogo.
        timer = new Timer(DELAY, this);
        timer.start();

        // Adicionando inimigos
        inimigos = new ArrayList<>();

        itensDropados = new ArrayList<>();
        gerenciadorItem = new GerenciadorItem();
        // Adicionando o item de tiro rápido a fase.
        itemTiroRapido = new ItemTiroRapido(1293, 434);
        itemTiroRapido.carregar();
        gerenciadorItem.adicionarItem(itemTiroRapido);

        // Adicionando o item de velocidade ao gerenciador de itens
        itemVelocidade = new ItemVelocidade(805, 130);
        itemVelocidade.carregar();
        gerenciadorItem.adicionarItem(itemVelocidade);
    }

    // Metodo utilizado para desenhar os elementos no painel.
    // O fundo está sendo desenhado e da mesma forma o jogador.
    // Já o os tiros, por serem vários, estão sendo instanciados
    // como forma de array.
    public void paintComponent(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.translate(offsetX, offsetY);
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

        // Carrega o item se ele não for nulo e for visível
        if (itemTiroRapido != null && itemTiroRapido.isVisivel()) {
            graficos.drawImage(itemTiroRapido.getImagem(), itemTiroRapido.getPosicaoEmX() - offsetX,
                    itemTiroRapido.getPosicaoEmY() - offsetY, this);
        }

        if (itemVelocidade != null && itemVelocidade.isVisivel()) {
            graficos.drawImage(itemVelocidade.getImagem(), itemVelocidade.getPosicaoEmX() - offsetX,
                    itemVelocidade.getPosicaoEmY() - offsetY, this);
        }

        for (Item item : itensDropados) {
            if (item.isVisivel()) {
                graficos.drawImage(item.getImagem(), item.getPosicaoEmX(), item.getPosicaoEmY(), null);
            }
        }

        personagem.desenharVida(graficos);

        desenharGameOver(graficos);

        g.dispose();
        repaint();
    }

    public void desenharGameOver(Graphics g) {
        if (jogoAcabou) {
            Font fonteGameOver = new Font("Arial", Font.BOLD, 48);
            g.setFont(fonteGameOver);
            g.setColor(Color.RED);
            g.drawString("Game Over", LARGURA_TELA / 2 - 100, ALTURA_TELA / 2);
        }
    }

    public void adicionarItemDropado(Item itemDropado) {
        itensDropados.add(itemDropado);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // O Personagem sempre será atualizado
        if (jogoAcabou) {
            return;
        }
        this.personagem.atualizar();
        this.spriteFundo.atualizarJogo(personagem);

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

        for (Item item : gerenciadorItem.getItens()) {
            if (item.isVisivel() && personagem.getRetangulo().intersects(item.getRetangulo())) {
                System.out.println("Colisão com item: " + item.getClass().getSimpleName());
                item.aplicarEfeito(personagem);
                item.setVisivel(false);
            }
        }

        for (Item item : itensDropados) {
            if (item.isVisivel() && personagem.getRetangulo().intersects(item.getRetangulo())) {
                item.aplicarEfeito(personagem);
                item.setVisivel(false);
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
                        contHits++;
                        // Aqui ele remove o tiro também.
                        if (contHits == 3) {
                            iteratorSuperTiro2.remove();
                        }
                        inimigo.setVisivel(false);
                        iteratorSuperTiro2.remove();
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
        spriteFundo.atualizarJogo(personagem);
        personagem.verificarColisaoBorda();

        if (personagem.getVida() <= 0) {
            jogoAcabou = true;
            return;
        }
        repaint();

    }

}