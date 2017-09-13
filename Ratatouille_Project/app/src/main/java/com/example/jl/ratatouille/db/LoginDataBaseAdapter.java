package com.example.jl.ratatouille.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

/**
 * Created by jav on 9/12/2017.
 */

public class LoginDataBaseAdapter {

    private SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public static final String DATABASE_CREATE = "CREATE TABLE " + DataBaseContract.LoginEntry.TABLE_NAME + " ("
            + DataBaseContract.LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DataBaseContract.LoginEntry.COLUMN_USERNAME + " TEXT,"
            + DataBaseContract.LoginEntry.COLUMN_PASSWORD + " TEXT)";

    public LoginDataBaseAdapter(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(context, DataBaseContract.DATABASE_NAME, null, DataBaseContract.DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDataBaseInstance() {
        return db;
    }

    public void insertEntry(String username, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", username);
        newValues.put("PASSWORD", password);

        db.insert("LOGIN", null, newValues);
    }

    public int deleteEntry(String username) {
        String where = "USERNAME=?";
        int numberDeleted = db.delete("LOGIN", where, new String[]{username});
        return numberDeleted;
    }
}
