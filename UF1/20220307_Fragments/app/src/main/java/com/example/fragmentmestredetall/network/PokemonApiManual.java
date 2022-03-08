package com.example.fragmentmestredetall.network;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonApiManual {
    String json;

    public PokemonApiManual(String json){
        this.json = json;
    }




    public ArrayList<Pokemon> parse() throws JSONException {

        ArrayList<Pokemon> pokemonsList = new ArrayList<>();
         JSONObject arrel = new JSONObject(json);
         int count = arrel.getInt("count");
         String next = arrel.getString("next");
         JSONArray pokemons = arrel.getJSONArray("results");

         for(int i=0;i<pokemons.length();i++) {

             JSONObject pokemon =  pokemons.getJSONObject(i);
             String name = pokemon.getString("name");
             String url = pokemon.getString("url");

             // descarregar la url
             /*String jsonPokemonEspecific = NetworkUtils.getJSon(url);
             // parsejar el pokemon resultat
             PokemonInfo info = (new Gson()).fromJson(jsonPokemonEspecific, PokemonInfo.class);*/
             Pokemon p = new Pokemon(name, url);
             pokemonsList.add(p);
         }
        return pokemonsList;
    }

}
