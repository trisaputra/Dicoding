package com.tri.submission5.detailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tri.submission5.R;

public class DetailMovieFavorite extends AppCompatActivity {

    public static final int REQUEST_DELETE = 500;
    public static final int RESULT_DELETE = 404;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_favorite);

    }
}
