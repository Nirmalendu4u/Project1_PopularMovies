package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MovieAdapter movieAdapter;

    Movie [] movies = {};

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>(Arrays.asList(movies)));

        // Get a reference to the ListView, and attach this adapter to it.
        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        gridView.setAdapter(movieAdapter);

        return rootView;
    }

/*
    private class FetchMovieTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Long doInBackground(String... params) {

        }

        @Override
        protected void onPostExecute(Long result) {

        }
    }*/
}


