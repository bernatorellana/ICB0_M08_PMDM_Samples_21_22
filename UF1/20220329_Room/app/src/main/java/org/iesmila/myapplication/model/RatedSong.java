package org.iesmila.myapplication.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class RatedSong {
    @Embedded public Rating rating;
    @Relation(
            entity = Song.class,
            parentColumn = "songId",
            entityColumn = "songId"
    )
    public Song song;
}
