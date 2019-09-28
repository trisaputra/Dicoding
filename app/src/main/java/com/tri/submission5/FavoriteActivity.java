package com.tri.submission5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.tri.submission5.adapter.TabAdapter;

public class FavoriteActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

//        adapter = new TabAdapter(getSupportFragmentManager());
//        adapter.addFragment(new FavMovieFragment(), );
//        adapter.addFragment(new FavTvFragment(), getString(R.string.tv));
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
    }
}
