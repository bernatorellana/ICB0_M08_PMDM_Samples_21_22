package com.example.activity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class DadesViewModel extends AndroidViewModel {

    MutableLiveData<String> valor = new MutableLiveData<String>();

    public MutableLiveData<String> getValor() {
        return valor;
    }


    public DadesViewModel(@NonNull Application application) {
        super(application);

    }
}
