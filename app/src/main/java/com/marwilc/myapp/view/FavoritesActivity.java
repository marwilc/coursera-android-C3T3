package com.marwilc.myapp.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PageAdapter;
import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.view.fragments.RecyclerViewFragment;
import com.marwilc.myapp.view.fragments.RecyclerViewFragmentFavorites;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<Fragment> fFavorites;
    private RecyclerViewFragmentFavorites rvfFavorites;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        toolbar = (Toolbar) findViewById(R.id.myActionBar2);
        viewPager   = (ViewPager) findViewById(R.id.viewPager2);

        rvfFavorites = new RecyclerViewFragmentFavorites();
        fFavorites = new ArrayList<>();
        fFavorites.add(rvfFavorites);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),fFavorites));

        if(toolbar != null)
            setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
