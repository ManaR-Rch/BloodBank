package com.bloodbank.dao;

import com.bloodbank.model.Receveur;
import com.bloodbank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReceveurDAO {

    private SessionFactory sessionFactory;

    public ReceveurDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // Ajouter un receveur
    public void ajouter(Receveur receveur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(receveur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Retourner tous les receveurs
    public List<Receveur> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Receveur> query = session.createQuery("FROM Receveur", Receveur.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Liste vide en cas d'erreur
        }
    }
    
    // Trouver un receveur par ID
    public Receveur findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Receveur.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Mettre à jour un receveur
    public void update(Receveur receveur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(receveur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Supprimer un receveur
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Receveur receveur = session.get(Receveur.class, id);
            if (receveur != null) {
                session.delete(receveur);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
