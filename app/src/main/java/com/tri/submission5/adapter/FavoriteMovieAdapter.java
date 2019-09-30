package com.tri.submission5.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tri.submission5.R;
import com.tri.submission5.detailActivity.DetailMovieFavorite;
import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.MovieTvModels;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {
    private ArrayList<MovieFav> dataList = new ArrayList<>();
    private Fragment frgament;

    public FavoriteMovieAdapter(Fragment frgament) {
        this.frgament = frgament;
    }

    public ArrayList<MovieFav> getData() {
        return dataList;
    }

    public void setData(ArrayList<MovieFav> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        this.dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, dataList.size());
    }


    @NonNull
    @Override
    public FavoriteMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.content_fav_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieAdapter.ViewHolder holder, int position) {
        final int POSITION = position;
        holder.bind(getData().get(position));

        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(frgament.getContext(), DetailMovieFavorite.class);
            frgament.startActivityForResult(i, DetailMovieFavorite.REQUEST_DELETE);
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama;
        private TextView deskripsi;
        private ImageView gambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txt_nama);
            deskripsi = itemView.findViewById(R.id.txt_deskripsi);
            gambar = itemView.findViewById(R.id.img_foto);
        }

        void bind(MovieFav dataFav){
            nama.setText(dataFav.getTitle());
            deskripsi.setText(dataFav.getOverview());
            Glide.with(itemView.getContext())
                    .load(dataFav.getPoster())
                    .apply(new RequestOptions().override(100, 160))
                    .into(gambar);
        }
    }
}
