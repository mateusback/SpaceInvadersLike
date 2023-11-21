package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.item.ItemSpeed;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemSpeedDAO {
    public void saveOrUpdateItemSpeed(ItemSpeed item) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemSpeed getItemSpeed(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(ItemSpeed.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
