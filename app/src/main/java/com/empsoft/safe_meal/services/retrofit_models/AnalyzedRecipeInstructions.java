package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelly on 25/02/17.
 */

public class AnalyzedRecipeInstructions {
    @SerializedName("name")
    private String name;

    @SerializedName("steps")
    private List<Step> steps;

    public AnalyzedRecipeInstructions() {}

    public AnalyzedRecipeInstructions(String name, List<Step> steps){
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "AnalyzedRecipeInstructions{" +
                "name='" + name + '\'' +
                ", steps=" + steps +
                '}';
    }
}
