package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.Enemy;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Collections;
import java.util.List;

public class EnemyDAO {

    public void saveOrUpdateEnemy(Enemy enemy) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(enemy);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Enemy getEnemy(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Enemy.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Enemy> getAllEnemies(int playerId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM Enemy WHERE player.id = :playerId";
            Query<Enemy> query = session.createQuery(hql, Enemy.class);
            query.setParameter("playerId", playerId);
            List<Enemy> enemies = query.list();

            for (Enemy enemy : enemies) {
                enemy.getEnemyController().load();
            }

            return enemies;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}