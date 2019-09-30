package com.tri.submission5.mapping;

import android.database.Cursor;

import com.tri.submission5.domain.TvFav;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.tri.submission5.db.AppSchemas.TvColumns.ID_TV;
import static com.tri.submission5.db.AppSchemas.TvColumns.IMAGE;
import static com.tri.submission5.db.AppSchemas.TvColumns.OVERVIEW;
import static com.tri.submission5.db.AppSchemas.TvColumns.RATING;
import static com.tri.submission5.db.AppSchemas.TvColumns.RELEASE;
import static com.tri.submission5.db.AppSchemas.TvColumns.TITLE;
import static com.tri.submission5.db.AppSchemas.TvColumns.VOTE;

public class TvMapping {

    public static ArrayList<TvFav> mapCursorToArrayList(Cursor tCursor) {
        ArrayList<TvFav> tvFavs = new ArrayList<>();

        if (tCursor.moveToNext()) {
            int id = tCursor.getInt(tCursor.getColumnIndexOrThrow(_ID));
            int tvId = tCursor.getInt(tCursor.getColumnIndexOrThrow(ID_TV));
            String judul = tCursor.getString(tCursor.getColumnIndexOrThrow(TITLE));
            String overview = tCursor.getString(tCursor.getColumnIndexOrThrow(OVERVIEW));
            String poster = tCursor.getString(tCursor.getColumnIndexOrThrow(IMAGE));
            String release = tCursor.getString(tCursor.getColumnIndexOrThrow(RELEASE));
            String rating = tCursor.getString(tCursor.getColumnIndexOrThrow(RATING));
            String vote = tCursor.getString(tCursor.getColumnIndexOrThrow(VOTE));
        }
        return tvFavs;
    }
}
