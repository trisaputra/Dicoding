package com.tri.submission5.detailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tri.submission5.R;
import com.tri.submission5.domain.MovieTvModels;

import java.util.Objects;

public class DetailTvActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MovieTvModels data;
    private TextView idTv, judul, release, populer, ratting, overview;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_actvitiy);

        toolbar = findViewById(R.id.toolbar);
        idTv = findViewById(R.id.tv_id);
        gambar = findViewById(R.id.img);
        judul = findViewById(R.id.tv_judul);
        release = findViewById(R.id.tv_release);
        populer = findViewById(R.id.tv_populer);
        ratting = findViewById(R.id.tv_ratting);
        overview = findViewById(R.id.tv_deksripsi);

        data = getIntent().getParcelableExtra("data_tv");

        idTv.setText(String.valueOf(Objects.requireNonNull(data).getId()));
        judul.setText(data.getTitle());
        release.setText(data.getReleaseDate());
        populer.setText(String.valueOf(data.getVote_count()));
        ratting.setText(String.valueOf(data.getRating()));
        overview.setText(data.getOverview());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185"+ data.getPoster())
                .into(gambar);

        toolbar.setTitle("Detail Movie & Tv");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener((View v) -> onBackPressed());
    }
}
