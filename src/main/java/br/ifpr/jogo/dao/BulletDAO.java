package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.bullet.Bullet;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Collections;
import java.util.List;

public class BulletDAO {

    public void saveOrUpdateBullet(Bullet bullet) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(bullet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bullet getBullet(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Bullet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Bullet> getAllBullets(int playerId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM Bullet WHERE player.id = :playerId";
            Query<Bullet> query = session.createQuery(hql, Bullet.class);
            query.setParameter("playerId", playerId);
            List<Bullet> bullets = query.list();

            return bullets;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}