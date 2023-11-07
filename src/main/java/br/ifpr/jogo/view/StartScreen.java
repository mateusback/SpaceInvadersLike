package br.ifpr.jogo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    public StartScreen() {
        super("Tela Inicial do Jogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton newGameBt = new JButton("Iniciar um Novo Jogo");
        JButton loadGameBt = new JButton("Carregar");
        JButton quitBt = new JButton("Sair");

        panel.add(newGameBt);
        panel.add(loadGameBt);
        panel.add(quitBt);

        newGameBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main main = new Main();
            }
        });

        loadGameBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pensar pensar pensar
            }
        });

        quitBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StartScreen();
            }
        });
    }
}
