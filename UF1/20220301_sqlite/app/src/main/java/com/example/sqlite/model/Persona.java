package com.example.sqlite.model;




public class Persona {

    int id;
    String nom;
    double pes;

    public Persona(int id, String nom, double pes) {
        this.id = id;
        this.nom = nom;
        this.pes = pes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPes() {
        return pes;
    }

    public void setPes(double pes) {
        this.pes = pes;
    }

    @Override
    public String toString() {
        return "<p>Persona{" +
                "<b>id=</b>" + id +
                ", <b>nom=</b>'" + nom + '\'' +
                ", <b>pes=</b>" + pes +
                "}</p>";
    }
}
