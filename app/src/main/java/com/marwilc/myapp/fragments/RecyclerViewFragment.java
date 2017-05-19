package com.marwilc.myapp.fragments;


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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {


    private ArrayList<Pet> alPets;
    private RecyclerView rvPets;

    public ArrayList<Pet> getAlPets() {
        return alPets;
    }

    public void setAlPets(ArrayList<Pet> alPets) {
        this.alPets = alPets;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        // Inflate the layout for this fragment

        rvPets = (RecyclerView) v.findViewById(R.id.rvAvatars);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvPets.setLayoutManager(llm);

        initPetList();
        initAdapter();
        return v;
    }

    public void initPetList(){

        alPets = new ArrayList<>();
        alPets.add(new Pet("Steam", 0, R.drawable.dog1));
        alPets.add(new Pet("Pitus", 0, R.drawable.panda2));
        alPets.add(new Pet("Fido", 0, R.drawable.pig3));
        alPets.add(new Pet("Try", 0, R.drawable.pinguin4));
        alPets.add(new Pet("Reapper", 0, R.drawable.cat5));
        alPets.add(new Pet("Tin", 0, R.drawable.rabbit6));
    }

    public void initAdapter(){
        PetAdapter adapter = new PetAdapter(alPets,getActivity());
        rvPets.setAdapter(adapter);

    }

}
