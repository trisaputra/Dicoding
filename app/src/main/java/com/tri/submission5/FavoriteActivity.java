package com.tri.submission5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.tri.submission5.adapter.TabAdapter;
import com.tri.submission5.fragment.MovieFragment;
import com.tri.submission5.fragment.TvFragment;

import java.util.Objects;

public class FavoriteActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabAdapter adapter;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        toolbar = findViewById(R.id.toolbar);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieFragment(), getString(R.string.movie));
        adapter.addFragment(new TvFragment(), "TV");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setTitle("Favorite Movie & Tv");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener((View v) -> onBackPressed());
    }
}
