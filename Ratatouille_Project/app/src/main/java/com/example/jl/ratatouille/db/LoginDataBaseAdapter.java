package com.example.jl.ratatouille.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.jl.ratatouille.R;

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
            + DataBaseContract.LoginEntry.COLUMN_PASSWORD + " TEXT,"
            + DataBaseContract.LoginEntry.COLUMN_ACC_TYPE + " TEXT)";

    /**
     * Constructor for the login database adapter
     *
     * @param context information about application environment
     */
    public LoginDataBaseAdapter(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(context, DataBaseContract.DATABASE_NAME, null, DataBaseContract.DATABASE_VERSION);
    }

    /**
     * Opens the database
     *
     * @return the login database adapter is returned
     * @throws SQLException if the database cannot be opened
     */
    public LoginDataBaseAdapter open() throws SQLException {
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
        newValues.put(DataBaseContract.LoginEntry.COLUMN_USERNAME, username);
        newValues.put(DataBaseContract.LoginEntry.COLUMN_PASSWORD, password);
        newValues.put(DataBaseContract.LoginEntry.COLUMN_ACC_TYPE, acc_type);

        db.insert(DataBaseContract.LoginEntry.TABLE_NAME, null, newValues);
    }

    /**
     * Deletes a username entry
     *
     * @param username the username to be deleted
     * @return the number value of the table location where the value was deleted
     */
    public int deleteEntry(String username) {
        String where = DataBaseContract.LoginEntry.COLUMN_USERNAME + "=?";
        int numberDeleted = db.delete(DataBaseContract.LoginEntry.TABLE_NAME, where, new String[]{username});
        return numberDeleted;
    }

    /**
     * Gets the password
     * @param username the username that the password is related to
     * @return the password needed
     */
    public String getPassword(String username) {
        Cursor cursor = db.query(DataBaseContract.LoginEntry.TABLE_NAME, null, " " + DataBaseContract.LoginEntry.COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        String password = context.getString(R.string.not_found);
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            password = cursor.getString(cursor.getColumnIndex(DataBaseContract.LoginEntry.COLUMN_PASSWORD));
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
        Cursor cursor = db.query(DataBaseContract.LoginEntry.TABLE_NAME, null, " " + DataBaseContract.LoginEntry.COLUMN_USERNAME + "=?", new String[]{username}, null, null, null);
        String accType = context.getString(R.string.not_found);
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            accType = cursor.getString(cursor.getColumnIndex(DataBaseContract.LoginEntry.COLUMN_ACC_TYPE));
        }
        cursor.close();
        return accType;
    }
}
