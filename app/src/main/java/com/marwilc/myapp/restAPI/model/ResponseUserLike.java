package com.marwilc.myapp.restAPI.model;

/**
 * Created by marwilc on 30/06/17.
 * Package name  com.marwilc.myapp.restAPI.model
 * Time 0:08
 * Project  MyApp
 */

public class ResponseUserLike {
    private String id;
    private String id_device;
    private String id_user;
    private String id_picture;

    public ResponseUserLike(String id, String id_device, String id_user, String id_picture) {
        this.id = id;
        this.id_device = id_device;
        this.id_user = id_user;
        this.id_picture = id_picture;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_picture() {
        return id_picture;
    }

    public void setId_picture(String id_picture) {
        this.id_picture = id_picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
