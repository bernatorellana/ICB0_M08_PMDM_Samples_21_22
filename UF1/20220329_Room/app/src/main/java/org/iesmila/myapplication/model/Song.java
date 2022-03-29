package org.iesmila.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Song {
    @PrimaryKey
    public long songId;
    public String name;
    public String band;


    public Song(long songId, String name, String band) {
        this.songId = songId;
        this.name = name;
        this.band = band;
    }

}
