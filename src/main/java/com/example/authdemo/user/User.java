package com.example.authdemo.user;

public class User {
    private final String username;
    private final String passwordHash; // stored hashed

    public User(String username, String passwordHash) {
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if(passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be blank");
        }
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
