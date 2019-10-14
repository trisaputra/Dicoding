package com.tri.submission5.mapping;

import android.database.Cursor;

import com.tri.submission5.domain.MovieFav;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.tri.submission5.db.AppSchemas.MovieColumns.ID_MOVIE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.IMAGE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.OVERVIEW;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RATING;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RELEASE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.TITLE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.VOTE;

public class MovieMapping {

    public static ArrayList<MovieFav> mapCursorToArrayList(Cursor mCursor){
        ArrayList<MovieFav> movieFavs = new ArrayList<>();

        if (mCursor.moveToNext()){
            int id = mCursor.getInt(mCursor.getColumnIndexOrThrow(_ID));
            int movieId = mCursor.getInt(mCursor.getColumnIndexOrThrow(ID_MOVIE));
            String judul = mCursor.getString(mCursor.getColumnIndexOrThrow(TITLE));
            String overview = mCursor.getString(mCursor.getColumnIndexOrThrow(OVERVIEW));
            String poster = mCursor.getString(mCursor.getColumnIndexOrThrow(IMAGE));
            String release = mCursor.getString(mCursor.getColumnIndexOrThrow(RELEASE));
            String rating = mCursor.getString(mCursor.getColumnIndexOrThrow(RATING));
            String vote = mCursor.getString(mCursor.getColumnIndexOrThrow(VOTE));
            movieFavs.add(new MovieFav(id, movieId, judul, overview, poster, release, rating, vote));
        }
        return movieFavs;
    }
}
