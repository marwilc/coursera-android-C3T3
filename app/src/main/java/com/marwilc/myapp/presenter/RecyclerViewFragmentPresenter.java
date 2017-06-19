package com.marwilc.myapp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.marwilc.myapp.IOFile.MyJson;
import com.marwilc.myapp.db.BuilderPets;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.RestConstantsAPI;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.PetResponse;
import com.marwilc.myapp.view.fragments.IRecyclerViewFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marwilc on 28/05/17.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private BuilderPets builderPets;
    private ArrayList<Pet> alPets;
    private ArrayList<Pet> alPetsDefinitive = new ArrayList<>();

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView irecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = irecyclerViewFragmentView;
        this.context = context;
        //getPetsDB();
        //getRecentMedia();
        getFollowsUser();

    }

    @Override
    public void getPetsDB() {
        builderPets = new BuilderPets(context);
        alPets = builderPets.getData();
        showPetsRV();
    }

    @Override
    public void getRecentMedia() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializerMediaRecent();

        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestApiInstagram(gsonMediaRecent);
        Call<PetResponse> petResponseCall = iEndPointsAPI.getRecentMedia();

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

                ArrayList <Pet> auxPets = petResponse.getPets();

                if(!auxPets.isEmpty())
                    buildAlpets(auxPets);   // fabrica la lista de mascotas definitiva
            }

            @Override
            public void onFailure (Call < PetResponse > Call, Throwable t){
                Toast.makeText(context, "Error connection try again", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE CONNECTION", t.toString());
            }
        });

    }

    // construye la lista definitiva de mascotas

    public void buildAlpets(ArrayList<Pet> alPetsaux) {

        for (int i = 0; i < alPetsaux.size(); i++) {
            this.alPetsDefinitive.add(alPetsaux.get(i));
        }
        showPetsRV();
    }

    @Override
    public void getDataUser(String userName) {

    }

    @Override
    public void getFollowsUser() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonDataUser = restApiAdapter.buildGsonDeserializerDataUser();

        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestApiInstagram(gsonDataUser);
        Call<PetResponse> petResponseCall = iEndPointsAPI.getFollowsUser();


        petResponseCall.enqueue(new Callback<PetResponse>() {
            @Override
            public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {

                PetResponse petResponse = response.body();

                ArrayList<Pet> alPetsFollows = petResponse.getPets();

                for (int i = 0; i < alPetsFollows.size() -1 ; i++){
                    getRecentMediaById(alPetsFollows.get(i).getId());
                }



            }

            @Override
            public void onFailure(Call<PetResponse> call, Throwable t) {
                Toast.makeText(context, "Error connection try again", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE CONNECTION", t.toString());
            }
        });

    }

    @Override
    public void showPetsRV() {
        iRecyclerViewFragmentView.initAdapterRV(iRecyclerViewFragmentView.createAdapter(this.alPetsDefinitive));
        iRecyclerViewFragmentView.generateLinearLayoutVertical();
    }
}
