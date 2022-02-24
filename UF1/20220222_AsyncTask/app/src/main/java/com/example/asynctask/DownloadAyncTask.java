package com.example.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.asynctask.network.NetworkUtils;
import com.example.asynctask.network.Pokemon;
import com.example.asynctask.network.PokemonApiManual;

import java.util.List;

public class DownloadAyncTask extends AsyncTask<String,Integer,List<Pokemon>  > {

    MainActivity activity;

    public DownloadAyncTask(MainActivity ma){
        this.activity = ma;
    }

    @Override
    protected List<Pokemon> doInBackground(String... url) {
        // això s'executa en Background
        try {
            String json = NetworkUtils.getJSon(url[0]);

            PokemonApiManual api = new PokemonApiManual(json);
            return api.parse();

        } catch (Exception e) {
            Log.e("Exemple", "Error a la descàrrega del JSON", e);
            return null;
        }
    }

    /*@Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }*/

    @Override
    protected void onPostExecute(List<Pokemon>  pokemons) {
        super.onPostExecute(pokemons);
        // Això s'executa al fil d'interfície gràfica
        String resultat;
        if(pokemons==null) {
            resultat = "ERROR A LA DESCÀRREGA";
        } else {
            resultat = pokemons.toString();
        }
        activity.mostraResultat(resultat);
    }
}
