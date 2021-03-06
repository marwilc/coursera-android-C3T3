package com.marwilc.myapp.view;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.marwilc.myapp.IOFile.MyJson;
import com.marwilc.myapp.R;
import com.marwilc.myapp.db.adapter.InstagramUserAdapterDB;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.presenter.IRecyclerViewFragmentPresenter;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.RestConstantsAPI;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.PetResponse;
import com.marwilc.myapp.restAPI.model.ResponseUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {

    private TextInputEditText   tietAccountUser;
    private ArrayList<Pet> pets;
    private Button btnSaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        tietAccountUser = (TextInputEditText) findViewById(R.id.tiUser);
        btnSaveAccount  = (Button)            findViewById(R.id.btnSaveAccount);

        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = tietAccountUser.getText().toString();

                getDataUser(userName);

                Snackbar.make(btnSaveAccount, "succes", Snackbar.LENGTH_LONG).show();

            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // implementacion de transiciones slide
            Slide slide = new Slide();
            slide.setDuration(1000);
            this.getWindow().setEnterTransition(slide);
            this.getWindow().setReturnTransition(new Fade());
        }


    }

    private void getDataUser(String userName) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonDataUser = restApiAdapter.buildGsonDeserializerDataUser();

        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestApiInstagram(gsonDataUser);
        Call<PetResponse> petResponseCall = iEndPointsAPI.getDataUser(userName, RestConstantsAPI.ACCESS_TOKEN);


        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {

                PetResponse petResponse = response.body();

                pets = petResponse.getPets();

                if (pets.size()>0) {
                    seveUserInstagramToDB(pets.get(0).getId()); // guarda los datos del usuario en la base de datos local
                    //sendToken(pets.get(0).getId());

                }

            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error connection try again", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE CONNECTION", t.toString());

            }
        });
    }

    private void seveUserInstagramToDB(String id) {
        //String idDevice = FirebaseInstanceId.getInstance().getToken(); // obtiene el id del dispositivo actual
        InstagramUserAdapterDB userAdapterDB = new InstagramUserAdapterDB(getBaseContext());
        //userAdapterDB.insertUserToTableInstagram(id); // inserta ambos id en la BD
        userAdapterDB.updateUserToTableInstagram(id);
        //sendTokenRegister(idDevice, idUser);
    }
    // termina el registro de usuarion

}
