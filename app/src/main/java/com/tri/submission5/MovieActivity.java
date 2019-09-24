package com.tri.submission5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.tri.submission5.domain.MovieTvModels;
import com.tri.submission5.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        // view model
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getData().observe(this, getDataObserve);

        // bind xml
        // TO-DO
        //recyclerView = findViewById();
    }

    private Observer<ArrayList<MovieTvModels>> getDataObserve = new Observer<ArrayList<MovieTvModels>>() {
        @Override
        public void onChanged(ArrayList<MovieTvModels> movieTvModels) {
            generateDataList(movieTvModels);
        }
    };

    private void generateDataList(ArrayList<MovieTvModels> dataList) {
        MovieApiAdapter adapter = new MovieApiAdapter(dataList, this);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private class MovieApiAdapter extends RecyclerView.Adapter<MovieApiAdapter.MovieApiViewHolder> {

        private ArrayList<MovieTvModels> dataList;
        private Context mContext;

        public MovieApiAdapter(ArrayList<MovieTvModels> dataList, Context mContext) {
            this.dataList = dataList;
            this.mContext = mContext;
        }

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
}
