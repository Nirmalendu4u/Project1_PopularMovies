package com.example.android.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;


/**
 * Created by nirmalendukumarmaisal on 12/03/16.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    /**
     *
     * @param context
     * @param movies
     */
    public MovieAdapter(Activity context, List<Movie> movies) {
        super(context, 0, movies);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_main, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_image);
        //iconView.setImageURI();
        return convertView;
    }
}
