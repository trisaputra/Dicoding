package com.tri.submission5.fragment;

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
import androidx.recyclerview.widget.RecyclerView;

import com.tri.submission5.R;
import com.tri.submission5.adapter.FavoriteTvAdapter;
import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.TvFav;
import com.tri.submission5.viewmodel.FavMovieViewModel;
import com.tri.submission5.viewmodel.FavTvViewModel;

import java.util.ArrayList;

public class TvFragment extends Fragment {
    FavoriteTvAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FavTvViewModel vModel;

    public TvFragment(){

    };

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

        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        vModel = ViewModelProviders.of(this).get(FavTvViewModel.class);
        vModel.getData().observe(this, getData);
        vModel.setData(getActivity().getApplicationContext());
        showRecyclerAdapter();
    }

    private void showRecyclerAdapter(){

    }

    private Observer<ArrayList<TvFav>> getData = new Observer<ArrayList<TvFav>>() {
        @Override
        public void onChanged(ArrayList<TvFav> tvFavs) {
            if (tvFavs.size() > 0){
                adapter.setData(tvFavs);
            } else {
                Toast.makeText(getContext(), "Gagall mengambil data", Toast.LENGTH_SHORT).show();
            }
        }
    };


}

