package com.tri.submission5.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.TvFav;

import java.util.ArrayList;

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.ViewHolder> {
    private ArrayList<TvFav> dataList = new ArrayList<>();
    private Fragment frgament;

    public FavoriteTvAdapter(Fragment frgament) {
        this.frgament = frgament;
    }

    public ArrayList<TvFav> getData(){
        return dataList;
    }

    public void setData(ArrayList<TvFav> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteTvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
