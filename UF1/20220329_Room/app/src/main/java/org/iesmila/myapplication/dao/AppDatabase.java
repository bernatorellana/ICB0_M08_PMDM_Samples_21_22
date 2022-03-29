package org.iesmila.myapplication.dao;


import org.iesmila.myapplication.model.Rating;
import org.iesmila.myapplication.model.Song;
import org.iesmila.myapplication.model.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Rating.class, Song.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract RatingDAO ratingDao();
    public abstract SongDAO songDao();
}
