package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.item.ItemAttackSpeed;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class ItemAttackSpeedDAO {
    public void saveOrUpdateItemAttackSpeed(ItemAttackSpeed item) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(item);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemAttackSpeed getItemAttackSpeed(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(ItemAttackSpeed.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
