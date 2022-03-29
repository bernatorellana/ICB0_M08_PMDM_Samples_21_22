package org.iesmila.myapplication.dao;

import org.iesmila.myapplication.model.Rating;
import org.iesmila.myapplication.model.Song;
import org.iesmila.myapplication.model.User;
import org.iesmila.myapplication.model.UserRatedSongs;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SongDAO {
    @Query("SELECT * FROM song")
    List<Song> getAll();

    @Insert
    void insertAll(Song... songs);

    @Delete
    void delete(Song user);
}
