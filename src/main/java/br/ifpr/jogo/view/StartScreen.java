package br.ifpr.jogo.view;

import br.ifpr.jogo.controller.StartScreenController;

import javax.swing.*;

public class StartScreen extends JFrame {
    public StartScreenController startScreenController;
    public StartScreen() {
        startScreenController = new StartScreenController(this);
    }
}
