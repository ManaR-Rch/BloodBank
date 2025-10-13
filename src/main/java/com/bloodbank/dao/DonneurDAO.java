package com.bloodbank.dao;

import com.bloodbank.model.Donneur;
import java.util.ArrayList;
import java.util.List;

public class DonneurDAO {

    // Simulation base de données (en mémoire)
    private static List<Donneur> donneurs = new ArrayList<>();

    // Ajouter un donneur
    public void ajouter(Donneur d) {
        donneurs.add(d);
    }

    // Retourner tous les donneurs
    public List<Donneur> getAll() {
        return donneurs;
    }
}
