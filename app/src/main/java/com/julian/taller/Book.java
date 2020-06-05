package com.julian.taller;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("id")
    private String id;

    private volumeInfo volumeInfo;

    public String getId() {
        return id;
    }

    public com.julian.taller.volumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
