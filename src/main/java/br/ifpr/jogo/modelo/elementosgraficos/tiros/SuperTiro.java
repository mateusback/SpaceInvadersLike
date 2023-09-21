package br.ifpr.jogo.modelo.elementosgraficos.tiros;


import br.ifpr.jogo.modelo.elementosgraficos.ElementoGrafico;
import br.ifpr.jogo.modelo.elementosgraficos.Personagem;
import br.ifpr.jogo.modelo.sprites.SpriteTiro;


public class SuperTiro extends ElementoGrafico{

    private SpriteTiro sprite;
    private Personagem personagem;
    private long tempoInicial;
    public static final int LARGURA_TIRO = 10;
    public static final int ALTURA_TIRO = 30;
    public static final int VELOCIDADE = 4;

    public SuperTiro(int posicaoPersonagemEmX, int posicaoPersonagemEmY, SpriteTiro sprite, String direcao, Personagem personagem) {
        super.setPosicaoEmX(posicaoPersonagemEmX);
        super.setPosicaoEmY(posicaoPersonagemEmY);
        this.sprite = sprite;
        super.setDirecao(direcao);
        super.setVisivel(true);
        super.setVelocidade(VELOCIDADE);
        this.personagem = personagem;
        this.tempoInicial = System.currentTimeMillis();
    }

    @Override
    public void carregar() {
        super.setImagem(sprite.getImagem(super.getDirecao()));
        super.setAlturaImagem(ALTURA_TIRO);
        super.setLarguraImagem(ALTURA_TIRO);
    }

    @Override
    public void atualizar() {
        long tempoAtual = System.currentTimeMillis();
        long tempoDecorrido = tempoAtual - tempoInicial;
        if (tempoDecorrido >= 5000) { // 5000 ms = 5 segundos
            super.setVisivel(false);
        } else {
            double angulo = (tempoDecorrido / 1000.0) * Math.PI * 2;
            int raio = 100;

            int posX = (int) (Math.cos(angulo) * raio) + personagem.getPosicaoEmX();
            int posY = (int) (Math.sin(angulo) * raio) + personagem.getPosicaoEmY();

            super.setPosicaoEmX(posX);
            super.setPosicaoEmY(posY);
        }
    }

    // Corrigir este método
    public boolean verificarVisibilidade() {
        boolean visivel = super.isVisivel(); // obtém o valor padrão de visibilidade da classe pai

        if (super.getPosicaoEmX() > 1600 || super.getPosicaoEmX() < 0 || super.getPosicaoEmY() < 0
                || super.getPosicaoEmY() > 960) {
            visivel = false;
        }

        return visivel;
    }

}