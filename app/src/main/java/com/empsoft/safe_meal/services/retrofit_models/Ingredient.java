package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michelly on 25/02/17.
 */

public class Ingredient {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    public Ingredient() {}

    public Ingredient(String id, String name, String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
