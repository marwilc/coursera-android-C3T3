package com.marwilc.myapp.IOFile;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.JsonKeys;
import com.marwilc.myapp.restAPI.RestConstantsAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by marwilc on 16/06/17.
 * Package name  com.marwilc.myapp.IOFile
 * Time 19:46
 * Project  MyApp
 */

public class MyJson {
    static String fileName = "account-setting.txt";

    public static void saveData(Context context, String mJsonResponse){
        try {
            File root = Environment.getExternalStorageDirectory();
            //Log.i("path", Environment.getExternalStorageDirectory().getPath());
            //FileOutputStream fileOutputStream = new FileOutputStream(new File(root, fileName));
            //fileOutputStream.write(mJsonResponse.getBytes());
            //fileOutputStream.close();

            //FileWriter file = new FileWriter(context.getFilesDir().getPath() +"/" + fileName);
            FileWriter file = new FileWriter(root +"/" + fileName); // guardar archivo en la memoria externa
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static String getData(Context context){
        try {
            File root = Environment.getExternalStorageDirectory();
            //File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            File f = new File(root + "/" + fileName);
            //check whether file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }
}
