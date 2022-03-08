package com.example.sqlite.bd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sqlite.model.Persona;

import java.util.List;

@Dao
public interface PersonaDAO  {

    @Query( "select * from persona")
    List<Persona> getPersones();

    @Query("select * from persona where id = :id ")
    Persona getPersona(int id);

    @Delete
    void esborraPersona(Persona p);

    @Insert
    void insertaPersona(Persona p);

    @Update
    void actualitzaPersona(Persona p);
}
