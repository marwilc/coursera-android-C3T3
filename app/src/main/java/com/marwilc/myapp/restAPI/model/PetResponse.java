package com.marwilc.myapp.restAPI.model;

import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

/**
 * Created by marwilc on 14/06/17.
 * Package name  com.marwilc.myapp.restAPI.model
 * Time 19:57
 * Project  MyApp
 */

public class PetResponse {
    ArrayList<Pet> pets;

    public ArrayList<Pet> getPets(){
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
