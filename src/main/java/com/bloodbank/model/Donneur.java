package com.bloodbank.model;

public class Donneur {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String groupeSanguin;
    private double poids;
    private int age;
    private String statut; // DISPO, NON_DISPO, NON_ELIGIBLE

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

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
