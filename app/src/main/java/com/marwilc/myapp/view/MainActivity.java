package com.marwilc.myapp.view;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PageAdapter;
import com.marwilc.myapp.view.fragments.ProfileFragment;
import com.marwilc.myapp.view.fragments.RecyclerViewFragment;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerViewFragment rvfPets;
    private ProfileFragment pfProfilePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar     = (Toolbar) findViewById(R.id.myActionBar);
        tabLayout   = (TabLayout) findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);

        setupViewPager();

        if (toolbar != null)
            setSupportActionBar(toolbar);

     }


    // crear menu e inflar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    // menu option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.mContact:
                // to activity contact
                Intent intent1 = new Intent(this, ContactActivity.class);
                startActivity(intent1);
                break;

            case R.id.mAbout:
                // to activity about
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;

            case R.id.mFavorites:
                // to activity favorites
                toFavoritesActivity();
                break;

            case R.id.mAccount:
                // to activity account settings
                Intent intent3 = new Intent(this, AccountActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> aggFragments(){
        ArrayList<Fragment> fragmets = new ArrayList<>();

        rvfPets         = new RecyclerViewFragment();
        pfProfilePet    = new ProfileFragment();
        fragmets.add(rvfPets);
        fragmets.add(pfProfilePet);

        return fragmets;
    }

    private void setupViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),aggFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_pets_1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pets_2);
    }


    public void toFavoritesActivity() {

        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }
}


