package com.example.jl.ratatouille.model;

/**
 * Class that represents an Administer
 * which is a type of User
 *
 * Created by Catherine on 10/2/2017.
 */

public class Admin extends User {

    /**
     * Constructor for Admin
     * @param username the username for the Administrator
     * @param password the password for the Administrator
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
