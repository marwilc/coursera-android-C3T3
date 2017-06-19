package com.marwilc.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.marwilc.myapp.modelData.Pet;

import java.util.ArrayList;

/**
 * Created by marwilc on 28/05/17.
 */

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreatePetTable = "CREATE TABLE " + DataBaseConstants.TABLE_PETS + "(" +
                                    DataBaseConstants.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    DataBaseConstants.TABLE_PETS_NAME + " TEXT, " +
                                    DataBaseConstants.TABLE_PETS_PICTURE + " INTEGER" +
                                    ")";

        String queryCreatePetLikesTable = "CREATE TABLE " + DataBaseConstants.TABLE_LIKES_PET + "(" +
                                        DataBaseConstants.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        DataBaseConstants.TABLE_LIKES_PET_ID_PET + " INTEGER, " +
                                        DataBaseConstants.TABLE_LIKES_PET_NUMBER_LIKES + " INTEGER, " +
                                        DataBaseConstants.TABLE_LIKES_PET_DATE + " INTEGER, " +
                                        "FOREIGN KEY (" + DataBaseConstants.TABLE_LIKES_PET_ID_PET + ") " +
                                        "REFERENCES " + DataBaseConstants.TABLE_PETS + "(" + DataBaseConstants.TABLE_PETS_ID + ")" +
                                        ")";
        db.execSQL(queryCreatePetTable);
        db.execSQL(queryCreatePetLikesTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXIST " + DataBaseConstants.TABLE_LIKES_PET);
    }

    public ArrayList<Pet> getAllPets(){
        ArrayList<Pet> alPets = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query , null);

        while (registers.moveToNext()){
            int tempLikes;
            Pet currentPet = new Pet();
            currentPet.setId(registers.getString(0));
            currentPet.setName(registers.getString(1));
            currentPet.setUrlPicture(registers.getString(2));

            tempLikes = getLikesPet(currentPet);
            currentPet.setLikes(tempLikes);
            alPets.add(currentPet);
        }

        db.close();
        return alPets;
    }

    // obtiene todas las mascotas con likes
    public ArrayList<Pet> getLastFivePets(){

        int tempLikes;
        ArrayList<Pet> alPets = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query , null);

        while (registers.moveToNext() && alPets.size()<5){
            Pet currentPet = new Pet();
            currentPet.setId(registers.getString(0));
            currentPet.setName(registers.getString(1));
            currentPet.setUrlPicture(registers.getString(2));

            tempLikes = getLikesPet(currentPet);

            currentPet.setLikes(tempLikes);
            if (currentPet.getLikes()>0)
                alPets.add(currentPet);

        }

        db.close();
        return alPets;
    }

    public void insertPet (ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_PETS , null, contentValues);
        db.close();
    }

    public void insertLikePet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_LIKES_PET , null, contentValues);
        db.close();
    }

    public int getLikesPet (Pet pet) {
        int likes = 0;

        String query = "SELECT COUNT(" + DataBaseConstants.TABLE_LIKES_PET_NUMBER_LIKES + ")" +
                        " FROM " + DataBaseConstants.TABLE_LIKES_PET +
                        " WHERE " + DataBaseConstants.TABLE_LIKES_PET_ID_PET + "=" + pet.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query,null);

        if(registers.moveToNext()){
            likes = registers.getInt(0);
        }

        db.close();
        return likes;
    }


}
