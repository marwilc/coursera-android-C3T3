package com.marwilc.myapp.restAPI.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.marwilc.myapp.restAPI.JsonKeys;
import com.marwilc.myapp.restAPI.RestConstantsAPI;
import com.marwilc.myapp.restAPI.model.PetResponse;
import com.marwilc.myapp.restAPI.model.ResponseLike;

import java.lang.reflect.Type;

/**
 * Created by marwilc on 29/06/17.
 * Package name  com.marwilc.myapp.restAPI.deserializer
 * Time 22:45
 * Project  MyApp
 */

public class ResponseLikeDeserializer implements JsonDeserializer<ResponseLike>{
    @Override
    public ResponseLike deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        ResponseLike responseLike = gson.fromJson(json, ResponseLike.class);

        String data                 = json.getAsJsonObject().get(JsonKeys.MEDIA_RESPONSE_ARRAY).getAsString();
        JsonObject likeResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.JSON_META);
        String code                 = likeResponseData.get(JsonKeys.JSON_CODE).getAsString();
        responseLike.setData(data);
        responseLike.setCode(code);

        return responseLike;
    }
}
