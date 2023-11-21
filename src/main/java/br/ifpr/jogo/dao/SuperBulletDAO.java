package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.bullet.SuperBullet;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class SuperBulletDAO {

    public void saveOrUpdateSuperBullet(SuperBullet superBullet) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(superBullet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SuperBullet getSuperBullet(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(SuperBullet.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<SuperBullet> getAllSuperBullets(int playerId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM SuperBullet WHERE player.id = :playerId";
            Query<SuperBullet> query = session.createQuery(hql, SuperBullet.class);
            query.setParameter("playerId", playerId);
            List<SuperBullet> superBullets = query.list();

            for (SuperBullet superBullet : superBullets) {
                superBullet.load();
            }

           return superBullets;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}