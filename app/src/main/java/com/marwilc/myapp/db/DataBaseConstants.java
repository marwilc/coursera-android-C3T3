package com.marwilc.myapp.db;

/**
 * Created by marwilc on 28/05/17.
 */

public final class DataBaseConstants {
    public static final String DATABASE_NAME = "pets";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PETS = "pet";
    public static final String TABLE_PETS_ID = "id";
    public static final String TABLE_PETS_NAME = "name";
    public static final String TABLE_PETS_PICTURE = "picture";

    public static final String TABLE_LIKES_PET = "pet_likes";
    public static final String TABLE_LIKES_PET_ID = "id";
    public static final String TABLE_LIKES_PET_ID_PET = "pet_id";
    public static final String TABLE_LIKES_PET_NUMBER_LIKES = "number_likes";
    public static final String TABLE_LIKES_PET_DATE = "date_likes" ;
}
