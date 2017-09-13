package com.example.jl.ratatouille.db;

import android.provider.BaseColumns;

/**
 * Created by jav on 9/12/2017.
 */

public class DataBaseContract {
    public static final String DATABASE_NAME = "ratatouille_database";
    public static final int DATABASE_VERSION = 1;

    private DataBaseContract() {}

    public class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }
}
