package com.bloodbank.dao;

import com.bloodbank.model.Receveur;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ReceveurDAO {

    // Simulation base de données (en mémoire)
    private static List<Receveur> receveurs = new ArrayList<>();
    private static AtomicLong idCounter = new AtomicLong(1);

    // Ajouter un receveur
    public void ajouter(Receveur r) {
        if (r.getId() == null) {
            r.setId(idCounter.getAndIncrement());
        }
        receveurs.add(r);
    }

    // Retourner tous les receveurs
    public List<Receveur> getAll() {
        return new ArrayList<>(receveurs);
    }
    
    // Trouver un receveur par ID
    public Receveur findById(Long id) {
        return receveurs.stream()
                .filter(r -> id.equals(r.getId()))
                .findFirst()
                .orElse(null);
    }
    
    // Mettre à jour un receveur
    public void update(Receveur receveur) {
        for (int i = 0; i < receveurs.size(); i++) {
            if (receveurs.get(i).getId().equals(receveur.getId())) {
                receveurs.set(i, receveur);
                break;
            }
        }
    }
    
    // Supprimer un receveur
    public void delete(Long id) {
        receveurs.removeIf(r -> id.equals(r.getId()));
    }
}
