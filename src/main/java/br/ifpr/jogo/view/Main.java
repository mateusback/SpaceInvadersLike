package br.ifpr.jogo.view;

import javax.swing.JFrame;

import br.ifpr.jogo.model.level.Level;
import br.ifpr.jogo.model.level.LevelModel;
import org.hibernate.Session;

import static br.ifpr.jogo.conexao.HibernateUtil.getSession;
import static br.ifpr.jogo.util.ScreenConstants.*;

//View
public class Main extends JFrame {

    public Main() {
        Level level = new Level();
        MainInit(level);
    }
    public Main(LevelModel levelModel) {
        Level level = new Level(levelModel);
        MainInit(level);
    }

    private void MainInit(Level level){
        add(level);
        setVisible(true);
        setTitle("Farm Invaders");
        setResizable(false);
        setSize(LARGURA_DA_JANELA, ALTURA_DA_JANELA);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Session session = getSession();
        StartScreen startScreen = new StartScreen();

    }
}
