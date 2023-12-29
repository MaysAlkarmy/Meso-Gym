package com.example.sportapp.models;

import com.google.gson.annotations.SerializedName;

public class Banner {


    String image_url;

    public Banner( String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image) {
        this.image_url = image;
    }


}
