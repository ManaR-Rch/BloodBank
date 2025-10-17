package com.bloodbank.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entité Receveur représentant un receveur de sang
 */
@Entity
@Table(name = "receveurs")
public class Receveur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nom", nullable = false, length = 50)
    private String nom;
    
    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;
    
    @Column(name = "cin", nullable = false, unique = true, length = 20)
    private String cin;
    
    @Column(name = "telephone", length = 20)
    private String telephone;
    
    @Column(name = "groupe_sanguin", nullable = false, length = 5)
    private String groupeSanguin;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "priorite", nullable = false)
    private PrioriteReceveur priorite; // CRITIQUE, URGENT, NORMAL
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutReceveur statut;   // EN_ATTENTE, SATISFAIT

    // Getters et Setters low code
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getGroupeSanguin() { return groupeSanguin; }
    public void setGroupeSanguin(String groupeSanguin) { this.groupeSanguin = groupeSanguin; }

    public PrioriteReceveur getPriorite() { return priorite; }
    public void setPriorite(PrioriteReceveur priorite) { this.priorite = priorite; }

    public StatutReceveur getStatut() { return statut; }
    public void setStatut(StatutReceveur statut) { this.statut = statut; }
    
    // Méthodes utilitaires
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receveur receveur = (Receveur) o;
        return Objects.equals(id, receveur.id) && Objects.equals(cin, receveur.cin);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, cin);
    }
    
    @Override
    public String toString() {
        return "Receveur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", groupeSanguin='" + groupeSanguin + '\'' +
                ", priorite=" + priorite +
                ", statut=" + statut +
                '}';
    }
}
