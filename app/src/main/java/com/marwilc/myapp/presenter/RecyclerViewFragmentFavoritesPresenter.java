package com.marwilc.myapp.presenter;

import android.content.Context;

import com.marwilc.myapp.db.BuilderPets;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.view.fragments.IRecyclerViewFragmentView;
import com.marwilc.myapp.view.fragments.RecyclerViewFragmentFavorites;

import java.util.ArrayList;

/**
 * Created by marwilc on 28/05/17.
 */

public class RecyclerViewFragmentFavoritesPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private BuilderPets builderPets;
    private ArrayList<Pet> alPets;

    public RecyclerViewFragmentFavoritesPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        getPetsDB();

    }

    @Override
    public void getPetsDB() {
        builderPets = new BuilderPets(context);
        alPets = builderPets.getDataFavorites(); // obtener data de favorites

        showPetsRV();
    }

    @Override
    public void getRecentMedia() {

    }

    @Override
    public void getRecentMediaById(String id) {

    }

    @Override
    public void getDataUser(String userName) {

    }

    @Override
    public void getFollowsUser() {

    }

    @Override
    public void showPetsRV() {
        iRecyclerViewFragmentView.initAdapterRV(iRecyclerViewFragmentView.createAdapter(alPets));
        iRecyclerViewFragmentView.generateLinearLayoutVertical();
    }
}
