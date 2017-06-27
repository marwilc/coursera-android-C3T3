package com.marwilc.myapp.restAPI;

import com.marwilc.myapp.modelData.Pet;
import com.marwilc.myapp.restAPI.adapter.RestApiAdapter;
import com.marwilc.myapp.restAPI.model.PetResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.marwilc.myapp.restAPI.RestConstantsAPI.ACCESS_TOKEN;

/**
 * Created by marwilc on 14/06/17.
 * Package name  com.marwilc.myapp.restAPI
 * Time 20:23
 * Project  MyApp
 */

public interface IEndPointsAPI {
    //users/self/media/recent/?access_token=ACCESS-TOKEN
    @GET(RestConstantsAPI.URL_GET_RECENT_MEDIA_USER)
    Call<PetResponse> getRecentMedia();

    // genera la url completa
    // users/search?q=marwilc&access_token=5032752476.d7427a7.662119d95916416a9bf831bc60894c65

    @GET(RestConstantsAPI.KEY_GET_USER_BY_USERNAME)
    Call<PetResponse> getDataUser(@Query("q") String userName, @Query("access_token") String accessToken);

    // users/{id}/media/recent/?access_token=5032752476.d7427a7.662119d95916416a9bf831bc60894c65
    @GET(RestConstantsAPI.URL_GET_RECENT_MEDIA_BY_ID)
    Call<PetResponse> getRecentMediaById(@Path("id") String id);

    // users/self/follows?access_token=5032752476.d7427a7.662119d95916416a9bf831bc60894c65
    @GET(RestConstantsAPI.URL_GET_FOLLOWS_USER)
    Call<PetResponse> getFollowsUser();

    @FormUrlEncoded
    @POST(RestConstantsAPI.KEY_POST_INSTAGRAM_USER)
    Call<PetResponse> registerUser(@Field("id_device") String device, @Field("id_user") String user);


}
