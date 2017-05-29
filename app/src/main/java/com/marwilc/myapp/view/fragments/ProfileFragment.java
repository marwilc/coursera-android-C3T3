package com.marwilc.myapp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.ProfileAdapter;
import com.marwilc.myapp.modelData.Pet;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private RecyclerView rvProfile;
    private ArrayList<Pet> alPets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container,false);

        rvProfile = (RecyclerView) v.findViewById(R.id.rvAvatars2);
        CircularImageView civProfile    = (CircularImageView) v.findViewById(R.id.ivAvatarFragmentProfile);
        TextView tvName                 = (TextView) v.findViewById(R.id.tvNameFragmentProfile);

        GridLayoutManager glmGrid = new GridLayoutManager(getActivity(), 3);
        rvProfile.setLayoutManager(glmGrid);

        initProfileList();
        initAdapter();

        civProfile.setImageResource(alPets.get(0).getPicture());
        tvName.setText(alPets.get(0).getName());

        return v;
    }


    public void initProfileList(){

        alPets = new ArrayList<>();
        alPets.add(new Pet("Steam", 5, R.drawable.dog1));
        alPets.add(new Pet("Pitus", 7, R.drawable.dog1));
        alPets.add(new Pet("Fido", 8, R.drawable.dog1));
        alPets.add(new Pet("Try", 9, R.drawable.dog1));
        alPets.add(new Pet("Reapper", 0, R.drawable.dog1));
        alPets.add(new Pet("Tin", 1, R.drawable.dog1));
    }
    public void initAdapter(){

        ProfileAdapter adapter = new ProfileAdapter(alPets, getActivity());
        rvProfile.setAdapter(adapter);


    }

}
