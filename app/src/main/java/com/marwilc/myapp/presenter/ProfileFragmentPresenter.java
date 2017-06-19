package com.marwilc.myapp.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.marwilc.myapp.IOFile.MyJson;
import com.marwilc.myapp.R;
import com.marwilc.myapp.db.BuilderPets;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.PetResponse;
import com.marwilc.myapp.view.fragments.IRecyclerViewFragmentView;
import com.marwilc.myapp.view.fragments.ProfileFragment;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by marwilc on 15/06/17.
 * Package name  com.marwilc.myapp.presenter
 * Time 23:35
 * Project  MyApp
 */

public class ProfileFragmentPresenter implements IRecyclerViewFragmentPresenter {
    private View v;
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private BuilderPets builderPets;
    private ArrayList<Pet> alPets;
    private Activity activity;


    public ProfileFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context, Activity activity, View v) {

        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        this.activity= activity;
        this.v = v;

        String id = MyJson.getData(context); //retorna el Id guardado en el archivo
        if(id != null)
            getRecentMediaById(id);

    }

    @Override
    public void getPetsDB() {
        builderPets = new BuilderPets(context);
        alPets = builderPets.getData();
        showPetsRV();
    }

    @Override
    public void getRecentMediaById(String id) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializerMediaRecent();

        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestApiInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = iEndPointsAPI.getRecentMediaById(id); // modificar

        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse (Call < PetResponse > call, Response< PetResponse > response){

                PetResponse petResponse = response.body();


                alPets = petResponse.getPets();

                showPetsRV();

                setProfilePicture();

            }

            @Override
            public void onFailure (Call < PetResponse > Call, Throwable t){
                Toast.makeText(context, "Error connection try again", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE CONNECTION", t.toString());
            }
        });
    }

    @Override
    public void getDataUser(String userName) {

    }

    @Override
    public void getFollowsUser() {

    }


    @Override
    public void getRecentMedia() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializerMediaRecent();

        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestApiInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = iEndPointsAPI.getRecentMedia(); // modificar

        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse (Call < PetResponse > call, Response< PetResponse > response){

                PetResponse petResponse = response.body();


                alPets = petResponse.getPets();

                showPetsRV();




            }

            @Override
            public void onFailure (Call < PetResponse > Call, Throwable t){
                Toast.makeText(context, "Error connection try again", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE CONNECTION", t.toString());
            }
        });

    }

    // coloca la imagen de perfil y el nomber
    public void setProfilePicture() {
        CircularImageView civProfile    = (CircularImageView) v.findViewById(R.id.ivAvatarFragmentProfile);
        TextView tvName                 = (TextView) v.findViewById(R.id.tvNameFragmentProfile);
        Picasso.with(activity)
                .load(alPets.get(0).getUrlProfilePicture())
                .placeholder(R.drawable.generic_avatar)
                .into(civProfile);

        tvName.setText(alPets.get(0).getName());
    }

    @Override
    public void showPetsRV() {
        iRecyclerViewFragmentView.initProfileAdapterRV(iRecyclerViewFragmentView.createProfileAdapter(alPets));
        iRecyclerViewFragmentView.generateGridLayout();
    }

}
