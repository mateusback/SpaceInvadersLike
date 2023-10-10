package br.ifpr.jogo.principal;

import javax.swing.JFrame;

import br.ifpr.jogo.modelo.Fase;
import org.hibernate.Session;

import static br.ifpr.jogo.conexao.HibernateUtil.getSession;

public class Principal extends JFrame {
    public static int ALTURA_DA_JANELA = 960;
    public static int LARGURA_DA_JANELA = 1600;

    public Principal() {
        Fase fase = new Fase();
        add(fase);
        setVisible(true);
        setTitle("Farm Invaders");
        setResizable(false);
        setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Session session = getSession();
        Principal principal = new Principal();

    }
}
