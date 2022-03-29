package org.iesmila.myapplication.dao;

import org.iesmila.myapplication.model.User;
import org.iesmila.myapplication.model.UserRatedSongs;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface UserDAO {
    @Transaction
    @Query("SELECT * FROM user")
    List<UserRatedSongs> getAll();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
