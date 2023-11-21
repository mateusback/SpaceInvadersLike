package br.ifpr.jogo.serivces;

import br.ifpr.jogo.controller.LevelController;
import br.ifpr.jogo.controller.MainController;
import br.ifpr.jogo.view.Main;

import javax.swing.*;

import static br.ifpr.jogo.util.ScreenConstants.WINDOW_HEIGHT;
import static br.ifpr.jogo.util.ScreenConstants.WINDOW_WIDTH;

public class MainService extends JFrame {
    private Main main;

    public MainService(Main main){
        this.main = main;
    }
    public void mainInit(LevelController levelController){
        add(levelController);
        setVisible(true);
        setTitle("Farm Invaders");
        setResizable(false);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
