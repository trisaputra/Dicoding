package com.tri.submission5.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tri.submission5.R;
import com.tri.submission5.adapter.FavoriteMovieAdapter;
import com.tri.submission5.detailActivity.DetailMovieActivity;
import com.tri.submission5.detailActivity.DetailMovieFavorite;
import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.viewmodel.FavMovieViewModel;

import java.util.ArrayList;


public class MovieFragment extends Fragment {
    FavoriteMovieAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FavMovieViewModel vModel;

    public MovieFragment(){};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fav_movie, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        vModel = ViewModelProviders.of(this).get(FavMovieViewModel.class);
        vModel.getData().observe(this, getData);
        showRecyclerAdapter();

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showRecyclerAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private Observer<ArrayList<MovieFav>> getData = new Observer<ArrayList<MovieFav>>() {
        @Override
        public void onChanged(ArrayList<MovieFav> movieFavs) {
            if (movieFavs.size() > 0){
                adapter.setData(movieFavs);
            } else {
                Toast.makeText(getContext(), "Gagall mengambil data", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == DetailMovieFavorite.REQUEST_DELETE){
                int position = data.getIntExtra("index", 0);
                adapter.removeData(position);
                Toast.makeText(getContext(), "Data telah dihapus", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
