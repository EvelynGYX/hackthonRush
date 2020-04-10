package com.example.virtuallibrary;

public class User {
    private String username = null;

    User (String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
