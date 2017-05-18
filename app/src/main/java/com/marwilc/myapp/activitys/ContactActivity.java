package com.marwilc.myapp.activitys;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.marwilc.myapp.R;

public class ContactActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText email;
    private TextInputEditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        addBtnSend();

        name      = (TextInputEditText) findViewById(R.id.etEditTextNameContact);
        email     = (TextInputEditText) findViewById(R.id.etEditTextEmailContact);
        message   = (TextInputEditText) findViewById(R.id.etEditTextMessageContact);


    }

    // add button send
    public void addBtnSend(){
        FloatingActionButton btnSend = (FloatingActionButton) findViewById(R.id.btnSendContact);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send email

            }
        });

    }
}
