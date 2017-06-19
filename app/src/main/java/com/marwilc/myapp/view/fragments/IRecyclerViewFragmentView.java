package com.marwilc.myapp.view.fragments;

import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.adapters.ProfileAdapter;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

/**
 * Created by marwilc on 28/05/17.
 */

public interface IRecyclerViewFragmentView {

    public void generateLinearLayoutVertical();

    public void generateGridLayout();

    public PetAdapter createAdapter(ArrayList<Pet> pets);

    public ProfileAdapter createProfileAdapter(ArrayList<Pet> pets);

    public void initAdapterRV(PetAdapter adapter);

    public void initProfileAdapterRV(ProfileAdapter adapter);
}
