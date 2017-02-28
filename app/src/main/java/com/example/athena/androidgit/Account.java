package com.example.athena.androidgit;

/**
 * Created by Athena on 2/28/2017.
 */

public class Account {

    public Account() {
    }

    public Account(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    private long id;
    private String email;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
