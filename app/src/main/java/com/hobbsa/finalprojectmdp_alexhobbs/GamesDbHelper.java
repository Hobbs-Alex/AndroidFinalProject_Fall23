package com.hobbsa.finalprojectmdp_alexhobbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GamesDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "gamesDb";
    public static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "games";
    private final Context context;

    public GamesDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(SQLiteDatabase db, Game game){
        ContentValues inputValues = new ContentValues();
        inputValues.put("name", game.getName());
        inputValues.put("publisher", game.getPublisher());
        inputValues.put("category", game.getCategory());
        inputValues.put("purchase_year", game.getPurchaseYear());
        db.insert(TABLE_NAME, null, inputValues);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion <1){
            db.execSQL("CREATE TABLE GAMES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME TEXT, PUBLISHER TEXT, CATEGORY TEXT, PURCHASE_YEAR INTEGER);");
        }
    }
}
