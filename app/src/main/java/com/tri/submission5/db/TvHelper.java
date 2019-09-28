package com.tri.submission5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tri.submission5.domain.MovieTvModels;
import com.tri.submission5.domain.TvFav;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.tri.submission5.db.AppSchemas.TvColumns.ID_TV;
import static com.tri.submission5.db.AppSchemas.TvColumns.IMAGE;
import static com.tri.submission5.db.AppSchemas.TvColumns.OVERVIEW;
import static com.tri.submission5.db.AppSchemas.TvColumns.RATING;
import static com.tri.submission5.db.AppSchemas.TvColumns.RELEASE;
import static com.tri.submission5.db.AppSchemas.TvColumns.TABLE_TV;
import static com.tri.submission5.db.AppSchemas.TvColumns.TITLE;
import static com.tri.submission5.db.AppSchemas.TvColumns.VOTE;

public class TvHelper {
    private static final String DATABASE_TABLE_TV = TABLE_TV;
    private static DatabaseHelper dbHelper;
    private static TvHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvHelper(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public static TvHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new TvHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
        if (database.isOpen())
            database.close();
    }

    public ArrayList<TvFav> getAllTv(){
        ArrayList<TvFav> arrayList = new ArrayList<>();
        Cursor csr = database.query(DATABASE_TABLE_TV, null, null, null, null, null, _ID + " DESC", null);
        csr.moveToFirst();
        TvFav tvFav;
        if (csr.getCount() > 0){
            do {
                tvFav = new TvFav();
                tvFav.setId(csr.getInt(csr.getColumnIndexOrThrow(_ID)));
                tvFav.setTvId(csr.getInt(csr.getColumnIndexOrThrow(ID_TV)));
                tvFav.setTitle(csr.getString(csr.getColumnIndexOrThrow(TITLE)));
                tvFav.setOverview(csr.getString(csr.getColumnIndexOrThrow(OVERVIEW)));
                tvFav.setPoster(csr.getString(csr.getColumnIndexOrThrow(IMAGE)));
                tvFav.setReleaseDate(csr.getString(csr.getColumnIndexOrThrow(RELEASE)));
                tvFav.setVote_count(csr.getString(csr.getColumnIndexOrThrow(VOTE)));

                arrayList.add(tvFav);
                csr.moveToNext();
            } while (!csr.isAfterLast());
        }
        csr.close();
        return arrayList;
    }

    public boolean checkData(int id) {
        String[] col = {
                _ID
        };
        String selection = "ID_TV=?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        Cursor csr = database.query(DATABASE_TABLE_TV, col, selection, selectionArgs, null, null, null, null);
        csr.moveToFirst();
        if (csr.getCount() <= 0) {
            csr.close();
            return false;
        } else {
            csr.close();
            return false;
        }
    }

    public long addFav(TvFav tvFav){
        ContentValues args = new ContentValues();
        args.put(ID_TV, tvFav.getTvId());
        args.put(TITLE, tvFav.getTitle());
        args.put(OVERVIEW, tvFav.getOverview());
        args.put(IMAGE, tvFav.getPoster());
        args.put(RELEASE, tvFav.getReleaseDate());
        args.put(VOTE, tvFav.getVote_count());
        args.put(RATING, tvFav.getRating());
        return database.insert(DATABASE_TABLE_TV, null, args);
    }

    public int getId(int id) {
        String[] col = {
                _ID
        };
        String selection = "ID_TV=?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        Cursor csx = database.query(DATABASE_TABLE_TV, col, selection, selectionArgs, null, null, null, null);
        csx.moveToFirst();
        if(csx.getCount() <= 0){
            csx.close();
            return -1;
        } else {
            int itemId = csx.getInt(
                    csx.getColumnIndexOrThrow(_ID)
            );
            csx.close();
            return itemId;
        }
    }

    public int deleteFavorit(int id){
        return database.delete(TABLE_TV, _ID+" = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE_TV, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE_TV
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE_TV, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE_TV, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE_TV, _ID + " = ?", new String[]{id});
    }
}
