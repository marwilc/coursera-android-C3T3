package com.marwilc.myapp.view;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import com.marwilc.myapp.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // implementacion de transiciones slide
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.setDuration(1200);
            this.getWindow().setEnterTransition(slide);
            this.getWindow().setReturnTransition(new Fade());
        }
    }
}
