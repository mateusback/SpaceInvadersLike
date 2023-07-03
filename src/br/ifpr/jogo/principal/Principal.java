package br.ifpr.jogo.principal;

import javax.swing.JFrame;

import br.ifpr.jogo.modelo.Fase;;

public class Principal extends JFrame {

    public Principal() {
        Fase fase = new Fase();
        add(fase);
        setVisible(true);
        setTitle("Farm Invaders");
        setResizable(false);
        setSize(1600, 960);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();

    }
}
