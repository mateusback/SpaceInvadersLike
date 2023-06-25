package br.ifpr.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import br.ifpr.jogo.modelo.itens.Item;

public class Personagem extends Entidade {
    // Atributos do Personagem
    private ArrayList<Tiro> tiros;
    private long tempoUltimoTiro;
    private long delayTiro;
    private Item itemEquipado;
    
    // Constantes
    private static int POSICAO_INICIAL_EM_X = 800;
    private static int POSICAO_INICIAL_EM_Y = 500;

    // Construtor, já carrega a sua posição inicial, inicializa um array de tiros
    // e seta o delay entre os tiros
    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.tiros = new ArrayList<Tiro>();
        this.delayTiro = 500;
        this.velocidade = 3;
    }

    // Metodo para carregar o jogador na tela
    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // Método responsavel por atulalizar o jogador, movimentando caso o deslocamento
    // não seja = 0
    @Override
    public void atualizar() {
        this.posicaoEmX += deslocamentoEmX;
        this.posicaoEmY += deslocamentoEmY;
    }

    // Metodo responsavel pelo moviemnto do personagem, alterando o deslocamento e
    // setando as direções.
    // Existe uma atualização do icone do jogador nas condições: baixo, direta e
    // esquerda.
    // Há um dash ao pressionar o a tecla espaço
    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        // Cima
        if (codigo == KeyEvent.VK_W) {
            deslocamentoEmY = - velocidade;
            this.setDirecao("cima");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Cima.gif");
            this.imagem = carregando.getImage();
        }

        // Baixo
        if (codigo == KeyEvent.VK_S) {
            deslocamentoEmY = velocidade;
            this.setDirecao("baixo");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Baixo.gif");
            this.imagem = carregando.getImage();
        }

        // Esquerda
        if (codigo == KeyEvent.VK_A) {
            deslocamentoEmX = -velocidade;
            this.setDirecao("esquerda");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Esquerda.gif");
            this.imagem = carregando.getImage();

            // Direita
        }
        if (codigo == KeyEvent.VK_D) {
            deslocamentoEmX = velocidade;
            this.setDirecao("direita");
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Direita.gif");
            this.imagem = carregando.getImage();
        }

        // Dash
        if (codigo == KeyEvent.VK_SPACE && direcao == "esquerda") {
            this.posicaoEmX -= 100;
        }
        if (codigo == KeyEvent.VK_SPACE && direcao == "direita") {
            this.posicaoEmX += 100;
        }
        if (codigo == KeyEvent.VK_SPACE && direcao == "cima") {
            this.posicaoEmY -= 100;
        }
        if (codigo == KeyEvent.VK_SPACE && direcao == "baixo") {
            this.posicaoEmY += 100;
        }
    }

    // Metodo responsavel por parar o personagem ao finalizar o movimento.
    // Este metodo está carregando a imagem padrão do personagem para que ela volte
    // ao deixar o personagem imovél
    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        boolean tudoSolto[] = new boolean[4];
        // Cima
        if (codigo == KeyEvent.VK_W) {
            deslocamentoEmY = 0;
            tudoSolto[0] = true;
        }

        // Baixo
        if (codigo == KeyEvent.VK_S) {
            deslocamentoEmY = 0;
            tudoSolto[1] = true;
        }

        // Esquerda
        if (codigo == KeyEvent.VK_A) {
            deslocamentoEmX = 0;
            tudoSolto[2] = true;
        }

        // Direita
        if (codigo == KeyEvent.VK_D) {
            deslocamentoEmX = 0;
            tudoSolto[3] = true;
        }

        // Isso é uma tentativa de fazer com que a imagem mude caso o personagem solte
        // todas as teclas.
        if (tudoSolto[0] == true && tudoSolto[1] == true && tudoSolto[2] == true && tudoSolto[3] == true) {
            ImageIcon carregando = new ImageIcon("recursos\\Personagem_Parado.png");
            this.imagem = carregando.getImage();
        }
    }

    // Este metodo é responsavel pela ação de tiro do jogador. Foi o metodo mais
    // trabalhoso até agora.
    // Ao atirar, a direção do tiro também é setada para que ele saia na direção
    // desejada.
    // O tiro também tem um delay básico.
    public void atirar(KeyEvent tecla) {
        //Vá
        long tempoAtual = System.currentTimeMillis();

        if (tempoAtual - tempoUltimoTiro < delayTiro) {
            return; // Ainda não passou tempo suficiente desde o último tiro
        }
        // Criando uma instância de Sprite
        Sprite sprite = new Sprite();
        sprite.carregar();
        // Tiro para a Direita
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT || tecla.getKeyCode() == KeyEvent.VK_L) {
            int frenteDoPersonagem = this.posicaoEmX + this.larguraImagem;
            int meioDoPersonagem = this.posicaoEmY + (this.alturaImagem / 2);
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "direita");
            this.tiros.add(tiro);
        }

        // Tiro para Cima
        if (tecla.getKeyCode() == KeyEvent.VK_UP || tecla.getKeyCode() == KeyEvent.VK_I) {
            int frenteDoPersonagem = this.posicaoEmX + (this.larguraImagem / 2);
            int meioDoPersonagem = this.posicaoEmY + this.alturaImagem;
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "cima");
            this.tiros.add(tiro);
        }

        // Tiro para a Esquerda
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT || tecla.getKeyCode() == KeyEvent.VK_J) {
            int frenteDoPersonagem = this.posicaoEmX - Tiro.LARGURA_TIRO;
            int meioDoPersonagem = this.posicaoEmY + (this.alturaImagem / 2);
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "esquerda");
            this.tiros.add(tiro);
        }

        // Tiro para Baixo
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN || tecla.getKeyCode() == KeyEvent.VK_K) {
            int frenteDoPersonagem = this.posicaoEmX + (this.larguraImagem / 2);
            int meioDoPersonagem = this.posicaoEmY - Tiro.ALTURA_TIRO;
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "baixo");
            this.tiros.add(tiro);
        }

        // Super Tiro
        if (tecla.getKeyCode() == KeyEvent.VK_F) {
            int frenteDoPersonagem = this.posicaoEmX + (this.larguraImagem / 2);
            int meioDoPersonagem = this.posicaoEmY - Tiro.ALTURA_TIRO;
            Tiro tiro = new Tiro(frenteDoPersonagem, meioDoPersonagem, sprite, "super");
            this.tiros.add(tiro);
        }
        tempoUltimoTiro = tempoAtual;
    }

    public void equiparItem(Item item) {
        itemEquipado = item;
    }

    public void usarItemEquipado() {
        if (itemEquipado != null) {
            itemEquipado.aplicarEfeito(this);
        }
    }
    // Getters e Setters

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
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

}