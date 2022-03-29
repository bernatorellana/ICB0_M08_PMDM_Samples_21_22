package org.iesmila.myapplication.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserRatedSongs {

    @Embedded
    public User user;
    @Relation(
            entity = Rating.class,
            parentColumn = "userId",
            entityColumn = "userId"
    )
    public List<RatedSong> ratedSongs;
}
