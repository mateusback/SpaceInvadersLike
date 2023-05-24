package br.ifpr.jogo.principal;

import javax.swing.JFrame;

public class Principal extends JFrame{

    public Principal() {
        setVisible(true);
        setTitle("Space Invaders");
        setSize(800, 600);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        Principal principal = new Principal();
    }
}
