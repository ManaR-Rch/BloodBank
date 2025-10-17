package com.bloodbank.model;

/**
 * Enumération pour les statuts possibles d'un donneur
 */
public enum StatutDonneur {
    DISPO("Disponible"),
    NON_DISPO("Non disponible"),
    NON_ELIGIBLE("Non éligible");
    
    private final String description;
    
    StatutDonneur(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return description;
    }
}
