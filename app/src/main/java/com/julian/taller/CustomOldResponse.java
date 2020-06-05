package com.julian.taller;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomOldResponse {
    @SerializedName("items")
    private ArrayList<Book> boos;

    public ArrayList<Book> getGetBooks() {
        return boos;
    }
}
