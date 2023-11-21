package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.Player;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDAO {

    public void saveOrUpdatePlayer(Player player) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(player);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Player.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Integer> getAvailableSaveIds() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Integer> query = session.createQuery("SELECT id FROM Player", Integer.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
