package com.example.jl.ratatouille.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.jl.ratatouille.R;
import com.example.jl.ratatouille.sqlite.SQLiteContract;
import com.example.jl.ratatouille.sqlite.SQLiteHelper;

/**
 * Created by jav on 9/12/2017.
 */

public class SQLiteAdapter {

    private SQLiteDatabase db;
    private final Context context;
    private final SQLiteHelper dbHelper;

    public static final String DATABASE_CREATE = "CREATE TABLE " + SQLiteContract.LoginEntry.TABLE_NAME + " ("
            + SQLiteContract.LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SQLiteContract.LoginEntry.COLUMN_USERNAME + " TEXT,"
            + SQLiteContract.LoginEntry.COLUMN_PASSWORD + " TEXT,"
            + SQLiteContract.LoginEntry.COLUMN_ACC_TYPE + " TEXT)";

    /**
     * Constructor for the login database adapter
     *
     * @param context information about application environment
     */
    public SQLiteAdapter(Context context) {
        this.context = context;
        dbHelper = new SQLiteHelper(context, SQLiteContract.DATABASE_NAME, null, SQLiteContract.DATABASE_VERSION);
    }

    /**
     * Opens the database
     *
     * @return the login database adapter is returned
     * @throws SQLException if the database cannot be opened
     */
    public SQLiteAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * Closes the database
     */
    public void close() {
        db.close();
    }

    /**
     * Gets the database instance
     *
     * @return the data
     */
    public SQLiteDatabase getDataBaseInstance() {
        return db;
    }

    /**
     * Adds an entry to the database
     *
     * @param username the username to be added to the database
     * @param password the password to be added to the database
     * @param acc_type the type of the account
     */
    public void insertEntry(String username, String password, String acc_type) {
        ContentValues newValues = new ContentValues();
        newValues.put(SQLiteContract.LoginEntry.COLUMN_USERNAME, username);
        newValues.put(SQLiteContract.LoginEntry.COLUMN_PASSWORD, password);
        newValues.put(SQLiteContract.LoginEntry.COLUMN_ACC_TYPE, acc_type);

        db.insert(SQLiteContract.LoginEntry.TABLE_NAME, null, newValues);
    }

    /**
     * Deletes a username entry
     *
     * @param username the username to be deleted
     * @return the number value of the table location where the value was deleted
     */
    public int deleteEntry(String username) {
        String where = SQLiteContract.LoginEntry.COLUMN_USERNAME + "=?";
        int numberDeleted = db.delete(SQLiteContract.LoginEntry.TABLE_NAME, where, new String[]{username});
        return numberDeleted;
    }

    /**
     * Gets the password
     * @param username the username that the password is related to
     * @return the password needed
     */
    public String getPassword(String username) {
        Cursor cursor = db.query(SQLiteContract.LoginEntry.TABLE_NAME, null, " " + SQLiteContract.LoginEntry.COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        String password = context.getString(R.string.not_found);
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            password = cursor.getString(cursor.getColumnIndex(SQLiteContract.LoginEntry.COLUMN_PASSWORD));
        }
        cursor.close();
        return password;
    }

    /**
     * gets account type
     * @param username the username of the account we are looking for
     * @return the account type associated with the username
     */
    public String getAccType(String username) {
        Cursor cursor = db.query(SQLiteContract.LoginEntry.TABLE_NAME, null, " " + SQLiteContract.LoginEntry.COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        String accType = context.getString(R.string.not_found);
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            accType = cursor.getString(cursor.getColumnIndex(SQLiteContract.LoginEntry.COLUMN_ACC_TYPE));
        }
        cursor.close();
        return accType;
    }
}
