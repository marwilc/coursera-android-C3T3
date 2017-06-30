package com.marwilc.myapp.restAPI;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * Created by marwilc on 14/06/17.
 * Package name  com.marwilc.myapp.restAPI
 * Time 20:26
 * Project  MyApp
 */

public final class RestConstantsAPI {
    // url base
    public static final String VERSION = "/v1/";
    public static final String ACCESS_TOKEN = "5032752476.d7427a7.662119d95916416a9bf831bc60894c65";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String KEY_GET_RECENT_MEDIA_BY_ID = "users/{id}/media/recent/";
    public static final String KEY_GET_FOLLOWS_USER = "users/self/follows";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;

    // direccion de la aplicacion node.js
    public static final String ROOT_URL_SERVER = "https://fathomless-ravine-76673.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";
    public static final String KEY_POST_USER_INSTAGRAM = "user-instagram/";

    // users/search
    public static final String KEY_GET_USER_BY_USERNAME = "users/search";

    // users/self/media/recent/?access_token=ACCESS-TOKEN // obtiene el contenido multimedia del usuario
    public static final String URL_GET_RECENT_MEDIA_USER =  KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN +
            ACCESS_TOKEN;

    // users/{id}/media/recent/?access_token=5032752476.d7427a7.662119d95916416a9bf831bc60894c65
    public static final String URL_GET_RECENT_MEDIA_BY_ID = KEY_GET_RECENT_MEDIA_BY_ID + KEY_ACCESS_TOKEN +
            ACCESS_TOKEN;

    // users/self/follows?access_token=5032752476.d7427a7.662119d95916416a9bf831bc60894c65
    public static final String URL_GET_FOLLOWS_USER = KEY_GET_FOLLOWS_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_USER_SEND_LIKE = "media/{media-id}/likes";
}
