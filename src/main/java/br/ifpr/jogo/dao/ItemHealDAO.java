package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.item.ItemHeal;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemHealDAO {
    public void saveOrUpdateItemHeal(ItemHeal item) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemHeal getItemHeal(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(ItemHeal.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}