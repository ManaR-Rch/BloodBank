package com.bloodbank.dao;

import com.bloodbank.model.Receveur;
import com.bloodbank.model.StatutReceveur;
import com.bloodbank.model.PrioriteReceveur;
import com.bloodbank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * DAO Hibernate pour les receveurs
 * Version avec Hibernate pour remplacer la version en mémoire
 */
public class HibernateReceveurDAO {

    private SessionFactory sessionFactory;

    public HibernateReceveurDAO() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * Ajouter un receveur
     */
    public void ajouter(Receveur receveur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(receveur);
            transaction.commit();
            System.out.println("✅ Receveur ajouté avec succès : " + receveur.getNom());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de l'ajout du receveur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Récupérer tous les receveurs
     */
    public List<Receveur> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Receveur> query = session.createQuery("FROM Receveur", Receveur.class);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la récupération des receveurs : " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Retourne une liste vide en cas d'erreur
        }
    }

    /**
     * Trouver un receveur par ID
     */
    public Receveur findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Receveur.class, id);
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche du receveur : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Mettre à jour un receveur
     */
    public void update(Receveur receveur) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(receveur);
            transaction.commit();
            System.out.println("✅ Receveur mis à jour avec succès : " + receveur.getNom());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de la mise à jour du receveur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Supprimer un receveur
     */
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Receveur receveur = session.get(Receveur.class, id);
            if (receveur != null) {
                session.delete(receveur);
                System.out.println("✅ Receveur supprimé avec succès : " + receveur.getNom());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("❌ Erreur lors de la suppression du receveur : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Récupérer les receveurs par statut
     */
    public List<Receveur> getByStatut(StatutReceveur statut) {
        try (Session session = sessionFactory.openSession()) {
            Query<Receveur> query = session.createQuery(
                "FROM Receveur r WHERE r.statut = :statut", Receveur.class);
            query.setParameter("statut", statut);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche par statut : " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Récupérer les receveurs par priorité
     */
    public List<Receveur> getByPriorite(PrioriteReceveur priorite) {
        try (Session session = sessionFactory.openSession()) {
            Query<Receveur> query = session.createQuery(
                "FROM Receveur r WHERE r.priorite = :priorite", Receveur.class);
            query.setParameter("priorite", priorite);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche par priorité : " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Récupérer les receveurs par groupe sanguin
     */
    public List<Receveur> getByGroupeSanguin(String groupeSanguin) {
        try (Session session = sessionFactory.openSession()) {
            Query<Receveur> query = session.createQuery(
                "FROM Receveur r WHERE r.groupeSanguin = :groupeSanguin", Receveur.class);
            query.setParameter("groupeSanguin", groupeSanguin);
            return query.list();
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la recherche par groupe sanguin : " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
}
