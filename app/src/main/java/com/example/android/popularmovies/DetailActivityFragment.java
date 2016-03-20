package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        Movie movie = (Movie) intent.getParcelableExtra(MovieConstants.DETAIL_VIEW_PARCEL_KEY);

        ImageView movieThumbnailImageView = (ImageView) view.findViewById(R.id.movie_thumbnail);
        String poster_path = MovieConstants.MOVIE_DB_BASE_IMAGE_URI + MovieConstants.POSTER_WIDTH_500 + movie.poster_path;
        Picasso.with(getActivity()).load(poster_path).into((ImageView) movieThumbnailImageView);

        getTextViewHelper(view, R.id.original_title).setText(movie.original_title);
        getTextViewHelper(view, R.id.release_date).setText(movie.release_date);
        getTextViewHelper(view, R.id.vote_average).setText(Integer.toString((int) movie.vote_average) + "/10");
        getTextViewHelper(view, R.id.overview).setText(movie.overview);

        Log.e(LOG_TAG, movie.original_title + ":" + movie.overview + ":" + movie.release_date);
        return view;
    }

    /**
     * A view finder helper
     * @param view
     * @param id
     * @return
     */
    private TextView getTextViewHelper(View view, int id) {
        return (TextView) view.findViewById(id);
    }

}
