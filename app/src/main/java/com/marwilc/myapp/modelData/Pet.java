package com.marwilc.myapp.modelData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marwilc on 15/05/17.
 */

public class Pet implements Parcelable{
    private String id;
    private String name;
    private int likes = 0;
    private String urlPicture;
    private String urlProfilePicture;
    private String idPicture;

    public Pet(String name, int likes, String urlPicture, String urlProfilePicture, String idPicture) {
        this.name               = name;
        this.likes              = likes;
        this.urlPicture         = urlPicture;
        this.urlProfilePicture  = urlProfilePicture;
        this.idPicture          = idPicture;
    }

    protected Pet(Parcel in) {
        name               = in.readString();
        likes              = in.readInt();
        urlPicture         = in.readString();
        idPicture          = in.readString();
        id                 = in.readString();
        urlProfilePicture  = in.readString();
    }

    public static final Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public Pet() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getUrlProfilePicture() {
        return urlProfilePicture;
    }

    public void setUrlProfilePicture(String urlProfilePicture) {
        this.urlProfilePicture = urlProfilePicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(likes);
        dest.writeString(urlPicture);
        dest.writeString(urlProfilePicture);
        dest.writeString(idPicture);
        dest.writeString(id);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setIdPicture(String idPicture) {
        this.idPicture = idPicture;
    }

    public String getIdPicture() {
        return idPicture;
    }
}
