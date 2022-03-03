package com.example.sqlite.modelview;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.sqlite.bd.DatabaseHelper;
import com.example.sqlite.model.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class PersonesViewModel extends AndroidViewModel {

    MutableLiveData<List<Persona>> mPersones = new MutableLiveData<List<Persona>>();


    public PersonesViewModel(@NonNull Application application) {
        super(application);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                loadData(application);
            }
        });
    }

    private  final String DB_NAME = "mydb";

    private void loadData(Application a){

        DatabaseHelper dbh = new DatabaseHelper(a, DB_NAME,1);
        SQLiteDatabase db =  dbh.getReadableDatabase();

        ArrayList<Persona> persones = new ArrayList<Persona>();
        Cursor c = db.rawQuery("select * from persona", null);
        int ID_COL = c.getColumnIndexOrThrow("id");
        int NOM_COL = c.getColumnIndexOrThrow("nom");
        int PES_COL = c.getColumnIndexOrThrow("pes");
        while(c.moveToNext()){
            int id = c.getInt(ID_COL);
            String nom = c.getString(NOM_COL);
            double pes = c.getDouble(PES_COL);
            Persona p = new Persona(id, nom, pes);
            persones.add(p);
        }
        mPersones.postValue(persones);

    }

    public LiveData<List<Persona>> getPersones() {
        return mPersones;
    }
}
