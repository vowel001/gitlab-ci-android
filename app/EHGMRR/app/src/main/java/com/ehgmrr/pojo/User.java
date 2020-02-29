package com.ehgmrr.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "username")
    private String Username;

    @ColumnInfo(name = "Phone")
    private String Phone;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Password")
    private String Password;

    @ColumnInfo(name = "type")
    private String type;

    public User() {
    }

    public User(int id, String username, String phone, String email, String password, String type) {
        this.id = id;
        Username = username;
        Phone = phone;
        Email = email;
        Password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
