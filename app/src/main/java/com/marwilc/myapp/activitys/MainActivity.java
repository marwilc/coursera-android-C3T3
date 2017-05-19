package com.marwilc.myapp.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.connections.Mail;
import com.marwilc.myapp.fragments.ProfileFragment;
import com.marwilc.myapp.fragments.RecyclerViewFragment;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

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




    public void toFavoritesActivity(){
        ArrayList<Pet> fivePets = buildFiveListPets();
        Log.i("size array five", Integer.toString(fivePets.size()));

        if(fivePets.size() != 0 ) {
            Intent intent = new Intent(this, FavoritesActivity.class);
            intent.putExtra("fivePets", fivePets);
            startActivity(intent);
        }
        else
            displayMessage("no favorites to show");
    }

    public void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.mFavorites), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public ArrayList<Pet> buildFiveListPets() {

        ArrayList<Pet> list = new ArrayList<>();
        this.bubblesort(rvfPets.getAlPets());

        int i=0;


        while (rvfPets.getAlPets().get(i).getLikes() > 0 && list.size() < 5){
            list.add(rvfPets.getAlPets().get(i));
            Log.i("pets.get(i)", Integer.toString(list.get(i).getLikes()));
            i++;

        }
        return (list);
    }

    public void bubblesort(ArrayList<Pet> pets) {
        boolean swapped = true;
        for(int i = pets.size() - 1; i > 0 && swapped; i--) {
            swapped = false;
            for (int j = 0; j < i; j++) {
                if (pets.get(j).getLikes() < pets.get(j + 1).getLikes()) {
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                    swapped = true;
                }
            }
        }
    }
}


