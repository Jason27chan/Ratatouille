package com.example.jl.ratatouille.sqlite;

import android.provider.BaseColumns;

/**
 * Database schema for ratatouille app
 *
 * Created by jav on 9/12/2017.
 */

public class SQLiteContract {
    public static final String DATABASE_NAME = "ratatouille_database";
    public static final int DATABASE_VERSION = 2;

    /**
     * The constructor for the database contract
     */
    private SQLiteContract() {}

    /**
     * Table for user information
     */
    public class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_ACC_TYPE = "acc_type";
    }
}
