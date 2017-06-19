package com.marwilc.myapp.view.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.adapters.ProfileAdapter;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.presenter.IRecyclerViewFragmentPresenter;
import com.marwilc.myapp.presenter.ProfileFragmentPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements IRecyclerViewFragmentView {


    private RecyclerView rvProfile;
    private IRecyclerViewFragmentPresenter presenter;
    //private CircularImageView civProfile;
    //private TextView tvName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container,false);

        rvProfile     = (RecyclerView) v.findViewById(R.id.rvAvatars2);
        //civProfile    = (CircularImageView) v.findViewById(R.id.ivAvatarFragmentProfile);
        //tvName        = (TextView) v.findViewById(R.id.tvNameFragmentProfile);
        presenter = new ProfileFragmentPresenter(this,getContext(), getActivity(), v);
        return v;
    }

    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvProfile.setLayoutManager(llm);
    }

    @Override
    public void generateGridLayout() {
        GridLayoutManager glmGrid = new GridLayoutManager(getActivity(), 3);
        rvProfile.setLayoutManager(glmGrid);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pet> pets) {
        PetAdapter adapter = new PetAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public ProfileAdapter createProfileAdapter(ArrayList<Pet> pets) {
        ProfileAdapter adapter = new ProfileAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public void initAdapterRV(PetAdapter adapter) {
        rvProfile.setAdapter(adapter);
    }

    @Override
    public void initProfileAdapterRV(ProfileAdapter adapter) {
        rvProfile.setAdapter(adapter);
    }
}
