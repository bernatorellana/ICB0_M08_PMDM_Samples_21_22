package org.iesmila.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey
    public long userId;
    public String name;

    public User(long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

}
