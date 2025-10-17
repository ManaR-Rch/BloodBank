package com.bloodbank.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entité Donneur représentant un donneur de sang
 */
@Entity
@Table(name = "donneurs")
public class Donneur {
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
    
    @Column(name = "poids", nullable = false)
    private double poids;
    
    @Column(name = "age", nullable = false)
    private int age;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutDonneur statut; // DISPO, NON_DISPO, NON_ELIGIBLE

    // Getters et Setters (low code)
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

    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public StatutDonneur getStatut() { return statut; }
    public void setStatut(StatutDonneur statut) { this.statut = statut; }
    
    // Méthodes utilitaires
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donneur donneur = (Donneur) o;
        return Objects.equals(id, donneur.id) && Objects.equals(cin, donneur.cin);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, cin);
    }
    
    @Override
    public String toString() {
        return "Donneur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", groupeSanguin='" + groupeSanguin + '\'' +
                ", statut=" + statut +
                '}';
    }
}
