package br.ifpr.jogo.controller;

import br.ifpr.jogo.view.StartScreen;
import br.ifpr.jogo.serivces.StartScreenService;

public class StartScreenController{
    private StartScreenService startScreenService;
    private StartScreen startScreen;
    public StartScreenController(StartScreen startScreen) {
        this.startScreen = startScreen;
        startScreenService = new StartScreenService(startScreen);
        startScreenService.initScreen();
    }

    public void initScreen(){
        startScreenService.initScreen();
    }
    public void loadGame(){
        startScreenService.loadGame();
    }
}
