package br.ifpr.jogo.modelo;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.Sprites.SpriteTiro;
import br.ifpr.jogo.modelo.itens.Item;

public class Personagem extends Entidade {
    private ArrayList<Tiro> tiros;
    private ArrayList<SuperTiro> superTiros;
    private long tempoUltimoTiro;
    private long delayTiro;
    private Item itemEquipado;
    private boolean tudoSolto[] = new boolean[4];
    private long tempoAtual;
    private int pontos;

    private static final int VIDA_BASE = 3;
    private static final int DELAY_BASE = 500;
    private static final int POSICAO_INICIAL_EM_X = 800;
    private static final int POSICAO_INICIAL_EM_Y = 500;
    private static final int VELOCIDADE = 3;
    private static final ImageIcon CORACAO_CHEIO = new ImageIcon("recursos\\CoracaoCheio.png");
    private static final ImageIcon CORACAO_VAZIO = new ImageIcon("recursos\\CoracaoVazio.png");

    public Personagem() {
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.tiros = new ArrayList<Tiro>();
        this.superTiros = new ArrayList<SuperTiro>();
        this.delayTiro = DELAY_BASE;
        super.setVelocidade(VELOCIDADE);
        super.setVida(VIDA_BASE);
        this.pontos = 0;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
        super.setImagem(carregando.getImage());
        super.setAlturaImagem(super.getImagem().getWidth(null));
        super.setLarguraImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + super.getDeslocamentoEmX());
        super.setPosicaoEmY(super.getPosicaoEmY() + super.getDeslocamentoEmY());
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_W) {
            super.setDeslocamentoEmY(super.getDeslocamentoEmY() - super.getVelocidade());
            super.setDirecao("cima");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Cima.gif");
            super.setImagem(carregando.getImage());
            tudoSolto[0] = false;
        }

        if (codigo == KeyEvent.VK_S) {
            super.setDeslocamentoEmY(super.getVelocidade());
            this.setDirecao("baixo");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Baixo.gif");
            super.setImagem(carregando.getImage());
            tudoSolto[1] = false;
        }

        if (codigo == KeyEvent.VK_A) {
            super.setDeslocamentoEmX(super.getDeslocamentoEmX() - super.getVelocidade());
            this.setDirecao("esquerda");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Esquerda.gif");
            super.setImagem(carregando.getImage());
            tudoSolto[2] = false;

        }
        if (codigo == KeyEvent.VK_D) {
            super.setDeslocamentoEmX(super.getVelocidade());
            this.setDirecao("direita");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Direita.gif");
            super.setImagem(carregando.getImage());
            tudoSolto[3] = false;
        }

        // Dash
        if (codigo == KeyEvent.VK_SPACE && super.getDirecao() == "esquerda") {
            super.setPosicaoEmX(super.getPosicaoEmX() - 100);
        }
        if (codigo == KeyEvent.VK_SPACE && super.getDirecao() == "direita") {
            super.setPosicaoEmX(super.getPosicaoEmX() + 100);
        }
        if (codigo == KeyEvent.VK_SPACE && super.getDirecao() == "cima") {
            super.setPosicaoEmY(super.getPosicaoEmY() - 100);
        }
        if (codigo == KeyEvent.VK_SPACE && super.getDirecao() == "baixo") {
            super.setPosicaoEmY(super.getPosicaoEmY() + 100);
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (codigo == KeyEvent.VK_W) {
            super.setDeslocamentoEmY(0);
            tudoSolto[0] = true;
        }
        if (codigo == KeyEvent.VK_S) {
            super.setDeslocamentoEmY(0);
            tudoSolto[1] = true;
        }
        if (codigo == KeyEvent.VK_A) {
            super.setDeslocamentoEmX(0);
            tudoSolto[2] = true;
        }
        if (codigo == KeyEvent.VK_D) {
            super.setDeslocamentoEmX(0);
            tudoSolto[3] = true;
        }

        if (tudoSolto[0] == true && tudoSolto[1] == true && tudoSolto[2] == true && tudoSolto[3] == true) {
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
            super.setImagem(carregando.getImage());
        }
    }

    public void atirar(KeyEvent tecla) {
        tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiro < delayTiro) {
            return;
        }
        SpriteTiro sprite = new SpriteTiro();
        sprite.carregar();

        int centroPersonagemX = super.getPosicaoEmX() + (super.getLarguraImagem() / 2);
        int centroPersonagemY = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);

        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT || tecla.getKeyCode() == KeyEvent.VK_L) {
            Tiro tiro = new Tiro(centroPersonagemX, centroPersonagemY, sprite, "direita");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_UP || tecla.getKeyCode() == KeyEvent.VK_I) {
            Tiro tiro = new Tiro(centroPersonagemX, (centroPersonagemY - super.getAlturaImagem()), sprite, "cima");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_LEFT || tecla.getKeyCode() == KeyEvent.VK_J) {
            Tiro tiro = new Tiro((centroPersonagemX - super.getLarguraImagem()), centroPersonagemY, sprite, "esquerda");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_DOWN || tecla.getKeyCode() == KeyEvent.VK_K) {
            Tiro tiro = new Tiro(centroPersonagemX, centroPersonagemY, sprite, "baixo");
            this.tiros.add(tiro);
        }

        if (tecla.getKeyCode() == KeyEvent.VK_F && this.getPontos() >= 500) {
            SuperTiro tiro = new SuperTiro(centroPersonagemX, centroPersonagemY, sprite, "super", this);
            this.superTiros.add(tiro);
            this.setPontos(this.getPontos()-500);
        }
        tempoUltimoTiro = tempoAtual;
    }

    public void verificarColisaoBorda() {
        if (super.getPosicaoEmX() < 0) {
            super.setPosicaoEmX(0);
        } else if (super.getPosicaoEmX() + super.getLarguraImagem() > 1600) {
            int maximoEmX = 1600 - super.getLarguraImagem(); // CALCULA A POSIÇÃO MÁXIMA
            super.setPosicaoEmX(maximoEmX);
        }

        if (super.getPosicaoEmY() < 0) {
            super.setPosicaoEmY(0);
        } else if (super.getPosicaoEmY() + super.getAlturaImagem() > 960) {
            int maximoEmY = 960 - super.getAlturaImagem();
            super.setPosicaoEmY(maximoEmY);
        }
    }

    public void equiparItem(Item item) {
        itemEquipado = item;
    }

    public void usarItemEquipado() {
        if (itemEquipado != null) {
            itemEquipado.aplicarEfeito(this);
        }
    }

    public void sofrerDano(int dano) {
        super.setVida(super.getVida() - dano);
        if (super.getVida() <= 0) {

        }
    }

    public void desenharVida(Graphics2D g) {
        int coracaoLargura = CORACAO_CHEIO.getIconWidth();
        int vidaCheia = getVida();

        for (int i = 0; i < VIDA_BASE; i++) {
            ImageIcon icone = (i < vidaCheia) ? CORACAO_CHEIO : CORACAO_VAZIO;
            g.drawImage(icone.getImage(), 10 + (i * coracaoLargura), 10, null);
        }
    }

    public void adicionarPontos(int quantidade) {
        pontos += quantidade;
    }

    // Getters e Setters
    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public ArrayList<SuperTiro> getSuperTiros() {
        return superTiros;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setSuperTiros(ArrayList<SuperTiro> superTiros) {
        this.superTiros = superTiros;
    }

    public long getTempoUltimoTiro() {
        return tempoUltimoTiro;
    }

    public void setTempoUltimoTiro(long tempoUltimoTiro) {
        this.tempoUltimoTiro = tempoUltimoTiro;
    }

    public long getDelayTiro() {
        return delayTiro;
    }

    public void setDelayTiro(long delayTiro) {
        this.delayTiro = delayTiro;
    }

    public Item getItemEquipado() {
        return itemEquipado;
    }

    public void setItemEquipado(Item itemEquipado) {
        this.itemEquipado = itemEquipado;
    }
    @Override
    public void setVelocidade(int velocidade){
        if(super.getVelocidade() > 10){
            return;
        }else{
            super.setVelocidade(velocidade);
        }
    }

}