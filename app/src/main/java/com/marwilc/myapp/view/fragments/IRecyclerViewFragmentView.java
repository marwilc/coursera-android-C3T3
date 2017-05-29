package com.marwilc.myapp.view.fragments;

import com.marwilc.myapp.adapters.PetAdapter;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

/**
 * Created by marwilc on 28/05/17.
 */

public interface IRecyclerViewFragmentView {

    public void generateLinearLayoutVertical();
    public PetAdapter createAdapter(ArrayList<Pet> pets);
    public void initAdapterRV(PetAdapter adapter);
}
