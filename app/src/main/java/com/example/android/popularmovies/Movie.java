package com.example.android.popularmovies;

/**
 * Created by nirmalendukumarmaisal on 12/03/16.
 */
public class Movie {

    int id;
    double vote_average;
    String original_title;
    String poster_path;
    String overview;
    String release_date;


    public static String baseUrl = "http://image.tmdb.org/t/p/";

    /**
     *
     * @param original_title
     * @param id
     * @param poster_path
     */
    public Movie(int id, double vote_average, String original_title, String poster_path, String overview, String release_date) {
        this.id = id;
        this.vote_average = vote_average;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
    }
}
