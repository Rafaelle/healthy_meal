package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelly on 25/02/17.
 */

public class Step {

    @SerializedName("number")
    private int number;

    @SerializedName("step")
    private String step;

    @SerializedName("ingredients")
    private List<Ingredient> ingredients;

    public Step() {}

    public Step(int number, String step, List<Ingredient> ingredients){
        this.number = number;
        this.step = step;
        this.ingredients = ingredients;
    }

    public int getNumber() {
        return number;
    }

    public String getStep() {
        return step;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return "Step{" +
                "number=" + number +
                ", step='" + step + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
