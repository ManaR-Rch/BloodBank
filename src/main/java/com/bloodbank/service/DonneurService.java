package com.bloodbank.service;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.model.Donneur;
import com.bloodbank.model.StatutDonneur;
import java.util.List;

public class DonneurService {
    
    private DonneurDAO donneurDAO;
    
    public DonneurService() {
        this.donneurDAO = new DonneurDAO();
    }
    
    /**
     * Ajouter un nouveau donneur avec validation métier
     */
    public void ajouterDonneur(Donneur donneur) {
        // Statut par défaut = DISPONIBLE
        donneur.setStatut(StatutDonneur.DISPO);
        
        // Vérifier l'éligibilité et ajuster le statut si nécessaire
        if (!estEligible(donneur)) {
            donneur.setStatut(StatutDonneur.NON_ELIGIBLE);
        }
        
        donneurDAO.ajouter(donneur);
    }
    
    /**
     * Récupérer tous les donneurs
     */
    public List<Donneur> getAllDonneurs() {
        return donneurDAO.getAll();
    }
    
    /**
     * Récupérer les donneurs disponibles
     */
    public List<Donneur> getDonneursDisponibles() {
        return donneurDAO.getAll().stream()
                .filter(d -> StatutDonneur.DISPO.equals(d.getStatut()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Vérifier l'éligibilité d'un donneur selon les règles simples
     */
    public boolean estEligible(Donneur donneur) {
        // Règles simples d'éligibilité :
        // - Âge >= 18 ans
        // - Poids >= 50 kg
        
        if (donneur.getAge() < 18) {
            return false;
        }
        
        if (donneur.getPoids() < 50.0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Marquer un donneur comme non disponible
     */
    public void marquerNonDisponible(Long donneurId) {
        Donneur donneur = donneurDAO.findById(donneurId);
        if (donneur != null) {
            donneur.setStatut(StatutDonneur.NON_DISPO);
            donneurDAO.update(donneur);
        }
    }
}
