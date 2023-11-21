package br.ifpr.jogo.controller;

import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.ItemSpeedDAO;
import br.ifpr.jogo.model.graphicelement.item.ItemSpeed;
import br.ifpr.jogo.serivces.item.ItemSpeedService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemSpeedController{
    private ItemSpeed itemSpeed;
    private ItemSpeedService itemSpeedService;
    private ItemSpeedDAO itemSpeedDAO;
    public ItemSpeedController(ItemSpeed itemSpeed) {
        this.setItemSpeedService(new ItemSpeedService(itemSpeed));
        this.setItemSpeed(itemSpeed);
        this.itemSpeedDAO = new ItemSpeedDAO();
    }
    public void load() {
        itemSpeedService.load();
    }

    public void update() {
        itemSpeedService.update();
    }

    public ItemSpeed getItemSpeed() {
        return itemSpeed;
    }

    public void setItemSpeed(ItemSpeed itemSpeed) {
        this.itemSpeed = itemSpeed;
    }

    public ItemSpeedService getItemSpeedService() {
        return itemSpeedService;
    }

    public void setItemSpeedService(ItemSpeedService itemSpeedService) {
        this.itemSpeedService = itemSpeedService;
    }
    public void saveOrUpdateItemSpeed(ItemSpeed item) {
        itemSpeedDAO.saveOrUpdateItemSpeed(item);
    }

    public ItemSpeed getItemSpeed(Integer id) {
        return itemSpeedDAO.getItemSpeed(id);
    }
}
