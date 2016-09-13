package com.rk.movieslist.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.rk.movieslist.Application.MoviesList;
import com.rk.movieslist.Models.MovieList;
import com.rk.movieslist.R;

/**
 * Created by ramakant on 14/9/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private MovieList movieList;
    private MovieList.Search[] imagePosterList;
    private ImageLoader imageLoder;
    private Context context;

    public MovieListAdapter(Context context, MovieList movieList){
        this.movieList = movieList;
        imagePosterList = movieList.getSearches();
        imageLoder = ImageLoader.getInstance();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item_layout,parent,false);
        holder = new MovieListViewHolder(view,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieListViewHolder movieListViewHolder = (MovieListViewHolder) holder;
        bindData(movieListViewHolder,position);
    }

    private void bindData(MovieListViewHolder movieListViewHolder, int position) {
        MovieList.Search object =  imagePosterList[position];
        imageLoder.displayImage(object.getPoster(),movieListViewHolder.getImage());
    }

    @Override
    public int getItemCount() {
        return imagePosterList.length;
    }
}
