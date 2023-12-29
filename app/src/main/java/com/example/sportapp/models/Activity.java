package com.example.sportapp.models;

public class Activity {

    int id;
    String name;
    String image;


    public Activity(int id, String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public int getId() {;
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String text) {
        this.name = text;
    }
}
