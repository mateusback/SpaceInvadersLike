package br.ifpr.jogo.view;

import javax.swing.JFrame;

import br.ifpr.jogo.controller.LevelController;
import br.ifpr.jogo.model.level.LevelModel;

import static br.ifpr.jogo.util.ScreenConstants.*;

//View
public class Main extends JFrame {

    public Main() {
        LevelController levelController = new LevelController();
        MainInit(levelController);
    }
    public Main(LevelModel levelModel) {
        LevelController levelController = new LevelController(levelModel);
        MainInit(levelController);
    }

    private void MainInit(LevelController levelController){
        add(levelController);
        setVisible(true);
        setTitle("Farm Invaders");
        setResizable(false);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        //Session session = getSession();
        StartScreen startScreen = new StartScreen();

    }
}
