package com.marwilc.myapp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marwilc.myapp.R;
import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.adapters.ProfileAdapter;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.presenter.IRecyclerViewFragmentPresenter;
import com.marwilc.myapp.presenter.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView rvPets;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        // Inflate the layout for this fragment

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.srlRefreshLayout);
        rvPets = (RecyclerView) v.findViewById(R.id.rvAvatars);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });


        return v;
    }

    private void refreshContent() {
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void generateLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
    }

    @Override
    public void generateGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rvPets.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pet> pets) {
        PetAdapter adapter = new PetAdapter(pets, getActivity());
        return adapter;
    }

    @Override
    public ProfileAdapter createProfileAdapter(ArrayList<Pet> pets) {
        return null;
    }

    @Override
    public void initAdapterRV(PetAdapter adapter) {
        rvPets.setAdapter(adapter);
    }

    @Override
    public void initProfileAdapterRV(ProfileAdapter adapter) {

    }
}
