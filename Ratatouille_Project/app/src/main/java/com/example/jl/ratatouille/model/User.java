package com.example.jl.ratatouille.model;

/**
 * Class that represents a User
 *
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

    /**
     * Checks to see if the User is an a
     * @return True if the user is an admin
     * False if the user is not an admin
     */
    public boolean isAdmin() {
        return false;
    }
}
