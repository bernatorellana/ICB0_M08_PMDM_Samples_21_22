package com.example.sqlite.bd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sqlite.model.Persona;

@Database(entities = {Persona.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonaDAO getPersonaDAO();
}