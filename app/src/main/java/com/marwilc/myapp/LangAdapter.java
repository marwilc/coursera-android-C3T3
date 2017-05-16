package com.marwilc.myapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by marwilc on 15/05/17.
 */

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.LangViewHolder>{
    ArrayList<Language> languages;
    Activity activity;

    public LangAdapter(ArrayList<Language> languages, Activity activity){
        this.languages  = languages;
        this.activity   = activity;
    }

    @Override
    public LangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_avatar,parent,false);
        return new LangViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LangAdapter.LangViewHolder holder, int position) {
        final Language language = languages.get(position);
        holder.imgPicture.setImageResource(language.getPicture());
        holder.tvNameCv.setText(language.getName());
        holder.tvLikesCv.setText(Integer.toString(language.getLikes()));

        holder.imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, language.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language.setLikes(language.getLikes()+1);
                Toast.makeText(activity, "like:" + language.getLikes(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public static class LangViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPicture;
        private TextView tvNameCv;
        private TextView tvLikesCv;
        private ImageButton btnLike;

        public LangViewHolder(View itemView){
            super(itemView);
            imgPicture  = (ImageView) itemView.findViewById(R.id.imgAvatarCv);
            tvNameCv    = (TextView) itemView.findViewById(R.id.tvNameCv);
            tvLikesCv   = (TextView) itemView.findViewById(R.id.tvNumberCv);
            btnLike     = (ImageButton) itemView.findViewById(R.id.ibLike);

        }
    }
}
