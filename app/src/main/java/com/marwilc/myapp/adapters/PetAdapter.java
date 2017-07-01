package com.marwilc.myapp.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.marwilc.myapp.db.BuilderPets;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.R;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.RestConstantsAPI;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.ResponseLike;
import com.marwilc.myapp.restAPI.model.ResponseUser;
import com.marwilc.myapp.restAPI.model.ResponseUserLike;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marwilc on 15/05/17.
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{
    ArrayList<Pet> pets;
    Activity activity;

    public PetAdapter(ArrayList<Pet> pets, Activity activity){
        this.pets       = pets;
        this.activity   = activity;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_avatar,parent,false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PetViewHolder holder, int position) {
        final Pet pet = pets.get(position);
        Picasso.with(activity)
                .load(pet.getUrlPicture())
                .placeholder(R.drawable.generic_avatar)
                .into(holder.imgPicture);

        holder.tvNameCv.setText(pet.getName());
        holder.tvLikesCv.setText(String.valueOf(pet.getLikes()));

        holder.imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, pet.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapLike(pet);
                registerLikeToFireBase(pet);
                sendNotification(pet);

            }
        });

    }

    private void sendNotification(Pet pet) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestAPIServer();
        Call<ResponseUser>  responseUserCall = iEndPointsAPI.sendNotificationLike(pet.getId());

        responseUserCall.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                ResponseUser responseUser = response.body();

                Log.d("ID", responseUser.getId());
                Log.d("ID_DEVICE", responseUser.getIdDevice());
                Log.d("ID_USER", responseUser.getIdUser());

            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });


    }

    private void registerLikeToFireBase(Pet pet) {
        String idDevice = FirebaseInstanceId.getInstance().getToken();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndPointsAPI iEndPointsAPI = restApiAdapter.setConnectionRestAPIServer();
        Call<ResponseUserLike> responseUserLikeCall = iEndPointsAPI.registerUserLike(idDevice, pet.getId(), pet.getIdPicture());

        responseUserLikeCall.enqueue(new Callback<ResponseUserLike>() {
            @Override
            public void onResponse(Call<ResponseUserLike> call, Response<ResponseUserLike> response) {
                ResponseUserLike responseUserLike = response.body();

                Log.d("id", responseUserLike.getId());
                Log.d("id_device", responseUserLike.getId_device());
                Log.d("id_user", responseUserLike.getId_user());
                Log.d("id_picture", responseUserLike.getId_picture());

            }

            @Override
            public void onFailure(Call<ResponseUserLike> call, Throwable t) {

            }
        });
    }

    private void tapLike(Pet pet) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonSendLike = restApiAdapter.buildGsonDeserializerSendLike();
        IEndPointsAPI iEndPoints = restApiAdapter.setConnectionRestApiInstagram(gsonSendLike);
        Call<ResponseLike> responseLikeCall = iEndPoints.registerLike(RestConstantsAPI.ACCESS_TOKEN, pet.getIdPicture());

        responseLikeCall.enqueue(new Callback<ResponseLike>() {

            @Override
            public void onResponse(Call<ResponseLike> call, Response<ResponseLike> response) {

                ResponseLike responseLike = response.body();
                Log.d("DATA", responseLike.getData());
                Log.d("CODE", responseLike.getCode());
            }

            @Override
            public void onFailure(Call<ResponseLike> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPicture;
        private TextView tvNameCv;
        private TextView tvLikesCv;
        private ImageButton btnLike;

        public PetViewHolder(View itemView){
            super(itemView);
            imgPicture  = (ImageView) itemView.findViewById(R.id.imgAvatarCv);
            tvNameCv    = (TextView) itemView.findViewById(R.id.tvNameCv);
            tvLikesCv   = (TextView) itemView.findViewById(R.id.tvNumberCv);
            btnLike     = (ImageButton) itemView.findViewById(R.id.ibLike);

        }
    }
}
