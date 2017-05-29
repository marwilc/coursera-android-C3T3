package com.marwilc.myapp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.presenter.IRecyclerViewFragmentPresenter;
import com.marwilc.myapp.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    private RecyclerView rvPets;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        // Inflate the layout for this fragment

        rvPets = (RecyclerView) v.findViewById(R.id.rvAvatars);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }

    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pet> pets) {
        PetAdapter adapter = new PetAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public void initAdapterRV(PetAdapter adapter) {
        rvPets.setAdapter(adapter);

    }
}