package com.example.jl.ratatouille.model;

/**
 * Created by Catherine on 10/2/2017.
 */

public class User {

    private String username;
    private String password;

    /**
     * Constructor for the user
     *
     * @param username the username of the user
     * @param password the password for the user
     */
    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    /**
     * Constructor for user
     */
    public User() {
        this("", "123456");
    }

    /**
     * Checks if the user is an admin or not
     * @return true if admin, false if not
     */

    public String getUsername() {
        return this.username;
    }

    public boolean isAdmin() {
        return false;
    }
}
