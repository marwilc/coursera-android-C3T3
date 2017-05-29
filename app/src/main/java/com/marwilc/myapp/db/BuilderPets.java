package com.marwilc.myapp.db;

import android.content.ContentValues;
import android.content.Context;

import com.marwilc.myapp.R;
import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by marwilc on 28/05/17.
 */

public class BuilderPets {

    private static final int LIKE = 1;
    private Context context;

    public BuilderPets(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> getData(){

        DataBase db = new DataBase(context);
        insertPets(db);
        return db.getAllPets();
    }


    public ArrayList<Pet> getDataFavorites() {
        DataBase db = new DataBase(context);
        return  db.getLastFivePets();
    }

    public void insertPets(DataBase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Steam");
        contentValues.put(DataBaseConstants.TABLE_PETS_PICTURE, R.drawable.dog1);

        db.insertPet(contentValues);


        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Pitus");
        contentValues.put(DataBaseConstants.TABLE_PETS_PICTURE, R.drawable.panda2);

        db.insertPet(contentValues);

        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Fido");
        contentValues.put(DataBaseConstants.TABLE_PETS_PICTURE, R.drawable.pig3);

        db.insertPet(contentValues);

        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Try");
        contentValues.put(DataBaseConstants.TABLE_PETS_PICTURE, R.drawable.pinguin4);

        db.insertPet(contentValues);

        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Tin");
        contentValues.put(DataBaseConstants.TABLE_PETS_PICTURE, R.drawable.rabbit6);

        db.insertPet(contentValues);


    }

    public void toLikePet (Pet pet) {
        DataBase db = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        Calendar c = Calendar.getInstance();
        contentValues.put(DataBaseConstants.TABLE_LIKES_PET_ID_PET, pet.getId());
        contentValues.put(DataBaseConstants.TABLE_LIKES_PET_NUMBER_LIKES, LIKE);
        contentValues.put(DataBaseConstants.TABLE_LIKES_PET_DATE,c.get(Calendar.SECOND));
        db.insertLikePet(contentValues);
    }


    public int getLikesPet(Pet pet){
        DataBase db = new DataBase(context);
        return db.getLikesPet(pet);
    }


}
