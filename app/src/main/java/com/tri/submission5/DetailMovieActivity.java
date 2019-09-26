package com.tri.submission5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    Toolbar toolbar;
    String data;
    TextView judul, release, populer, ratting, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        judul = findViewById(R.id.tx_judul);
        release = findViewById(R.id.tx_release);
        populer = findViewById(R.id.tx_populer);
        ratting = findViewById(R.id.tx_ratting);
        overview = findViewById(R.id.tx_deksripsi);

        data = getIntent().getStringExtra("data_movie");



        toolbar.setTitle("Detail Movie & Tv");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener((View v) -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
