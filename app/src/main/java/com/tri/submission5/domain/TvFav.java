package com.tri.submission5.domain;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.tri.submission5.db.AppSchemas.MovieColumns.IMAGE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.OVERVIEW;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RATING;
import static com.tri.submission5.db.AppSchemas.MovieColumns.RELEASE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.TITLE;
import static com.tri.submission5.db.AppSchemas.MovieColumns.VOTE;
import static com.tri.submission5.db.AppSchemas.TvColumns.ID_TV;
import static com.tri.submission5.db.AppSchemas.getColumnInt;
import static com.tri.submission5.db.AppSchemas.getColumnString;

public class TvFav implements Parcelable {

    private int id, tvId;
    private String title, overview, poster, rating, vote_count, releaseDate;

    public TvFav(int id, int tvId, String title, String overview, String poster, String rating, String vote_count, String releaseDate) {
        this.id = id;
        this.tvId = tvId;
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.rating = rating;
        this.vote_count = vote_count;
        this.releaseDate = releaseDate;
    }

    public TvFav(){

    }

    public TvFav(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.tvId = getColumnInt(cursor, ID_TV);
        this.title = getColumnString(cursor, TITLE);
        this.overview = getColumnString(cursor, OVERVIEW);
        this.poster = getColumnString(cursor, IMAGE);
        this.releaseDate = getColumnString(cursor, RELEASE);
        this.vote_count = getColumnString(cursor, VOTE);
        this.rating = getColumnString(cursor, RATING);
    }

    protected TvFav(Parcel in) {
        this.id = in.readInt();
        this.tvId = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.rating = in.readString();
        this.vote_count = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Creator<TvFav> CREATOR = new Creator<TvFav>() {
        @Override
        public TvFav createFromParcel(Parcel in) {
            return new TvFav(in);
        }

        @Override
        public TvFav[] newArray(int size) {
            return new TvFav[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTvId() {
        return tvId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
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



    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.tvId);
        parcel.writeString(this.title);
        parcel.writeString(this.overview);
        parcel.writeString(this.poster);
        parcel.writeString(this.rating);
        parcel.writeString(this.vote_count);
        parcel.writeString(this.releaseDate);
    }
}
