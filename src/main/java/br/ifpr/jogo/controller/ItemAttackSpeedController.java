package br.ifpr.jogo.controller;

import br.ifpr.jogo.dao.ItemAttackSpeedDAO;
import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeed;
import br.ifpr.jogo.serivces.item.ItemAttackSpeedService;

public class ItemAttackSpeedController{
    private ItemAttackSpeedService itemAttackSpeedService;
    private ItemAttackSpeed itemAttackSpeed;
    private ItemAttackSpeedDAO itemAttackSpeedDAO;
    public ItemAttackSpeedController(ItemAttackSpeed itemAttackSpeed) {
        this.setItemAttackSpeedService(new ItemAttackSpeedService(itemAttackSpeed));
        this.setItemAttackSpeed(itemAttackSpeed);
        this.itemAttackSpeedDAO = new ItemAttackSpeedDAO();
    }

    public void load() {
        itemAttackSpeedService.load();
    }

    public ItemAttackSpeedService getItemAttackSpeedService() {
        return itemAttackSpeedService;
    }

    public void setItemAttackSpeedService(ItemAttackSpeedService itemAttackSpeedService) {
        this.itemAttackSpeedService = itemAttackSpeedService;
    }

    public ItemAttackSpeed getItemAttackSpeed() {
        return itemAttackSpeed;
    }

    public void setItemAttackSpeed(ItemAttackSpeed itemAttackSpeed) {
        this.itemAttackSpeed = itemAttackSpeed;}
    public void saveOrUpdateItemAttackSpeed(ItemAttackSpeed item) {
        itemAttackSpeedDAO.saveOrUpdateItemAttackSpeed(item);
    }
    public ItemAttackSpeed getItemAttackSpeed(Integer id) {
        return itemAttackSpeedDAO.getItemAttackSpeed(id);
    }

}