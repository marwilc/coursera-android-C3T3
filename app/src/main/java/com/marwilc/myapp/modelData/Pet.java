package com.marwilc.myapp.modelData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marwilc on 15/05/17.
 */

public class Pet implements Parcelable{
    private String name;
    private int likes;
    private int picture;

    public Pet(String name, int likes, int picture) {
        this.name = name;
        this.likes = likes;
        this.picture = picture;
    }

    protected Pet(Parcel in) {
        name = in.readString();
        likes = in.readInt();
        picture = in.readInt();
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(likes);
        dest.writeInt(picture);
    }
}
