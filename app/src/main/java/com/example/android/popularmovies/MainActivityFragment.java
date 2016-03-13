package com.example.android.popularmovies;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
       // ArrayList<Movie> movieList = new ArrayList<Movie>(Arrays.asList(movies));

        movieAdapter = new MovieAdapter(getActivity(), new ArrayList<>(Arrays.asList(movies)));

        GridView gridView = (GridView) rootView.findViewById(R.id.movie_grid);
        gridView.setAdapter(movieAdapter);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        new FetchMovieTask().execute();
    }

    private class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {

        private final String LOG_TAG = FetchMovieTask.class.getSimpleName();

        private Movie[] getFormattedMovieData(String movieJSONData) throws JSONException{
            JSONObject jsonObject = new JSONObject(movieJSONData);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            //need to change it later- the hardcoded value
            Movie[] movieArray = new Movie[20];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                Movie movie = new Movie(
                        (int) jsonObj.get("id"),
                        (double) jsonObj.get("vote_average"),
                        jsonObj.get("original_title").toString(),
                        jsonObj.get("poster_path").toString(),
                        jsonObj.get("overview").toString(),
                        jsonObj.get("release_date").toString());

                movieArray[i] = movie;
                Log.v(LOG_TAG, jsonObj.toString());
            }

            return movieArray;
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJSONData = null;
            try {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String sort_order = sharedPref.getString(getString(R.string.sort_order_default_label),
                        getString(R.string.sort_order_default_value));


                final String BASE_URL = "http://api.themoviedb.org/3/movie/" + sort_order + "?api_key=";
                final String API_KEY = BuildConfig.OPEN_MOVIEDB_API_KEY;


                URL url = new URL(BASE_URL + API_KEY);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                if (builder.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                movieJSONData = builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getFormattedMovieData(movieJSONData);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Movie[] movieArray) {

            if(movieArray != null) {
                List<Movie> movies = Arrays.asList(movieArray);
                movieAdapter.clear();
                for(Movie movie : movies) {
                    movieAdapter.add(movie);
                }
            }

        }


    }
}


