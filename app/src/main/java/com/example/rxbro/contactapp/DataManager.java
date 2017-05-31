package com.example.rxbro.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by rxbro on 5/30/2017.
 */

public class DataManager {
    private SQLiteDatabase db;
    public static final String TABLE_ROW_ID = "id";
    public static final String TABLE_ROW_FNAME = "fname";
    public static final String TABLE_ROW_LNAME = "lname";
    public static final String TABLE_ROW_PATH = "path";
    private static final String DB_NAME = "Contacts_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_CONTACT = "contacts";

    public DataManager(Context context) {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(String fname, String lname, String path) {
        String query = "INSERT INTO " + TABLE_CONTACT + " (" + TABLE_ROW_FNAME + ", " + TABLE_ROW_LNAME + ", " + TABLE_ROW_PATH + ") " + "VALUES ("
                + "'" + fname + "'" + lname + "'" + path + "); ";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    public void delete(String fname, String lname, String path) {
        String query = "DELETE FROM " + TABLE_CONTACT + " WHERE " + TABLE_ROW_FNAME + " = '" + fname + "';";
        Log.i("delete() = ", query);
        db.execSQL(query);

    }

    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" + " from " + TABLE_CONTACT, null);
        return c;
    }

    public Cursor searchName(String name) {
        String query = "SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_FNAME + ", " + TABLE_ROW_LNAME + ", " + TABLE_ROW_PATH + " from " + TABLE_CONTACT + " WHERE " + TABLE_CONTACT + " = '" + TABLE_ROW_FNAME + "';";
        Log.i("searchName() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String newTableQueryString = "Create table " +
                    TABLE_CONTACT + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_FNAME
                    + "text not null,"
                    + TABLE_ROW_LNAME
                    + "text not null,"
                    + TABLE_ROW_PATH
                    + "text not null);";
            db.execSQL(newTableQueryString);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

