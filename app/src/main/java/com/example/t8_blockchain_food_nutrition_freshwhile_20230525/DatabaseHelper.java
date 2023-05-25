package com.example.t8_blockchain_food_nutrition_freshwhile_20230525;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "produce.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createProduceTable(db);
        createUserTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS produce");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    private void createProduceTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE produce ("
                + "fresh_produce_id TEXT PRIMARY KEY,"
                + "fresh_produce_category TEXT,"
                + "date_of_handling TEXT,"
                + "location_of_handling TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }

    private void createUserTable(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE users ("
                + "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT,"
                + "email TEXT,"
                + "password TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }
}
