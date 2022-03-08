package com.example.sqlite.modelview;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.sqlite.bd.AppDatabase;
import com.example.sqlite.bd.PersonaDAO;
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

        //@TODO  aquí hem de fer una consulta a la BD

        AppDatabase db =
                Room.databaseBuilder(a, AppDatabase.class,"mydatabase")
                        .createFromAsset("mydatabase.db")
                        .build();
        //---------------------------------------------------------
        PersonaDAO personaDAO= db.getPersonaDAO();
        mPersones.postValue(personaDAO.getPersones());

    }

    public LiveData<List<Persona>> getPersones() {
        return mPersones;
    }
}
