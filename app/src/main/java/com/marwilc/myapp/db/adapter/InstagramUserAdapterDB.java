package com.marwilc.myapp.db.adapter;

import android.content.ContentValues;
import android.content.Context;

import com.marwilc.myapp.db.DataBase;
import com.marwilc.myapp.db.DataBaseConstants;
import com.marwilc.myapp.restAPI.model.ResponseUser;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by marwilc on 27/06/17.
 * Package name  com.marwilc.myapp.db.adapter
 * Time 17:32
 * Project  MyApp
 */

public class InstagramUserAdapterDB {

    private Context context;

    public InstagramUserAdapterDB(Context context) {
        this.context = context;
    }


    public void insertUserToTableInstagram(String idUser) {
        DataBase db = new DataBase(context);

        if(checkDistinctIdUser(db, idUser)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.TABLE_USER_INSTAGRAM_ID_USER, idUser);
            db.insertUserIntagram(contentValues);
        }
    }

    // chequea si hya elementos repetidos retorna verdad si esta vacio y falso en caso contrario
    private boolean checkDistinctIdUser(DataBase db, String idUser) {

        ArrayList<ResponseUser> userArrayList;
        userArrayList = db.getUserIdById(idUser);
        return(userArrayList.isEmpty());
    }


    public ArrayList<ResponseUser> getData() {
        DataBase db = new DataBase(context);
        return db.getAllAccountsRegisterOnThisDevice();
    }
}
