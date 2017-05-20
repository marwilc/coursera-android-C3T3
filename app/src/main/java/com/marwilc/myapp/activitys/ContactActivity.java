package com.marwilc.myapp.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.marwilc.myapp.R;
import com.marwilc.myapp.connections.Mail;


import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class ContactActivity extends AppCompatActivity {

    private TextInputEditText user;
    private TextInputEditText pass;
    private String subject;
    private TextInputEditText body;
    private TextView recipient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar myActionBar = (Toolbar) findViewById(R.id.myActionBarContact);
        setSupportActionBar(myActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addBtnSend();

        user        = (TextInputEditText) findViewById(R.id.etEditTextEmailContact);
        pass        = (TextInputEditText) findViewById(R.id.etEditTextPassContact);
        subject     = "comment for app";
        body        = (TextInputEditText) findViewById(R.id.etEditTextBodyContact);
        recipient   = (TextView) findViewById(R.id.tvDeveloper);

    }


    // add button send
    public void addBtnSend(){
        FloatingActionButton btnSend = (FloatingActionButton) findViewById(R.id.btnSendContact);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send email
              sendMessage();
            }
        });

    }

    private void sendMessage() {
        String[] recipients = { recipient.getText().toString() };
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail(user.getText().toString(), pass.getText()
                .toString());
        email.m.set_from(user.getText().toString());
        email.m.setBody(body.getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject(subject);
        email.execute();
    }

    public void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.btnSendContact), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}


//background class
class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail m;
    ContactActivity activity;

    public SendEmailAsyncTask() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (m.send()) {
                activity.displayMessage("Email sent.");
            } else {
                activity.displayMessage("Email failed to send.");
            }

            return true;
        } catch (AuthenticationFailedException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
            e.printStackTrace();
            activity.displayMessage("Authentication failed.");
            return false;
        } catch (MessagingException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Email failed");
            e.printStackTrace();
            activity.displayMessage("Email failed to send.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            activity.displayMessage("Unexpected error occured.");
            return false;
        }
    }
}
