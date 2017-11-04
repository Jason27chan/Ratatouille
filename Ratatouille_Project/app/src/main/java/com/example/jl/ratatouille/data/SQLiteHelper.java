package com.example.jl.ratatouille.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jl.ratatouille.adapter.UserSQLiteAdapter;

/**
 * Creates and upgrades the database to update version
 *
 * Created by jav on 9/12/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    /**
     * Constructor for DataBase Helper
     *
     * @param context information about application environment
     * @param name the name for the database file
     * @param factory used to create cursor objects or null for object
     * @param version the number of the database
     */
    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserSQLiteAdapter.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLiteContract.LoginEntry.TABLE_NAME);
        onCreate(db);
    }
}
