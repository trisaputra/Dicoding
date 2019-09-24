package com.tri.submission5.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.MovieApiViewHolder> {

    @NonNull
    @Override
    public MovieApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieApiViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieApiViewHolder extends RecyclerView.ViewHolder {


        public MovieApiViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
