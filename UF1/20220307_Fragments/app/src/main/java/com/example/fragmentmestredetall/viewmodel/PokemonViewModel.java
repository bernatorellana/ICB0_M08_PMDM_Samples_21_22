package com.example.fragmentmestredetall.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.fragmentmestredetall.network.NetworkUtils;
import com.example.fragmentmestredetall.network.Pokemon;
import com.example.fragmentmestredetall.network.PokemonApiManual;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonViewModel extends AndroidViewModel {

    MutableLiveData<List<Pokemon>> pokemons = new MutableLiveData<>();

    public PokemonViewModel(@NonNull Application application) {
        super(application);

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Log.d("Bernat", "init");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("Bernat", "run");
                    String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=200";
                    Log.d("Bernat", "run1");
                    String json = NetworkUtils.getJSon(url);
                    Log.d("Bernat", "downloaded:"+json);

                    PokemonApiManual api= new PokemonApiManual(json);

                    List<Pokemon> p = api.parse();
                    pokemons.postValue(p);
                    Log.d("Bernat", "downloaded:"+p.size());

                } catch (Exception e) {
                    Log.e("ERR","Error parsing json", e);
                }
                finally {
                    Log.d("Bernat", "X");
                }
            }
        });

    }

    public MutableLiveData<List<Pokemon>> getPokemons() {
        return pokemons;
    }



}
