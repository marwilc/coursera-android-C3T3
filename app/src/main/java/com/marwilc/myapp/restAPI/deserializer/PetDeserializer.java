package com.marwilc.myapp.restAPI.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.JsonKeys;
import com.marwilc.myapp.restAPI.model.PetResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by marwilc on 14/06/17.
 * Package name  com.marwilc.myapp.restAPI.deserializer
 * Time 19:43
 * Project  MyApp
 */

public class PetDeserializer implements JsonDeserializer<PetResponse> {

    @Override
    public PetResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetResponse petResponse = gson.fromJson(json, PetResponse.class);
        JsonArray petResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petResponse.setPets(deserializerPetJson(petResponseData));

        return petResponse;
    }

    private ArrayList<Pet> deserializerPetJson(JsonArray petResponseData){
        ArrayList<Pet> pets = new ArrayList<>();

        for (int i = 0; i < petResponseData.size(); i++) {
            JsonObject petResponseDataObject = petResponseData.get(i).getAsJsonObject();

            JsonObject userJsonObject = petResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id                 = userJsonObject.get(JsonKeys.USER_ID).getAsString();
            String fullName           = userJsonObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String profilePicture     = userJsonObject.get(JsonKeys.MEDIA_PROFILE_PICTURE).getAsString();

            JsonObject imageJsonObject           = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJsonObject   = imageJsonObject.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlPicture                    = stdResolutionJsonObject.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJsonObject = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes                  = likesJsonObject.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Pet currentPet  = new Pet();
            currentPet.setId(id);
            currentPet.setName(fullName);
            currentPet.setUrlPicture(urlPicture);
            currentPet.setUrlProfilePicture(profilePicture);
            currentPet.setLikes(likes);

            pets.add(currentPet);
        }
        return pets;
    }

}
