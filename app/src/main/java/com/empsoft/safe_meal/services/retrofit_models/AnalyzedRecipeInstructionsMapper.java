package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by caynan on 17/02/17.
 */
public class AnalyzedRecipeInstructionsMapper {

    @SerializedName("id")
    private int id = 0;

    @SerializedName("stepBreakdown")
    private boolean stepBreakdown = false;

    public AnalyzedRecipeInstructionsMapper() {}

    public AnalyzedRecipeInstructionsMapper(int id, boolean stepBreakdown) {
        this.id = id;
        this.stepBreakdown = stepBreakdown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStepBreakdown() {
        return stepBreakdown;
    }

    public void setStepBreakdown(boolean stepBreakdown) {
        this.stepBreakdown = stepBreakdown;
    }

    @Override
    public String toString() {
        return "RecipeInformationMapper{" +
                "id=" + id +
                ", stepBreakdown=" + stepBreakdown +
                '}';
    }

}
