package br.ifpr.jogo.view;

import br.ifpr.jogo.dao.*;
import br.ifpr.jogo.model.graphicelement.*;
import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.model.level.LevelModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {
    public static boolean isALoadedGame = false;
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
                loadGame();
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
    public void loadGame(){
        PlayerDAO playerDAO = new PlayerDAO();
        List<Integer> saveIds = playerDAO.getAvailableSaveIds();

        if (saveIds.isEmpty()) {
            JOptionPane.showMessageDialog(StartScreen.this, "Nenhum save disponível.", "Carregar Jogo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Integer selectedSaveId = (Integer) JOptionPane.showInputDialog(StartScreen.this, "Escolha o save para carregar:", "Carregar Jogo", JOptionPane.QUESTION_MESSAGE, null, saveIds.toArray(), saveIds.get(0));
            if (selectedSaveId != null) {
                Player loadedPlayer = playerDAO.getPlayer(selectedSaveId);
                List<Enemy> loadedEnemies = new EnemyDAO().getAllEnemies(selectedSaveId);
                List<Cloud> loadedClouds = new CloudDAO().getAllClouds();
                List<Bullet> loadedBullets = new BulletDAO().getAllBullets(selectedSaveId);
                List<SuperBullet> loadedSuperBullets = new SuperBulletDAO().getAllSuperBullets(selectedSaveId);

                LevelModel levelModel = new LevelModel(loadedPlayer, loadedEnemies, loadedClouds, loadedBullets, loadedSuperBullets);
                Main main = new Main(levelModel);
            }
        }
    }
}
