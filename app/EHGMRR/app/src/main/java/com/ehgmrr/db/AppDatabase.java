package com.ehgmrr.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ehgmrr.pojo.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}



