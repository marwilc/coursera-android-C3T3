package com.marwilc.myapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.model.ResponseUser;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

        // query: creando tabla pets
        String queryCreatePetTable = "CREATE TABLE " + DataBaseConstants.TABLE_PETS + "(" +
                                    DataBaseConstants.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    DataBaseConstants.TABLE_PETS_NAME + " TEXT, " +
                                    DataBaseConstants.TABLE_PETS_PICTURE + " INTEGER" +
                                    ")";

        // query creando tabla likes
        String queryCreatePetLikesTable = "CREATE TABLE " + DataBaseConstants.TABLE_LIKES_PET + "(" +
                                        DataBaseConstants.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        DataBaseConstants.TABLE_LIKES_PET_ID_PET + " INTEGER, " +
                                        DataBaseConstants.TABLE_LIKES_PET_NUMBER_LIKES + " INTEGER, " +
                                        DataBaseConstants.TABLE_LIKES_PET_DATE + " INTEGER, " +
                                        "FOREIGN KEY (" + DataBaseConstants.TABLE_LIKES_PET_ID_PET + ") " +
                                        "REFERENCES " + DataBaseConstants.TABLE_PETS + "(" + DataBaseConstants.TABLE_PETS_ID + ")" +
                                        ")";

        // query creando tabla user_instagram
        String queryCreateUserInstagramTable = "CREATE TABLE " + DataBaseConstants.TABLE_USER_INSTAGRAM + "(" +
                DataBaseConstants.TABLE_USER_INSTAGRAM_ID_USER + " TEXT PRIMARY KEY " +
                ")";


        db.execSQL(queryCreatePetTable);
        db.execSQL(queryCreatePetLikesTable);
        db.execSQL(queryCreateUserInstagramTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_LIKES_PET);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_USER_INSTAGRAM);
    }

    // retorna toda la base de datos
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

    //retorna los likes de una mascota
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

    // obtiene todas las cuentas registradas en este dispositivo
    public ArrayList<ResponseUser> getAllAccountsRegisterOnThisDevice(){

        ArrayList<ResponseUser> userArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + DataBaseConstants.TABLE_USER_INSTAGRAM;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query , null);

        while (registers.moveToNext()){
            ResponseUser currentUser = new ResponseUser();
            currentUser.setIdUser(registers.getString(0));

            userArrayList.add(currentUser);
        }

        db.close();
        return userArrayList;
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

    public void insertUserIntagram(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_USER_INSTAGRAM, null , contentValues);
        db.close();
    }

    public void updateUserIntagram(ContentValues contentValues, String idOld) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DataBaseConstants.TABLE_USER_INSTAGRAM, contentValues ,
                DataBaseConstants.TABLE_USER_INSTAGRAM_ID_USER + " = " + idOld, null);
        db.close();

    }


    public ArrayList<ResponseUser> getUserIdById(String idUser) {

        ArrayList<ResponseUser> userArrayList = new ArrayList<>();
        String query = "SELECT " + DataBaseConstants.TABLE_USER_INSTAGRAM_ID_USER +
                        " FROM " + DataBaseConstants.TABLE_USER_INSTAGRAM +
                        " WHERE " + DataBaseConstants.TABLE_USER_INSTAGRAM_ID_USER + "=" + idUser;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registers = db.rawQuery(query , null);

        while (registers.moveToNext()){
            ResponseUser currentUser = new ResponseUser();
            currentUser.setIdUser(registers.getString(0));
            userArrayList.add(currentUser);
        }
        db.close();
        return userArrayList;
    }


}