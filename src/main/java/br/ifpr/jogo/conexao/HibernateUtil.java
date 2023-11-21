package br.ifpr.jogo.conexao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory SESSION_FACTORY;
    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Criação Inicial da SessionFactory falhou! " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }
    public static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

}