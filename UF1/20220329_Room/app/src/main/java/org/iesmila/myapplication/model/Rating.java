package org.iesmila.myapplication.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "songId"})
public class Rating {
    public long userId;
    public long songId;
    public int rating;

    public Rating(long userId, long songId, int rating) {
        this.userId = userId;
        this.songId = songId;
        this.rating = rating;
    }

}
