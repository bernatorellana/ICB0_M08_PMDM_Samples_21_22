package com.example.sqlite.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RenameTable;

@Entity(tableName = "persona")
public class Persona {

    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @NonNull
    @ColumnInfo(name = "nom")
    String nom;

    @NonNull
    @ColumnInfo(name = "pes")
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
