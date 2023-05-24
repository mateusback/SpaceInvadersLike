package br.ifpr.jogo.principal;

import javax.swing.JFrame;

import br.ifpr.jogo.modelo.Fase;;

public class Principal extends JFrame{

    public Principal() {
        Fase fase = new Fase();
        add(fase);
        setVisible(true);
        setTitle("Space Invaders");
        setResizable(false);
        setSize(1000, 1000);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        Principal principal = new Principal();
    }
}
