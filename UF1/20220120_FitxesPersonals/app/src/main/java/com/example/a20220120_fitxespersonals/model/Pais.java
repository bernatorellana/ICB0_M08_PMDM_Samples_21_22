package com.example.a20220120_fitxespersonals.model;

import com.example.a20220120_fitxespersonals.R;

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private int  mCodi;
    private String mNom;

    private static List<Pais> sPaisos;
    public static List<Pais> getPaisos() {
        if(sPaisos==null) {
            sPaisos = new ArrayList<>();
            sPaisos.add(new Pais(
                    1,
                    "Spain"));
            sPaisos.add(new Pais(
                    2,
                    "France"));
        }
        return sPaisos;
    }


    public Pais(int mCodi, String mNom) {
        this.setCodi(mCodi);
        this.setNom(mNom);
    }


    public int getCodi() {
        return mCodi;
    }

    public void setCodi(int mCodi) {
        this.mCodi = mCodi;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String mNom) {
        this.mNom = mNom;
    }
}
