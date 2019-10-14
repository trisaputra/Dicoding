package com.tri.submission5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tri.submission5.db.AppSchemas;
import com.tri.submission5.db.DatabaseHelper;
import com.tri.submission5.domain.MovieTvModels;

import java.util.ArrayList;

public class TvDao {
    private Context mContext;
    private static ArrayList<MovieTvModels> data = new ArrayList<>();

    public void TvDao(Context context){
        this.mContext = context;
    }

    public void addTv(MovieTvModels models){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(AppSchemas.TvColumns.ID_TV, models.getId());
        contentValues.put(AppSchemas.TvColumns.TITLE, models.getTitle());
        contentValues.put(AppSchemas.TvColumns.OVERVIEW, models.getOverview());
        contentValues.put(AppSchemas.TvColumns.IMAGE, models.getPoster());
        contentValues.put(AppSchemas.TvColumns.RELEASE, models.getReleaseDate());
        contentValues.put(AppSchemas.TvColumns.VOTE, models.getVote_count());
        contentValues.put(AppSchemas.TvColumns.RATING, models.getRating());

        db.insert(AppSchemas.TvColumns.TABLE_TV, null, contentValues);
        db.close();
    }

    public void deleteTv(String id){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String where = "id=?";
        db.delete(AppSchemas.TvColumns.TABLE_TV, where, new String[]{id});
        db.close();
    }

    public ArrayList<MovieTvModels> getRowsMovie(){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        data.clear();
        Cursor cursor = db.query(AppSchemas.TvColumns.TABLE_TV, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            MovieTvModels models = new MovieTvModels();
            models.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.ID_TV))));
            models.setTitle(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.TITLE)));
            models.setOverview(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.OVERVIEW)));
            models.setPoster(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.IMAGE)));
            models.setReleaseDate(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.RELEASE)));
            models.setVote_count(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.VOTE)));
            models.setRating(cursor.getString(cursor.getColumnIndex(AppSchemas.TvColumns.RATING)));
            data.add(models);
        }

        cursor.close();
        db.close();
        return data;
    }
}
