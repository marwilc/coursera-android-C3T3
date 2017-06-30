package com.marwilc.myapp.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.marwilc.myapp.restAPI.IEndPointsAPI;
import com.marwilc.myapp.restAPI.RestConstantsAPI;
import com.marwilc.myapp.restAPI.deserializer.PetDeserializer;
import com.marwilc.myapp.restAPI.deserializer.ResponseLikeDeserializer;
import com.marwilc.myapp.restAPI.deserializer.UserDataDeserializer;
import com.marwilc.myapp.restAPI.model.PetResponse;
import com.marwilc.myapp.restAPI.model.ResponseLike;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marwilc on 14/06/17.
 * Package name  com.marwilc.myapp.restAPI.adapter
 * Time 18:46
 * Project  MyApp
 */

public class RestApiAdapter {

    // establece la conexion con la API de instagram
    public IEndPointsAPI setConnectionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(RestConstantsAPI.ROOT_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

        return retrofit.create(IEndPointsAPI.class);

    }

    public IEndPointsAPI setConnectionRestApiInstagram() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestConstantsAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IEndPointsAPI.class);
    }

    public IEndPointsAPI setConnectionRestAPIServer() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestConstantsAPI.ROOT_URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IEndPointsAPI.class);
    }
    // deserializador para la media recent data del usuario
    public Gson buildGsonDeserializerMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new PetDeserializer());

        return gsonBuilder.create();
    }

    // deserializador para la data del usuario
    public Gson buildGsonDeserializerDataUser() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetResponse.class, new UserDataDeserializer());

        return gsonBuilder.create();
    }


    public Gson buildGsonDeserializerSendLike() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ResponseLike.class, new ResponseLikeDeserializer());

        return gsonBuilder.create();
    }
}
