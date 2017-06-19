package com.marwilc.myapp.restAPI.deserializer;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.marwilc.myapp.IOFile.MyJson;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.JsonKeys;
import com.marwilc.myapp.restAPI.model.PetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by marwilc on 16/06/17.
 * Package name  com.marwilc.myapp.restAPI.deserializer
 * Time 13:23
 * Project  MyApp
 */

public class UserDataDeserializer implements JsonDeserializer <PetResponse>{
    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petResponse.setPets(deserializerDataUserJson(petResponseData));
        return petResponse;
    }

    // deserializacion de la data de un usuario
    private ArrayList<Pet> deserializerDataUserJson(JsonArray petResponseData) {
        ArrayList<Pet> pets = new ArrayList<>();

        for (int i = 0; i < petResponseData.size(); i++) {
            JsonObject petResponseDataJsonObject = petResponseData.get(i).getAsJsonObject();


            // se obtienen los datos del usuario en forma de Json y se asignan a variables comunes
            String id           = petResponseDataJsonObject.get(JsonKeys.USER_ID).getAsString();    // se obtiene el id
            //String userName     = petResponseDataJsonObject.get(JsonKeys.USER_NAME).getAsString();// se obtienes el username en minuscula
            String urlProfilePicture   = petResponseDataJsonObject.get(JsonKeys.MEDIA_PROFILE_PICTURE).getAsString();  // se obtiene la foto de perfil del usuario
            String fullName     = petResponseDataJsonObject.get(JsonKeys.USER_FULLNAME).getAsString(); // se obtiene el

            Pet currentPet  = new Pet(); // se creea el nuevo objeto para asigarle los datos obtenido del Json
            currentPet.setId(id);
            currentPet.setName(fullName);
            currentPet.setUrlProfilePicture(urlProfilePicture);


            pets.add(currentPet); // se agrega el objeto a la lista en caso de que haya mas datos


        }
        return pets;
    }
}
