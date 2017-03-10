package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caynan on 14/02/17.
 */

public class GroceryProducts {

    @SerializedName("originalString")
    private String originalString;

    @SerializedName("ingredientImage")
    private String ingredientImage;

    @SerializedName("metaInformation")
    private List<String> metaInformation;

    @SerializedName("products")
    List<Product> products;

    //TODO: Never forget to include the default constructor when you overwrite it. Retrofit needs it.
    public GroceryProducts() {}

    public GroceryProducts(String originalString, String ingredientImage,
                           List<String> metaInformation, List<Product> products) {

        this.originalString = originalString;
        this.ingredientImage = ingredientImage;
        this.metaInformation = metaInformation;
        this.products = products;
    }

    public String getOriginalString() {
        return originalString;
    }

    public void setOriginalString(String originalString) {
        this.originalString = originalString;
    }

    public String getIngredientImage() {
        return ingredientImage;
    }

    public void setIngredientImage(String ingredientImage) {
        this.ingredientImage = ingredientImage;
    }

    public List<String> getMetaInformation() {
        return metaInformation;
    }

    public void setMetaInformation(List<String> metaInformation) {
        this.metaInformation = metaInformation;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "GroceryProducts{" +
                "originalString='" + originalString + '\'' +
                ", ingredientImage='" + ingredientImage + '\'' +
                ", metaInformation='" + metaInformation + '\'' +
                ", products=" + products +
                '}';
    }
}
