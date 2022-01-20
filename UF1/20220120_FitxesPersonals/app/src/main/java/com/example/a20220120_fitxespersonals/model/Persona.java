package com.example.a20220120_fitxespersonals.model;

import com.example.a20220120_fitxespersonals.R;

import java.util.ArrayList;
import java.util.List;

public class Persona {

    private static List<Persona> sPersones;
    public static List<Persona> getPersones() {
        if(sPersones==null) {
            sPersones = new ArrayList<>();
            sPersones.add(new Persona(
                    "Paco",
                    "Maroto",
                    R.drawable.img,
                    "paco@maroto.com","600123122", Sexe.HOME,
                    false,
                    Pais.getPaisos().get(0)
            ));
        }
        return sPersones;
    }

    private int mRecursImatge;
    private String mNom;
    private String mCognom;
    private String mEmail;
    private String mTelefon;
    private Sexe mSexe;
    private boolean mEsActiu;
    private Pais mPais;

    public Persona(String mNom, String mCognom, int imatge, String mEmail, String mTelefon, Sexe mSexe, boolean mEsActiu, Pais mPais) {
        this.setNom(mNom);
        this.setRecursImatge(imatge);
        this.setCognom(mCognom);
        this.setEmail(mEmail);
        this.setTelefon(mTelefon);
        this.setSexe(mSexe);
        this.setEsActiu(mEsActiu);
        this.setPais(mPais);
    }
    public int getRecursImatge() {
        return mRecursImatge;
    }

    public void setRecursImatge(int mRecursImatge) {
        this.mRecursImatge = mRecursImatge;
    }

    public String getNom() {
        return mNom;
    }

    public void setNom(String mNom) {
        this.mNom = mNom;
    }

    public String getCognom() {
        return mCognom;
    }

    public void setCognom(String mCognom) {
        this.mCognom = mCognom;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getTelefon() {
        return mTelefon;
    }

    public void setTelefon(String mTelefon) {
        this.mTelefon = mTelefon;
    }

    public Sexe getSexe() {
        return mSexe;
    }

    public void setSexe(Sexe mSexe) {
        this.mSexe = mSexe;
    }

    public boolean ismEsActiu() {
        return mEsActiu;
    }

    public void setEsActiu(boolean mEsActiu) {
        this.mEsActiu = mEsActiu;
    }

    public Pais getPais() {
        return mPais;
    }

    public void setPais(Pais mPais) {
        this.mPais = mPais;
    }
}
