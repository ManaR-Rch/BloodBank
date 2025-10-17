package com.bloodbank.dao;

import com.bloodbank.model.Donneur;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DonneurDAO {

    // Simulation base de données (en mémoire)
    private static List<Donneur> donneurs = new ArrayList<>();
    private static AtomicLong idCounter = new AtomicLong(1);

    // Ajouter un donneur
    public void ajouter(Donneur d) {
        if (d.getId() == null) {
            d.setId(idCounter.getAndIncrement());
        }
        donneurs.add(d);
    }

    // Retourner tous les donneurs
    public List<Donneur> getAll() {
        return new ArrayList<>(donneurs);
    }
    
    // Trouver un donneur par ID
    public Donneur findById(Long id) {
        return donneurs.stream()
                .filter(d -> id.equals(d.getId()))
                .findFirst()
                .orElse(null);
    }
    
    // Mettre à jour un donneur
    public void update(Donneur donneur) {
        for (int i = 0; i < donneurs.size(); i++) {
            if (donneurs.get(i).getId().equals(donneur.getId())) {
                donneurs.set(i, donneur);
                break;
            }
        }
    }
    
    // Supprimer un donneur
    public void delete(Long id) {
        donneurs.removeIf(d -> id.equals(d.getId()));
    }
}
