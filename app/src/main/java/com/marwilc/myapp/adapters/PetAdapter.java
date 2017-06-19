package com.marwilc.myapp.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marwilc.myapp.db.BuilderPets;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        //holder.imgPicture.setImageResource(pet.getPicture());
        holder.tvNameCv.setText(pet.getName());
        holder.tvLikesCv.setText(String.valueOf(pet.getLikes()));

        holder.imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, pet.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        /*
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "like:" + holder.tvLikesCv.getText(),Toast.LENGTH_SHORT).show();
                BuilderPets builderPets = new BuilderPets(activity);
                builderPets.toLikePet(pet);

                holder.tvLikesCv.setText(String.valueOf(builderPets.getLikesPet(pet)));

            }
        });
        */
    }


    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPicture;
        private TextView tvNameCv;
        private TextView tvLikesCv;
        //private ImageButton btnLike;

        public PetViewHolder(View itemView){
            super(itemView);
            imgPicture  = (ImageView) itemView.findViewById(R.id.imgAvatarCv);
            tvNameCv    = (TextView) itemView.findViewById(R.id.tvNameCv);
            tvLikesCv   = (TextView) itemView.findViewById(R.id.tvNumberCv);
            //btnLike     = (ImageButton) itemView.findViewById(R.id.ibLike);

        }
    }
}
