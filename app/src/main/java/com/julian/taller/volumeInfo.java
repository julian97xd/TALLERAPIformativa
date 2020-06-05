package com.julian.taller;

import com.google.gson.annotations.SerializedName;

public class volumeInfo {
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    imageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public com.julian.taller.imageLinks getImageLinks() {
        return imageLinks;
    }

    public String getDescription() {
        return description;
    }

}
