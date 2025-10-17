package com.bloodbank.service;

import com.bloodbank.model.Donneur;
import com.bloodbank.model.Receveur;
import java.util.ArrayList;
import java.util.List;

public class CompatibiliteService {
    private DonneurService donneurService;
    private ReceveurService receveurService;

    public CompatibiliteService() {
        this.donneurService = new DonneurService();
        this.receveurService = new ReceveurService();
    }

    /**
     * Vérifie la compatibilité de deux groupes sanguins.
     * Version simple: O- compatible avec tous / groupes identiques acceptés.
     */
    public boolean estCompatible(String groupeDonneur, String groupeReceveur) {
        if (groupeDonneur == null || groupeReceveur == null) {
            return false;
        }
        if ("O-".equalsIgnoreCase(groupeDonneur)) {
            return true;
        }
        return groupeDonneur.equalsIgnoreCase(groupeReceveur);
    }

    /**
     * Vérifie la compatibilité entre un donneur et un receveur (objets).
     */
    public boolean estCompatible(Donneur donneur, Receveur receveur) {
        if (donneur == null || receveur == null) {
            return false;
        }
        return estCompatible(donneur.getGroupeSanguin(), receveur.getGroupeSanguin());
    }

    /**
     * Trouver les donneurs compatibles pour un receveur.
     */
    public List<Donneur> trouverDonneursCompatible(Receveur receveur) {
        List<Donneur> compatibles = new ArrayList<>();
        for (Donneur d : donneurService.getDonneursDisponibles()) {
            if (estCompatible(d, receveur)) {
                compatibles.add(d);
            }
        }
        return compatibles;
    }

    /**
     * Trouver les receveurs compatibles pour un donneur.
     */
    public List<Receveur> trouverReceveursCompatible(Donneur donneur) {
        List<Receveur> compatibles = new ArrayList<>();
        for (Receveur r : receveurService.getReceveursEnAttente()) {
            if (estCompatible(donneur, r)) {
                compatibles.add(r);
            }
        }
        return compatibles;
    }

    /**
     * Statistiques simples (optionnel, correspondance minimale à l'ancien service).
     */
    public String obtenirStatistiquesCompatibility() {
        List<Donneur> donneurs = donneurService.getDonneursDisponibles();
        List<Receveur> receveurs = receveurService.getReceveursEnAttente();
        int total = 0;
        for (Donneur d : donneurs) {
            total += trouverReceveursCompatible(d).size();
        }
        return String.format("Compatibilités possibles: %d entre %d donneurs et %d receveurs", total, donneurs.size(), receveurs.size());
    }
}


