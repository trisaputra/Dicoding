package com.tri.submission5.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieTvModels implements Parcelable {
    private int id;
    private String title, overview, poster, rating, popularity, releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
        dest.writeString(this.rating);
        dest.writeString(this.popularity);
        dest.writeString(this.releaseDate);
    }

    public MovieTvModels() {
    }

    protected MovieTvModels(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = in.readString();
        this.rating = in.readString();
        this.popularity = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Parcelable.Creator<MovieTvModels> CREATOR = new Parcelable.Creator<MovieTvModels>() {
        @Override
        public MovieTvModels createFromParcel(Parcel source) {
            return new MovieTvModels(source);
        }

        @Override
        public MovieTvModels[] newArray(int size) {
            return new MovieTvModels[size];
        }
    };
}
