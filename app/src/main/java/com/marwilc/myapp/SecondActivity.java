package com.marwilc.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<Language> fiveLang;
    private RecyclerView languageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fiveLang = new ArrayList<Language>();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            fiveLang = bundle.getParcelableArrayList("fiveLang");

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
        LangAdapter adapter = new LangAdapter(fiveLang,this);
        languageList.setAdapter(adapter);
    }
}
