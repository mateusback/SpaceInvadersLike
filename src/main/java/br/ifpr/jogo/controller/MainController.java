package br.ifpr.jogo.controller;

import br.ifpr.jogo.view.Main;
import br.ifpr.jogo.serivces.MainService;

import javax.swing.*;

public class MainController extends JFrame {
    private Main main;
    private MainService mainService;

    public MainController(Main main){
        this.main = main;
        this.mainService = new MainService(main);
    }
    public void mainInit(LevelController levelController){
        mainService.mainInit(levelController);
    }
}
