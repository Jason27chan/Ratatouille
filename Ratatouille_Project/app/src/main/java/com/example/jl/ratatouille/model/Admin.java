package com.example.jl.ratatouille.model;

/**
 * Created by Catherine on 10/2/2017.
 */

public class Admin extends User {

    /**
     * Constructor for Admin
     * @param username
     * @param password
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Checks if the user is an admin or not
     * @return true if admin, false if not
     */
    public boolean isAdmin() {
        return true;
    }
}
