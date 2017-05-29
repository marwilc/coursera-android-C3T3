package com.marwilc.myapp.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marwilc.myapp.R;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

/**
 * Created by marwilc on 19/05/17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>{
    ArrayList<Pet> pets;
    Activity activity;


    public ProfileAdapter(ArrayList<Pet> pets, Activity activity) {
        this.pets = pets;
        this.activity = activity;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_avatar2,parent,false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {

        final Pet pet = pets.get(position);
        String likes= Integer.toString(pet.getLikes());
        holder.ivPicture.setImageResource(pet.getPicture());
        holder.tvlikes.setText(likes);
    }


    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPicture;
        private TextView tvlikes;


        public ProfileViewHolder(View itemView) {
            super(itemView);

            ivPicture   = (ImageView) itemView.findViewById(R.id.imgAvatarCv2);
            tvlikes     = (TextView) itemView.findViewById(R.id.tvNumberCv2);
        }
    }
}
