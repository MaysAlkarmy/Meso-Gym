package com.example.sportapp.models;

import com.google.gson.annotations.SerializedName;

public class SubActivity {

    int id;
    String name;
    String image;
    String time;
    String Main_Activity;
    String describe_activity;

    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String message;

    public SubActivity() {

    }

    public SubActivity(int id, String name, String image, String time, String main_Activity, String describe_activity,
                       Boolean error, String message) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.time = time;
        Main_Activity = main_Activity;
        this.describe_activity= describe_activity;
        this.error= error;
        this.message= message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMain_Activity() {
        return Main_Activity;
    }

    public void setMain_Activity(String main_Activity) {
        Main_Activity = main_Activity;
    }

    public String getDescribe_activity() {
        return describe_activity;
    }

    public void setDescribe_activity(String describe_activity) {
        this.describe_activity = describe_activity;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
