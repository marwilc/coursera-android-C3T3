package com.marwilc.myapp.restAPI.model;

/**
 * Created by marwilc on 26/06/17.
 * Package name  com.marwilc.notificationfirebase.model
 * Time 23:13
 * Project  NotificacionFirebase
 */

public class ResponseUser {
    private String id;
    private String id_device;
    private String id_user;

    public ResponseUser(String id, String idDevice, String idUser) {
        this.id = id;
        this.id_device = idDevice;
        this.id_user = idUser;
    }

    public ResponseUser() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDevice() {
        return id_device;
    }

    public void setIdDevice(String idDevice) {
        this.id_device = idDevice;
    }

    public String getIdUser() {
        return id_user;
    }

    public void setIdUser(String idUser) {
        this.id_user = idUser;
    }
}

