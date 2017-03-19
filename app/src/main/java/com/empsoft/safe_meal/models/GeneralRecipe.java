package com.empsoft.safe_meal.models;

import android.graphics.Bitmap;

import com.empsoft.safe_meal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.empsoft.safe_meal.services.retrofit_models.Recipe;
import com.empsoft.safe_meal.services.retrofit_models.RecipeInformation;

public class GeneralRecipe {
    Recipe recipe;
    RecipeInformation information;
    AnalyzedRecipeInstructions analyzedRecipeInstructions;
    Bitmap image;

    public GeneralRecipe(Recipe recipe, Bitmap image) {
        this.recipe = recipe;
        this.image = image;
        information = null;
        analyzedRecipeInstructions = null;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public RecipeInformation getInformation() {
        return information;
    }

    public void setInformation(RecipeInformation information) {
        this.information = information;
    }

    public AnalyzedRecipeInstructions getAnalyzedRecipeInstructions() {
        return analyzedRecipeInstructions;
    }

    public void setAnalyzedRecipeInstructions(AnalyzedRecipeInstructions analyzedRecipeInstructions) {
        this.analyzedRecipeInstructions = analyzedRecipeInstructions;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
