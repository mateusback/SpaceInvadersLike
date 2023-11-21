package br.ifpr.jogo.controller;

import br.ifpr.jogo.conexao.HibernateUtil;
import br.ifpr.jogo.dao.ItemAttackSpeedDAO;
import br.ifpr.jogo.dao.ItemHealDAO;
import br.ifpr.jogo.model.graphicelement.item.ItemHeal;
import br.ifpr.jogo.serivces.item.ItemHealService;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemHealController{
    private ItemHeal itemHeal;
    private ItemHealService itemHealService;
    private ItemHealDAO itemHealDAO;
    public ItemHealController(ItemHeal itemHeal) {
        this.setItemHealService(new ItemHealService(itemHeal));
        this.setItemHeal(itemHeal);
        this.itemHealDAO = new ItemHealDAO();
    }

    public void load() {
        itemHealService.load();
    }

    public ItemHeal getItemHeal() {
        return itemHeal;
    }

    public void setItemHeal(ItemHeal itemHeal) {
        this.itemHeal = itemHeal;
    }

    public ItemHealService getItemHealService() {
        return itemHealService;
    }

    public void setItemHealService(ItemHealService itemHealService) {
        this.itemHealService = itemHealService;
    }

    public void saveOrUpdateItemHeal(ItemHeal item) {
        itemHealDAO.saveOrUpdateItemHeal(item);
    }

    public ItemHeal getItemHeal(Integer id) {
        return itemHealDAO.getItemHeal(id);
    }
}
