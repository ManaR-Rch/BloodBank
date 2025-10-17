package com.bloodbank.model;

/**
 * Enumération pour les statuts possibles d'un receveur
 */
public enum StatutReceveur {
    EN_ATTENTE("En attente"),
    SATISFAIT("Satisfait"),
    SERVI("Servi");
    
    private final String description;
    
    StatutReceveur(String description) {
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
