package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeInformation {

    @SerializedName("vegetarian")
    private boolean vegetarian = false;

    @SerializedName("vegan")
    private boolean vegan = false;

    @SerializedName("glutenFree")
    private boolean glutenFree = false;

    @SerializedName("dairyFree")
    private boolean dairyFree = false;

    @SerializedName("veryHealthy")
    private boolean veryHealthy = false;

    @SerializedName("cheap")
    private boolean cheap = false;

    @SerializedName("veryPopular")
    private boolean veryPopular = false;

    @SerializedName("sustainable")
    private boolean sustainable = false;

    @SerializedName("weightWatcherSmartPoints")
    private int weightWatcherSmartPoints = 0;

    @SerializedName("gaps")
    private String gaps = "";

    @SerializedName("lowFodmap")
    private boolean lowFodmap = false;

    @SerializedName("ketogenic")
    private boolean ketogenic = false;

    @SerializedName("whole30")
    private boolean whole30 = false;

    @SerializedName("servings")
    private int servings;

    @SerializedName("sourceUrl")
    private String sourceUrl;

    @SerializedName("spoonacularSourceUrl")
    private String spoonacularSourceUrl;

    @SerializedName("aggregateLikes")
    private int aggregateLikes;

    @SerializedName("creditText")
    private String creditText;

    @SerializedName("sourceName")
    private String sourceName;

    @SerializedName("extendedIngredients")
    private List<ExtendedIngredient> extendedIngredients;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("readyInMinutes")
    private Integer readyInMinutes;

    @SerializedName("image")
    private String image;

    @SerializedName("imageType")
    private String imageType;

    public RecipeInformation() {}

    public RecipeInformation(boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean cheap, boolean veryPopular, boolean sustainable, int weightWatcherSmartPoints, String gaps, boolean lowFodmap, boolean ketogenic, boolean whole30, int servings, String sourceUrl, String spoonacularSourceUrl, int aggregateLikes, String creditText, String sourceName, List<ExtendedIngredient> extendedIngredients, Integer id, String title, Integer readyInMinutes, String image, String imageType, String instructions) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.cheap = cheap;
        this.veryPopular = veryPopular;
        this.sustainable = sustainable;
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
        this.gaps = gaps;
        this.lowFodmap = lowFodmap;
        this.ketogenic = ketogenic;
        this.whole30 = whole30;
        this.servings = servings;
        this.sourceUrl = sourceUrl;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
        this.aggregateLikes = aggregateLikes;
        this.creditText = creditText;
        this.sourceName = sourceName;
        this.extendedIngredients = extendedIngredients;
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.image = image;
        this.imageType = imageType;
        this.instructions = instructions;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public String getGaps() {
        return gaps;
    }

    public boolean isLowFodmap() {
        return lowFodmap;
    }

    public boolean isKetogenic() {
        return ketogenic;
    }

    public boolean isWhole30() {
        return whole30;
    }

    public int getServings() {
        return servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public String getCreditText() {
        return creditText;
    }

    public String getSourceName() {
        return sourceName;
    }

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }

    public String getInstructions() {
        return instructions;
    }

    @SerializedName("instructions")
    private String instructions;



    @Override
    public String toString() {
        return "RecipeInformation{" +
                "vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", glutenFree=" + glutenFree +
                ", dairyFree=" + dairyFree +
                ", veryHealthy=" + veryHealthy +
                ", cheap=" + cheap +
                ", veryPopular=" + veryPopular +
                ", sustainable=" + sustainable +
                ", weightWatcherSmartPoints=" + weightWatcherSmartPoints +
                ", gaps='" + gaps + '\'' +
                ", lowFodmap=" + lowFodmap +
                ", ketogenic=" + ketogenic +
                ", whole30=" + whole30 +
                ", servings=" + servings +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", spoonacularSourceUrl='" + spoonacularSourceUrl + '\'' +
                ", aggregateLikes=" + aggregateLikes +
                ", creditText='" + creditText + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", extendedIngredients=" + extendedIngredients +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", readyInMinutes=" + readyInMinutes +
                ", image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }

    //
//
//            "gaps": "no",
//            "lowFodmap": false,
//            "ketogenic": false,
//            "whole30": false,
//            "servings": 10,
//            "sourceUrl": "http://www.epicurious.com/recipes/food/views/Char-Grilled-Beef-Tenderloin-with-Three-Herb-Chimichurri-235342",
//            "spoonacularSourceUrl": "https://spoonacular.com/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992",
//            "aggregateLikes": 0,
//            "creditText": "Epicurious",
//            "sourceName": "Epicurious",
//            "extendedIngredients": [
//                {
//                    "id": 1022009,
//                        "aisle": "Ethnic Foods",
//                        "image": "https://spoonacular.com/cdn/ingredients_100x100/chili-powder.jpg",
//                        "name": "ancho chile powder",
//                        "amount": 1.5,
//                        "unit": "teaspoons",
//                        "unitShort": "t",
//                        "unitLong": "teaspoons",
//                        "originalString": "1 1/2 teaspoons chipotle chile powder or ancho chile powder",
//                        "metaInformation": []
//                },
//                {...}
//            ],
//            "id": 156992,
//            "title": "Char-Grilled Beef Tenderloin with Three-Herb Chimichurri",
//            "readyInMinutes": 45,
//            "image": "https://spoonacular.com/recipeImages/char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992.jpg",
//            "imageType": "jpg",
//            "instructions": "PreparationFor spice rub:                                        Combine all ingredients in small bowl.                                                                            Do ahead: Can be made 2 days ahead. Store airtight at room temperature.                                    For chimichurri sauce:                                        Combine first 8 ingredients in blender; blend until almost smooth. Add 1/4 of parsley, 1/4 of cilantro, and 1/4 of mint; blend until incorporated. Add remaining herbs in 3 more additions, pureeing until almost smooth after each addition.                                                                            Do ahead: Can be made 3 hours ahead. Cover; chill.                                    For beef tenderloin:                                        Let beef stand at room temperature 1 hour.                                                                            Prepare barbecue (high heat). Pat beef dry with paper towels; brush with oil. Sprinkle all over with spice rub, using all of mixture (coating will be thick). Place beef on grill; sear 2 minutes on each side. Reduce heat to medium-high. Grill uncovered until instant-read thermometer inserted into thickest part of beef registers 130F for medium-rare, moving beef to cooler part of grill as needed to prevent burning, and turning occasionally, about 40 minutes. Transfer to platter; cover loosely with foil and let rest 15 minutes. Thinly slice beef crosswise. Serve with chimichurri sauce.                                                                            *Available at specialty foods stores and from tienda.com."

}
