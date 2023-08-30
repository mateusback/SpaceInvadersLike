package br.ifpr.jogo.principal;

import javax.swing.JFrame;

import br.ifpr.jogo.modelo.Fase;;

public class Principal extends JFrame {
    public static int ALTURA_DA_JANELA = 960;
    public static int LARGURA_DA_JANELA = 1600;

    public Principal() {
        Fase fase = new Fase();
        add(fase);
        setVisible(true);
        setTitle("Farm Invaders");
        setResizable(false);
        setSize(1600, ALTURA_DA_JANELA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();

    }
}
