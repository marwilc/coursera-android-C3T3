package com.marwilc.myapp.activitys;

import android.content.Intent;
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
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pet> pets;
    private RecyclerView recyclerViewPets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(myActionBar);

        recyclerViewPets = (RecyclerView) findViewById(R.id.rvAvatars);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewPets.setLayoutManager(llm);
        initLangList();
        initAdapter();

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

    public void initLangList(){

        pets = new ArrayList<>();
        pets.add(new Pet("Steam", 0, R.drawable.dog1));
        pets.add(new Pet("Pitus", 0, R.drawable.panda2));
        pets.add(new Pet("Fido", 0, R.drawable.pig3));
        pets.add(new Pet("Try", 0, R.drawable.pinguin4));
        pets.add(new Pet("Reapper", 0, R.drawable.cat5));
        pets.add(new Pet("Tin", 0, R.drawable.rabbit6));
    }

    public void initAdapter(){
        PetAdapter adapter = new PetAdapter(pets,this);
        recyclerViewPets.setAdapter(adapter);

    }

    public void toFavoritesActivity(){
        ArrayList<Pet> fivePets = buildFiveListPets();
        Log.i("size array five", Integer.toString(fivePets.size()));

        Intent intent = new Intent(this,FavoritesActivity.class);
        intent.putExtra("fivePets", fivePets);
        startActivity(intent);
    }

    public ArrayList<Pet> buildFiveListPets() {

        ArrayList<Pet> list = new ArrayList<>();
        this.bubblesort(pets);

        int i=0;


        while (pets.get(i).getLikes() > 0 && list.size() < 5){
            list.add(pets.get(i));
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
