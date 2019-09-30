package com.tri.submission5.detailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.tri.submission5.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tri.submission5.adapter.FavoriteMovieAdapter;
import com.tri.submission5.db.MovieHelper;
import com.tri.submission5.domain.MovieFav;
import com.tri.submission5.domain.MovieTvModels;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MovieTvModels data;
    private TextView idMovie, judul, release, populer, ratting, overview;
    private ImageView gambar;
    private FloatingActionButton fab;
    private MovieHelper moviex;
    private int idData;
    private MovieFav favoriteMovie;

    private boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        idMovie = findViewById(R.id.tv_id);
        gambar = findViewById(R.id.img);
        judul = findViewById(R.id.tv_judul);
        release = findViewById(R.id.tv_release);
        populer = findViewById(R.id.tv_populer);
        ratting = findViewById(R.id.tv_ratting);
        overview = findViewById(R.id.tv_deksripsi);
        fab = findViewById(R.id.fab_favorite);

        data = getIntent().getParcelableExtra("data_movie");

        idMovie.setText(String.valueOf(data.getId()));
        judul.setText(data.getTitle());
        release.setText(data.getReleaseDate());
        ratting.setText(String.valueOf(data.getVote_count()));
        overview.setText(data.getOverview());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185"+ data.getPoster())
                .into(gambar);

        moviex = MovieHelper.getIntance(this);
        favoriteMovie = new MovieFav();
        moviex.open();

        favoriteMovie.setMovieId(data.getId());
        favoriteMovie.setTitle(data.getTitle());
        favoriteMovie.setOverview(data.getOverview());
        favoriteMovie.setPoster(data.getPoster());
        favoriteMovie.setReleaseDate(data.getReleaseDate());
        favoriteMovie.setVote_count(data.getVote_count());
        favoriteMovie.setRating(data.getRating());

        fab.setOnClickListener(v -> {
            if (!isFav) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                long result = moviex.addFav(favoriteMovie);
                if (result > 0){
                    favoriteMovie.setId((int) result);;
                    Snackbar.make(fab, "sukses", Snackbar.LENGTH_SHORT).show();
                }
                isFav = true;
            } else {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_false));
                long deleteresult = moviex.deleteFav(favoriteMovie.getId());
                if (deleteresult > 0){
                    Snackbar.make(fab, "sukses hapus", Snackbar.LENGTH_SHORT).show();
                }
                isFav = false;
            }
        });

        toolbar.setTitle("Detail Movie & Tv");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener((View v) -> onBackPressed());
    }


}
