package com.bloodbank.service;

import com.bloodbank.model.Donneur;
import com.bloodbank.model.Receveur;
import java.util.List;
import java.util.ArrayList;

public class CompatibilityService {
    
    private DonneurService donneurService;
    private ReceveurService receveurService;
    
    public CompatibilityService() {
        this.donneurService = new DonneurService();
        this.receveurService = new ReceveurService();
    }
    
    /**
     * Vérifier la compatibilité entre un donneur et un receveur
     */
    public boolean estCompatible(Donneur donneur, Receveur receveur) {
        if (donneur == null || receveur == null) {
            return false;
        }
        
        // Règles de compatibilité des groupes sanguins
        return estCompatibleGroupeSanguin(donneur.getGroupeSanguin(), receveur.getGroupeSanguin());
    }
    
    /**
     * Trouver les donneurs compatibles pour un receveur donné
     */
    public List<Donneur> trouverDonneursCompatible(Receveur receveur) {
        List<Donneur> donneursCompatible = new ArrayList<>();
        List<Donneur> donneursDisponibles = donneurService.getDonneursDisponibles();
        
        for (Donneur donneur : donneursDisponibles) {
            if (estCompatible(donneur, receveur)) {
                donneursCompatible.add(donneur);
            }
        }
        
        return donneursCompatible;
    }
    
    /**
     * Trouver les receveurs compatibles pour un donneur donné
     */
    public List<Receveur> trouverReceveursCompatible(Donneur donneur) {
        List<Receveur> receveursCompatible = new ArrayList<>();
        List<Receveur> receveursEnAttente = receveurService.getReceveursEnAttente();
        
        for (Receveur receveur : receveursEnAttente) {
            if (estCompatible(donneur, receveur)) {
                receveursCompatible.add(receveur);
            }
        }
        
        return receveursCompatible;
    }
    
    /**
     * Vérifier la compatibilité des groupes sanguins
     * Règles ABO et Rhésus
     */
    private boolean estCompatibleGroupeSanguin(String groupeDonneur, String groupeReceveur) {
        if (groupeDonneur == null || groupeReceveur == null) {
            return false;
        }
        
        // Extraction du groupe ABO et du facteur Rhésus
        String groupeABODonneur = groupeDonneur.substring(0, groupeDonneur.length() - 1);
        String rhésusDonneur = groupeDonneur.substring(groupeDonneur.length() - 1);
        
        String groupeABOReceveur = groupeReceveur.substring(0, groupeReceveur.length() - 1);
        String rhésusReceveur = groupeReceveur.substring(groupeReceveur.length() - 1);
        
        // Compatibilité ABO
        boolean compatibleABO = estCompatibleABO(groupeABODonneur, groupeABOReceveur);
        
        // Compatibilité Rhésus (le donneur doit avoir le même Rhésus ou être négatif)
        boolean compatibleRhésus = rhésusDonneur.equals("-") || rhésusDonneur.equals(rhésusReceveur);
        
        return compatibleABO && compatibleRhésus;
    }
    
    /**
     * Vérifier la compatibilité ABO
     */
    private boolean estCompatibleABO(String groupeDonneur, String groupeReceveur) {
        // O peut donner à tous
        if ("O".equals(groupeDonneur)) {
            return true;
        }
        
        // A peut donner à A et AB
        if ("A".equals(groupeDonneur)) {
            return "A".equals(groupeReceveur) || "AB".equals(groupeReceveur);
        }
        
        // B peut donner à B et AB
        if ("B".equals(groupeDonneur)) {
            return "B".equals(groupeReceveur) || "AB".equals(groupeReceveur);
        }
        
        // AB ne peut donner qu'à AB
        if ("AB".equals(groupeDonneur)) {
            return "AB".equals(groupeReceveur);
        }
        
        return false;
    }
    
    /**
     * Obtenir les statistiques de compatibilité
     */
    public String obtenirStatistiquesCompatibility() {
        List<Donneur> donneursDisponibles = donneurService.getDonneursDisponibles();
        List<Receveur> receveursEnAttente = receveurService.getReceveursEnAttente();
        
        int totalCompatibilites = 0;
        for (Donneur donneur : donneursDisponibles) {
            totalCompatibilites += trouverReceveursCompatible(donneur).size();
        }
        
        return String.format("Compatibilités possibles: %d entre %d donneurs et %d receveurs", 
                           totalCompatibilites, donneursDisponibles.size(), receveursEnAttente.size());
    }
}
