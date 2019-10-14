package com.tri.submission5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tri.submission5.db.AppSchemas;
import com.tri.submission5.db.DatabaseHelper;
import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.MovieTvModels;

import java.util.ArrayList;

public class MovieDao {
    private Context mContext;
    private static ArrayList<MovieTvModels> data = new ArrayList<>();

    public MovieDao(Context context){
        this.mContext = context;
    }

    public void addMovie(MovieTvModels models){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AppSchemas.MovieColumns.ID_MOVIE, models.getId());
        contentValues.put(AppSchemas.MovieColumns.TITLE, models.getTitle());
        contentValues.put(AppSchemas.MovieColumns.OVERVIEW, models.getOverview());
        contentValues.put(AppSchemas.MovieColumns.IMAGE, models.getPoster());
        contentValues.put(AppSchemas.MovieColumns.RELEASE, models.getReleaseDate());
        contentValues.put(AppSchemas.MovieColumns.VOTE, models.getVote_count());
        contentValues.put(AppSchemas.MovieColumns.RATING, models.getRating());

        db.insert(AppSchemas.MovieColumns.TABLE_MOVIE, null, contentValues);
        db.close();
    }

    public void deleteMovie(String id){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String where = "id=?";
        db.delete(AppSchemas.MovieColumns.TABLE_MOVIE, where, new String[]{id});
        db.close();
    }

    public ArrayList<MovieTvModels> getRowsMovie(){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        data.clear();
        Cursor cursor = db.query(AppSchemas.MovieColumns.TABLE_MOVIE, null, null, null, null,null, null, null);
        while (cursor.moveToNext()) {
            MovieTvModels models = new MovieTvModels();
            models.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.ID_MOVIE))));
            models.setTitle(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.TITLE)));
            models.setOverview(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.OVERVIEW)));
            models.setPoster(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.IMAGE)));
            models.setReleaseDate(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.RELEASE)));
            models.setVote_count(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.VOTE)));
            models.setRating(cursor.getString(cursor.getColumnIndex(AppSchemas.MovieColumns.RATING)));
            data.add(models);
        }

        cursor.close();
        db.close();
        return data;
    }

}
