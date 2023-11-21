package br.ifpr.jogo.dao;

import br.ifpr.jogo.model.graphicelement.Cloud;
import br.ifpr.jogo.conexao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

public class CloudDAO {

    public void saveOrUpdateCloud(Cloud cloud) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(cloud);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cloud getCloud(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Cloud.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Cloud> getAllClouds() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Cloud> criteria = builder.createQuery(Cloud.class);
            Root<Cloud> root = criteria.from(Cloud.class);
            criteria.select(root);

            return session.createQuery(criteria).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}