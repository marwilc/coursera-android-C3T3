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
    private ArrayList<Pet> pets;
    private String token;
    private String id;

    public PetResponse(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Pet> getPets(){
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
