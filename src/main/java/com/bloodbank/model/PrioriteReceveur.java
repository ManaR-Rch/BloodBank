package com.bloodbank.model;

/**
 * Enumération pour les priorités possibles d'un receveur
 */
public enum PrioriteReceveur {
    CRITIQUE("Critique"),
    URGENT("Urgent"),
    NORMAL("Normal");
    
    private final String description;
    
    PrioriteReceveur(String description) {
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
