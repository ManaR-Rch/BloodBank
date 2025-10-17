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
        // Validation métier avant ajout
        if (estEligible(donneur)) {
            donneur.setStatut(StatutDonneur.DISPO);
            donneurDAO.ajouter(donneur);
        } else {
            donneur.setStatut(StatutDonneur.NON_ELIGIBLE);
            donneurDAO.ajouter(donneur);
        }
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
     * Vérifier l'éligibilité d'un donneur selon les critères médicaux
     */
    public boolean estEligible(Donneur donneur) {
        // Critères d'éligibilité :
        // - Âge entre 18 et 65 ans
        // - Poids minimum 50 kg
        // - Groupe sanguin valide
        
        if (donneur.getAge() < 18 || donneur.getAge() > 65) {
            return false;
        }
        
        if (donneur.getPoids() < 50.0) {
            return false;
        }
        
        if (!estGroupeSanguinValide(donneur.getGroupeSanguin())) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Vérifier si le groupe sanguin est valide
     */
    private boolean estGroupeSanguinValide(String groupeSanguin) {
        return groupeSanguin != null && 
               (groupeSanguin.equals("A+") || groupeSanguin.equals("A-") ||
                groupeSanguin.equals("B+") || groupeSanguin.equals("B-") ||
                groupeSanguin.equals("AB+") || groupeSanguin.equals("AB-") ||
                groupeSanguin.equals("O+") || groupeSanguin.equals("O-"));
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
