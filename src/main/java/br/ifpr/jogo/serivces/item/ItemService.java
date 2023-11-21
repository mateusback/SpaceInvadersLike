package br.ifpr.jogo.serivces.item;

import br.ifpr.jogo.model.graphicelement.Player;

public interface ItemService {
    void checkPlayerColision(Player player);
    void applyEffect(Player player);
}
