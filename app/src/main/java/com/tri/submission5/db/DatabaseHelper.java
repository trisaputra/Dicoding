package com.tri.submission5.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "submission.db";

    private static final int DATABASE_VERSION = 2;

    /**
     * MOVIE TABLE
     */

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    +" (%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    " %s INTEGER DEFAULT 0,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL)",
            AppSchemas.MovieColumns.TABLE_MOVIE,
            AppSchemas.MovieColumns._ID,
            AppSchemas.MovieColumns.ID_MOVIE,
            AppSchemas.MovieColumns.TITLE,
            AppSchemas.MovieColumns.OVERVIEW,
            AppSchemas.MovieColumns.IMAGE,
            AppSchemas.MovieColumns.RELEASE,
            AppSchemas.MovieColumns.VOTE,
            AppSchemas.MovieColumns.RATING);

    /**
     * TV TABLE
     */

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    +" (%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    " %s INTEGER DEFAULT 0,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL,"+
                    " %s TEXT NOT NULL)",
            AppSchemas.TvColumns.TABLE_TV,
            AppSchemas.TvColumns._ID,
            AppSchemas.TvColumns.ID_TV,
            AppSchemas.TvColumns.TITLE,
            AppSchemas.TvColumns.OVERVIEW,
            AppSchemas.TvColumns.IMAGE,
            AppSchemas.TvColumns.RELEASE,
            AppSchemas.TvColumns.VOTE,
            AppSchemas.TvColumns.RATING);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+AppSchemas.MovieColumns.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS "+AppSchemas.TvColumns.TABLE_TV);
        onCreate(db);
    }
}
