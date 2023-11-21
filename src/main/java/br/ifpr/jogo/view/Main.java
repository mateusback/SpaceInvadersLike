package br.ifpr.jogo.view;

import javax.swing.JFrame;

import br.ifpr.jogo.controller.LevelController;
import br.ifpr.jogo.controller.MainController;
import br.ifpr.jogo.model.level.LevelModel;
import org.hibernate.Session;

import static br.ifpr.jogo.util.ScreenConstants.*;
import static br.ifpr.jogo.conexao.HibernateUtil.getSession;

public class Main extends JFrame {
    private MainController mainController;
    public Main() {
        this.mainController = new MainController(this);
        LevelController levelController = new LevelController();
        mainController.mainInit(levelController);
    }
    public Main(LevelModel levelModel) {
        this.mainController = new MainController(this);
        LevelController levelController = new LevelController(levelModel);
        mainController.mainInit(levelController);
    }

    public static void main(String[] args) {
        Session session = getSession();
        StartScreen startScreen = new StartScreen();

    }
}
