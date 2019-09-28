package com.tri.submission5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.TvFav;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.tri.submission5.db.AppSchemas.MovieColumns.IMAGE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.ID_MOVIE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.OVERVIEW;
import static com.tri.submission5.db.AppSchemas.MovieColumns.VOTE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RATING;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RELEASE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.TABLE_MOVIE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.TITLE;

public class MovieHelper {
    private static final String DATABASE_TABLE_MOVIE = TABLE_MOVIE;
    private static DatabaseHelper dbHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getIntance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<MovieFav> getAllMovie(){
        ArrayList<MovieFav> arrayList = new ArrayList<>();
        Cursor csr = database.query(DATABASE_TABLE_MOVIE, null, null, null, null, null, _ID + " DESC", null);
        csr.moveToFirst();
        MovieFav movieFav;
        if (csr.getCount() > 0){
            do {
                movieFav = new MovieFav();
                movieFav.setId(csr.getInt(csr.getColumnIndexOrThrow(_ID)));
                movieFav.setMovieId(csr.getInt(csr.getColumnIndexOrThrow(ID_MOVIE)));
                movieFav.setTitle(csr.getString(csr.getColumnIndexOrThrow(TITLE)));
                movieFav.setOverview(csr.getString(csr.getColumnIndexOrThrow(OVERVIEW)));
                movieFav.setPoster(csr.getString(csr.getColumnIndexOrThrow(IMAGE)));
                movieFav.setReleaseDate(csr.getString(csr.getColumnIndexOrThrow(RELEASE)));
                movieFav.setVote_count(csr.getString(csr.getColumnIndexOrThrow(VOTE)));
                movieFav.setRating(csr.getString(csr.getColumnIndexOrThrow(RATING)));

                arrayList.add(movieFav);
                csr.moveToNext();
            } while (!csr.isAfterLast());
        }
        csr.close();
        return arrayList;
    }

    public boolean checkData(int id){
        String[] col = {
                _ID
        };
        String selection = "ID_MOVIE=?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        Cursor csr = database.query(DATABASE_TABLE_MOVIE, col, selection, selectionArgs, null, null, null, null);
        csr.moveToFirst();
        if (csr.getCount() <= 0){
            csr.close();
            return false;
        } else{
            csr.close();
            return true;
        }
    }

    public long addFav(MovieFav movieFav) {
        ContentValues args = new ContentValues();
        args.put(ID_MOVIE, movieFav.getMovieId());
        args.put(TITLE, movieFav.getTitle());
        args.put(OVERVIEW, movieFav.getOverview());
        args.put(IMAGE, movieFav.getPoster());
        args.put(RELEASE, movieFav.getReleaseDate());
        args.put(VOTE, movieFav.getVote_count());
        args.put(RATING, movieFav.getRating());
        return database.insert(DATABASE_TABLE_MOVIE, null, args);
    }

    public int getId(int id) {
        String[] col = {
                _ID
        };
        String selection = "ID_MOVIE=?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        Cursor csr = database.query(DATABASE_TABLE_MOVIE,
                col,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null);
        csr.moveToFirst();
        if (csr.getCount() <= 0){
            csr.close();
            return -1;
        } else {
            int idx = csr.getInt(
                    csr.getColumnIndexOrThrow(_ID)
            );
            csr.close();
            return idx;
        }
    }

    public int deleteFav(int id){
        return database.delete(TABLE_MOVIE, _ID+" = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE_MOVIE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE_MOVIE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE_MOVIE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE_MOVIE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE_MOVIE, _ID + " = ?", new String[]{id});
    }
}
