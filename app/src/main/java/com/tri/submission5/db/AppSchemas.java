package com.tri.submission5.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import java.security.PublicKey;

public class AppSchemas {
    public static final String AUTHORITY_MOVIE = "com.tri.submission5.movie";
    public static final String AUTHORITY_TV = "com.tri.submission5.tv";
    private static final String SCHEME = "content";

    private AppSchemas(){
    }

    public static final class MovieColumns implements BaseColumns {
        public static final String TABLE_MOVIE = "movie";
        public static final String ID_MOVIE = "ID_MOVIE";
        public static final String TITLE = "TITLE";
        public static final String OVERVIEW = "OVERVIEW";
        public static final String IMAGE = "IMAGE";
        public static final String RELEASE = "RELEASE";
        public static final String VOTE = "VOTE";
        public static final String RATING = "RATING";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY_MOVIE)
                .appendPath(TABLE_MOVIE)
                .build();
    }

    public static final class TvColumns implements BaseColumns {
        public static final String TABLE_TV = "tv";
        public static final String ID_TV= "ID_TV";
        public static final String TITLE = "TITLE";
        public static final String OVERVIEW = "OVERVIEW";
        public static final String IMAGE = "IMAGE";
        public static final String RELEASE = "RELEASE";
        public static final String VOTE = "VOTE";
        public static final String RATING = "RATING";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY_TV)
                .appendPath(TABLE_TV)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }


}
