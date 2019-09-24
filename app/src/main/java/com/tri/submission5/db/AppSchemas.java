package com.tri.submission5.db;

import android.provider.BaseColumns;

import java.security.PublicKey;

public class AppSchemas {
    public static final String AUTHORITY_MOVIE = "";
    public static final String AUTHORITY_TV = "";
    private static final String SCHEME = "content";

    private AppSchemas(){
    }

    public static final class MovieColumns implements BaseColumns {
        public static final String TABEL_MOVIE = "movie";
        public static final String ID_MOVIE = "ID_MOVIE";
        public static final String TITLE = "TITLE";
        public static final String OVERVIEW = "OVERVIEW";
        public static final String GAMBAR = "GAMBAR";
        public static final String RELEASE = "RELEASE";
        public static final String POPULARITY = "POPULARITY";
        public static final String RATING = "RATING";
    }
}
