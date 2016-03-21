package com.example.android.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by nirmalendukumarmaisal on 12/03/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(Activity context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.grid_item, parent, false);
        }

        String poster_path = MovieConstants.MOVIE_DB_BASE_IMAGE_URI + MovieConstants.POSTER_WIDTH_500 + movie.poster_path;
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
        Picasso.with(getContext()).load(poster_path).into((ImageView) imageView);

        return convertView;
    }
}
