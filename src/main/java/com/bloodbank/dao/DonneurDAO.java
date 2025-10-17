package com.bloodbank.dao;

import com.bloodbank.model.Donneur;
import com.bloodbank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DonneurDAO {

    private SessionFactory sessionFactory;

    public DonneurDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // Ajouter un donneur
    public void ajouter(Donneur donneur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(donneur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Retourner tous les donneurs
    public List<Donneur> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Donneur> query = session.createQuery("FROM Donneur", Donneur.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Liste vide en cas d'erreur
        }
    }
    
    // Trouver un donneur par ID
    public Donneur findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Donneur.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Mettre à jour un donneur
    public void update(Donneur donneur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(donneur);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    // Supprimer un donneur
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Donneur donneur = session.get(Donneur.class, id);
            if (donneur != null) {
                session.delete(donneur);
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
