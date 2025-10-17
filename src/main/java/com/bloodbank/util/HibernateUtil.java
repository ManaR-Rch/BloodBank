package com.bloodbank.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Classe utilitaire pour gérer la SessionFactory Hibernate
 * Pattern Singleton pour s'assurer qu'une seule instance existe
 */
public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    // Constructeur privé pour empêcher l'instanciation
    private HibernateUtil() {}
    
    /**
     * Obtient la SessionFactory Hibernate
     * @return SessionFactory configurée
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Charger la configuration depuis hibernate.cfg.xml
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                
                // Créer le ServiceRegistry
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                
                // Construire la SessionFactory
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                
                System.out.println("✅ SessionFactory Hibernate créée avec succès !");
                
            } catch (Exception e) {
                System.err.println("❌ Erreur lors de la création de SessionFactory : " + e.getMessage());
                e.printStackTrace();
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
    
    /**
     * Ferme la SessionFactory
     * À appeler lors de l'arrêt de l'application
     */
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("✅ SessionFactory Hibernate fermée avec succès !");
        }
    }
}
