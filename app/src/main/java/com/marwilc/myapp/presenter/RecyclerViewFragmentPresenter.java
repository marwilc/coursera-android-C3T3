package com.marwilc.myapp.presenter;

import android.content.Context;

import com.marwilc.myapp.db.BuilderPets;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.view.fragments.IRecyclerViewFragmentView;
import com.marwilc.myapp.view.fragments.RecyclerViewFragment;

import java.util.ArrayList;

/**
 * Created by marwilc on 28/05/17.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private BuilderPets builderPets;
    private ArrayList<Pet> alPets;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView irecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = irecyclerViewFragmentView;
        this.context = context;
        getPetsDB();

    }

    @Override
    public void getPetsDB() {
        builderPets = new BuilderPets(context);
        alPets = builderPets.getData();
        showPetsRV();
    }

    @Override
    public void showPetsRV() {
        iRecyclerViewFragmentView.initAdapterRV(iRecyclerViewFragmentView.createAdapter(alPets));
        iRecyclerViewFragmentView.generateLinearLayoutVertical();
    }
}
