package com.example.a20220203_recyclerview.model;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuari
 */
public enum Rarity implements Serializable {
    COMMON,
    RARE,
    EPIC;    
    public static List<Rarity> getLlista(){
        return Arrays.asList(Rarity.values());
    }
}
