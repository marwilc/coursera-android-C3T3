package com.marwilc.myapp.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.marwilc.myapp.IOFile.MyJson;
import com.marwilc.myapp.R;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.presenter.IRecyclerViewFragmentPresenter;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.RestConstantsAPI;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.PetResponse;

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
                    //MyJson.saveData(getBaseContext(), pets.get(0).getId()); // guardar el Json en un archivo
                    sendToken(pets.get(0).getId());
                }

            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error connection try again", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE CONNECTION", t.toString());
            }
        });
    }

    // recibe un id de usuario y configura el id del dispositivo a registrarse para enviarlo a firebase
    public void sendToken  (String idUser) {
        String idDevice = FirebaseInstanceId.getInstance().getToken();
        sendTokenRegister(idDevice , idUser);
    }

    private void sendTokenRegister(String idDevice, String idUser){
        Log.d("ID_DEVICE",idDevice);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndPointsAPI iEndPoints = restApiAdapter.setConnectionRestAPIServer();
        Call<PetResponse> responseUserCall = iEndPoints.registerUser(idDevice, idUser);

        responseUserCall.enqueue(new Callback<PetResponse>() {

            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {

                PetResponse responseUser= response.body();
                Log.d("ID_DEVICE", responseUser.getToken());
                Log.d("ID_USER", responseUser.getId());
            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {

            }
        });
    }

    // termina el registro de usuarion

}
