package com.ehgmrr.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ehgmrr.pojo.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE username LIKE :user OR email LIKE :user AND Password LIKE :pass LIMIT 1")
    User login(String user, String pass);

    @Query("SELECT * FROM user WHERE Email LIKE :email LIMIT 1")
    User findByEmail(String email);

    @Query("SELECT * FROM user WHERE username LIKE :user OR email LIKE :user LIMIT 1")
    User findByUsername(String user);

    @Query("SELECT * FROM user WHERE Phone LIKE :phone LIMIT 1")
    User findByPhone(String phone);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(User... users);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Update
    void update(User user);

}

