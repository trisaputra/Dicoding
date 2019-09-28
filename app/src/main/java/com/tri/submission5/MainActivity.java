package com.tri.submission5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.android.material.navigation.NavigationView;
import com.tri.submission5.domain.MovieTvModels;
import com.tri.submission5.viewmodel.MovieViewModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Context mContext;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private MovieViewModel vModel;
    private RecyclerView recyclerView;
    private RvMovieAdapter adapter;
    private NavigationView navigationView;
    private EditText et_cari;
    private Button btn_cari;

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        toolbar= findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigation);
        et_cari = findViewById(R.id.et_cari);
        btn_cari = findViewById(R.id.btn_cari);

        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle("Movie");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        vModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        vModel.getData().observe(this, getDataObserve);
        vModel.setData(this,null);

        btn_cari.setOnClickListener(v -> {
            if (et_cari.getText().toString().trim().isEmpty()) {
                vModel.setData(this,null);
            } else {
                vModel.setData(this, et_cari.getText().toString());
            }
        });
    }

    private Observer<ArrayList<MovieTvModels>> getDataObserve = new Observer<ArrayList<MovieTvModels>>() {
        @Override
        public void onChanged(ArrayList<MovieTvModels> movieTvModels) {
            generateDataList(movieTvModels);
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_movie) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_tv) {
            Intent intent = new Intent(mContext, TvActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_alarm) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void generateDataList(ArrayList<MovieTvModels> dataList) {
        adapter = new RvMovieAdapter(dataList);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private class RvMovieAdapter extends RecyclerView.Adapter<RvMovieAdapter.ViewHolder> {

        private ArrayList<MovieTvModels> data;

        public RvMovieAdapter(ArrayList<MovieTvModels> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public RvMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.content_item_movie, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RvMovieAdapter.ViewHolder holder, int position) {
            holder.judul.setText(data.get(position).getTitle());
            Glide.with(mContext)
                    .load("https://image.tmdb.org/t/p/w185"+data.get(position).getPoster())
                    .into(holder.gambar);

            holder.detail.setOnClickListener(view ->{
                Intent i = new Intent(mContext, DetailMovieActivity.class);
                i.putExtra("data_movie", data.get(position));
                startActivity(i);
            });
        }

        @Override
        public int getItemCount() {
            return (data == null) ? 0 : data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView judul;
            Button detail;
            ImageView gambar;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                judul = itemView.findViewById(R.id.tx_judul);
                detail = itemView.findViewById(R.id.btn_detail);
                gambar = itemView.findViewById(R.id.imgMovie);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
