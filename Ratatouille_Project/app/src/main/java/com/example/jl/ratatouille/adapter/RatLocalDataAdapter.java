package com.example.jl.ratatouille.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.jl.ratatouille.data.SQLiteContract;
import com.example.jl.ratatouille.data.SQLiteHelper;

/**
 * Created by jav on 11/3/2017.
 */

public class RatLocalDataAdapter {
    private SQLiteDatabase db;
    private final Context context;
    private SQLiteHelper dbHelper;

    public static final String DATABASE_CREATE = "CREATE TABLE " + SQLiteContract.Rats.TABLE_NAME + " ("
            + SQLiteContract.Rats._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SQLiteContract.LoginEntry.COLUMN_USERNAME + " TEXT,";
}
