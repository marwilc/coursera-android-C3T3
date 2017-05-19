package com.marwilc.myapp.activitys;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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

public class FavoritesActivity extends AppCompatActivity {

    private ArrayList<Pet> fivePets;
    private RecyclerView languageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fivePets = new ArrayList<Pet>();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            fivePets = bundle.getParcelableArrayList("fivePets");

        /*Bundle bundle = this.getIntent().getExtras();
        if(bundle!= null)
            fiveLang = bundle.getParcelable("fiveLang");
        */
        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar2);
        setSupportActionBar(myActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        languageList = (RecyclerView) findViewById(R.id.rvFavorites);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        Log.i("l", languageList.toString());
        languageList.setLayoutManager(llm);

        initAdapter();



    }

    public void initAdapter(){
        PetAdapter adapter = new PetAdapter(fivePets,this);
        languageList.setAdapter(adapter);
    }
}
