package com.tri.submission5.domain;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.tri.submission5.db.AppSchemas.MovieColumns.IMAGE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.ID_MOVIE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.OVERVIEW;
import static com.tri.submission5.db.AppSchemas.MovieColumns.VOTE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RATING;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RELEASE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.TITLE;
import static com.tri.submission5.db.AppSchemas.getColumnInt;
import static com.tri.submission5.db.AppSchemas.getColumnString;

public class MovieFav implements Parcelable {
    private int id, movieId;
    private String title, overview, poster, rating, vote_count, releaseDate;

    protected MovieFav(Parcel in) {
        this.id = in.readInt();
        this.movieId = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.rating = in.readString();
        this.vote_count = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Creator<MovieFav> CREATOR = new Creator<MovieFav>() {
        @Override
        public MovieFav createFromParcel(Parcel in) {
            return new MovieFav(in);
        }

        @Override
        public MovieFav[] newArray(int size) {
            return new MovieFav[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public MovieFav(int id, int movieId, String title, String overview, String poster, String release, String vote_count, String rating) {
        this.id = id;
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.releaseDate = release;
        this.vote_count = vote_count;
        this.rating = rating;
    }

    public MovieFav(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.movieId = getColumnInt(cursor, ID_MOVIE);
        this.title = getColumnString(cursor, TITLE);
        this.overview = getColumnString(cursor, OVERVIEW);
        this.poster = getColumnString(cursor, IMAGE);
        this.releaseDate = getColumnString(cursor, RELEASE);
        this.vote_count = getColumnString(cursor, VOTE);
        this.rating = getColumnString(cursor, RATING);
    }

    public MovieFav(){

    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.movieId);
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster);
        parcel.writeString(this.rating);
        parcel.writeString(this.vote_count);
        parcel.writeString(this.releaseDate);
    }


}
