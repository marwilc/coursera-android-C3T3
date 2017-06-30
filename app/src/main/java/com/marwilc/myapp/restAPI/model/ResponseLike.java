package com.marwilc.myapp.restAPI.model;

/**
 * Created by marwilc on 29/06/17.
 * Package name  com.marwilc.myapp.restAPI.model
 * Time 22:39
 * Project  MyApp
 */

public class ResponseLike {

    private String data;
    private String code;


    public ResponseLike(String data, String code) {
        this.data = data;
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
