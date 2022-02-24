package com.example.asynctask.network;

public class Pokemon {

    String name;
    String url;
    PokemonInfo info;

    public Pokemon(String name, String url, PokemonInfo info) {
        this.name = name;
        this.url = url;
        this.info = info;
    }

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PokemonInfo getInfo(){
        return info;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public void setInfo(PokemonInfo info) {
        this.info = info;
    }
}
