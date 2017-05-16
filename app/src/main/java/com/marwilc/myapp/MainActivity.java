package com.marwilc.myapp;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Language> languages;
    private RecyclerView languageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(myActionBar);

        languageList = (RecyclerView) findViewById(R.id.rvAvatars);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        languageList.setLayoutManager(llm);
        initLangList();
        initAdapter();

     }

    public void initLangList(){

        languages = new ArrayList<Language>();
        languages.add(new Language("JavaScript", 0, R.drawable.javascritp));
        languages.add(new Language("Python", 0, R.drawable.python));
        languages.add(new Language("Java", 0, R.drawable.java));
        languages.add(new Language("C++", 0, R.drawable.c__));
        languages.add(new Language("C", 0, R.drawable.c));
        languages.add(new Language("PHP", 0, R.drawable.php));
        languages.add(new Language("R", 0, R.drawable.r));
        languages.add(new Language("Pascal", 0, R.drawable.pascal));
        languages.add(new Language("Visual Basic", 0, R.drawable.vb));
    }

    public void initAdapter(){
        LangAdapter adapter = new LangAdapter(languages,this);
        languageList.setAdapter(adapter);

    }

    public void toSecondActivity(View v){
        ArrayList<Language> fiveListLang = buildFiveListLang();
        Log.i("size array five", Integer.toString(fiveListLang.size()));

        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("fiveLang", fiveListLang);
        startActivity(intent);
    }

    public ArrayList<Language> buildFiveListLang() {

        ArrayList<Language> list = new ArrayList<Language>();
        this.bubblesort(languages);

        int i=0;


        while (languages.get(i).getLikes() > 0 && list.size() < 5){
            list.add(languages.get(i));
            Log.i("languages.get(i)", Integer.toString(list.get(i).getLikes()));
            i++;

        }
        return (list);
    }

    public void bubblesort(ArrayList<Language> languages) {
        boolean swapped = true;
        for(int i = languages.size() - 1; i > 0 && swapped; i--) {
            swapped = false;
            for (int j = 0; j < i; j++) {
                if (languages.get(j).getLikes() < languages.get(j + 1).getLikes()) {
                    Language temp = languages.get(j);
                    languages.set(j, languages.get(j + 1));
                    languages.set(j + 1, temp);
                    swapped = true;
                }
            }
        }
    }

}
