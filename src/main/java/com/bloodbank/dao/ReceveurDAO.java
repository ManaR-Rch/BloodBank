package com.bloodbank.dao;

import com.bloodbank.model.Receveur;
import java.util.ArrayList;
import java.util.List;

public class ReceveurDAO {

    // Simulation base de données (en mémoire)
    private static List<Receveur> receveurs = new ArrayList<>();

    // Ajouter un receveur
    public void ajouter(Receveur r) {
        receveurs.add(r);
    }

    // Retourner tous les receveurs
    public List<Receveur> getAll() {
        return receveurs;
    }
}
