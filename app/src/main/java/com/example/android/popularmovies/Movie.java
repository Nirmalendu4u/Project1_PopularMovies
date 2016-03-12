package com.example.android.popularmovies;

/**
 * Created by nirmalendukumarmaisal on 12/03/16.
 */
public class Movie {
    String name;
    int rating;
    String location;

    /**
     *
     * @param name
     * @param rating
     * @param location
     */
    public Movie(String name, int rating, String location) {
        this.name = name;
        this.rating = rating;
        this.location = location;
    }
}
