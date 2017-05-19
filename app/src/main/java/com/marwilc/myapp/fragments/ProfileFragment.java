package com.marwilc.myapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.ProfileAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private RecyclerView rvProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container,false);

        rvProfile = (RecyclerView) v.findViewById(R.id.rvAvatars2);

        GridLayoutManager glmGrid = new GridLayoutManager(getActivity(), 3);
        rvProfile.setLayoutManager(glmGrid);

        initAdapter();
        return v;
    }

    public void initAdapter(){

        ProfileAdapter adapter = new ProfileAdapter(alPetProfiles, getActivity());
        rvProfile.setAdapter(adapter);


    }

}
