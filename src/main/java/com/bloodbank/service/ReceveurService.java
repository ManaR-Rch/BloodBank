package com.bloodbank.service;

import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.model.Receveur;
import com.bloodbank.model.StatutReceveur;
import com.bloodbank.model.PrioriteReceveur;
import java.util.List;

public class ReceveurService {
    
    private ReceveurDAO receveurDAO;
    
    public ReceveurService() {
        this.receveurDAO = new ReceveurDAO();
    }
    
    /**
     * Ajouter un nouveau receveur
     */
    public void ajouterReceveur(Receveur receveur) {
        // Définir le statut par défaut
        if (receveur.getStatut() == null) {
            receveur.setStatut(StatutReceveur.EN_ATTENTE);
        }
        // Définir la priorité par défaut si manquante
        if (receveur.getPriorite() == null) {
            receveur.setPriorite(determinerPriorite(receveur));
        }
        receveurDAO.ajouter(receveur);
    }
    
    /**
     * Récupérer tous les receveurs
     */
    public List<Receveur> getAllReceveurs() {
        return receveurDAO.getAll();
    }
    
    /**
     * Récupérer les receveurs en attente
     */
    public List<Receveur> getReceveursEnAttente() {
        return receveurDAO.getAll().stream()
                .filter(r -> StatutReceveur.EN_ATTENTE.equals(r.getStatut()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Récupérer les receveurs par priorité
     */
    public List<Receveur> getReceveursParPriorite(PrioriteReceveur priorite) {
        return receveurDAO.getAll().stream()
                .filter(r -> priorite.equals(r.getPriorite()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Marquer un receveur comme satisfait
     */
    public void marquerSatisfait(Long receveurId) {
        Receveur receveur = receveurDAO.findById(receveurId);
        if (receveur != null) {
            receveur.setStatut(StatutReceveur.SATISFAIT);
            receveurDAO.update(receveur);
        }
    }

    /**
     * Marquer un receveur comme servi après affectation d'un donneur
     */
    public void marquerServi(Long receveurId) {
        Receveur receveur = receveurDAO.findById(receveurId);
        if (receveur != null) {
            receveur.setStatut(StatutReceveur.SERVI);
            receveurDAO.update(receveur);
        }
    }
    
    /**
     * Déterminer la priorité d'un receveur selon des critères médicaux
     */
    public PrioriteReceveur determinerPriorite(Receveur receveur) {
        // Logique pour déterminer la priorité
        // Pour l'instant, on utilise une logique simple basée sur le groupe sanguin
        if ("O-".equals(receveur.getGroupeSanguin())) {
            return PrioriteReceveur.CRITIQUE; // O- est le donneur universel
        } else if ("AB+".equals(receveur.getGroupeSanguin())) {
            return PrioriteReceveur.NORMAL; // AB+ peut recevoir de tous
        } else {
            return PrioriteReceveur.URGENT;
        }
    }
}
