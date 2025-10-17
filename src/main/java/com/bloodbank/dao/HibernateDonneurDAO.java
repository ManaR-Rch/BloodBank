package com.bloodbank.dao;

import com.bloodbank.model.Donneur;
import com.bloodbank.model.StatutDonneur;
import com.bloodbank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO Hibernate pour les donneurs
 * Version avec Hibernate pour remplacer la version en mémoire
 */
public class HibernateDonneurDAO {

    private SessionFactory sessionFactory;

    public HibernateDonneurDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * Ajouter un donneur
     */
    public void ajouter(Donneur donneur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(donneur);
            transaction.commit();
            System.out.println("✅ Donneur ajouté avec succès : " + donneur.getNom());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de l'ajout du donneur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Récupérer tous les donneurs
     */
    public List<Donneur> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Donneur> query = session.createQuery("FROM Donneur", Donneur.class);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la récupération des donneurs : " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Retourne une liste vide en cas d'erreur
        }
    }

    /**
     * Trouver un donneur par ID
     */
    public Donneur findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Donneur.class, id);
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche du donneur : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Mettre à jour un donneur
     */
    public void update(Donneur donneur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(donneur);
            transaction.commit();
            System.out.println("✅ Donneur mis à jour avec succès : " + donneur.getNom());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de la mise à jour du donneur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Supprimer un donneur
     */
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Donneur donneur = session.get(Donneur.class, id);
            if (donneur != null) {
                session.delete(donneur);
                System.out.println("✅ Donneur supprimé avec succès : " + donneur.getNom());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de la suppression du donneur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Récupérer les donneurs par statut
     */
    public List<Donneur> getByStatut(StatutDonneur statut) {
        try (Session session = sessionFactory.openSession()) {
            Query<Donneur> query = session.createQuery(
                "FROM Donneur d WHERE d.statut = :statut", Donneur.class);
            query.setParameter("statut", statut);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche par statut : " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Récupérer les donneurs par groupe sanguin
     */
    public List<Donneur> getByGroupeSanguin(String groupeSanguin) {
        try (Session session = sessionFactory.openSession()) {
            Query<Donneur> query = session.createQuery(
                "FROM Donneur d WHERE d.groupeSanguin = :groupeSanguin", Donneur.class);
            query.setParameter("groupeSanguin", groupeSanguin);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche par groupe sanguin : " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}
