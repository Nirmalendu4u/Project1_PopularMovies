package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nirmalendukumarmaisal on 12/03/16.
 */
public class Movie implements Parcelable {

    int id;
    double vote_average;
    String original_title;
    String poster_path;
    String overview;
    String release_date;

    /**
     *
     * @param id movie id
     * @param vote_average movie rating
     * @param original_title movie title
     * @param poster_path movie poster path
     * @param overview movie description
     * @param release_date movie release date
     */
    public Movie(int id, double vote_average, String original_title, String poster_path, String overview, String release_date) {
        this.id = id;
        this.vote_average = vote_average;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }

    public Movie(Parcel in) {
        id = in.readInt();
        vote_average = in.readDouble();
        original_title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeDouble(vote_average);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }

    public  static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
