package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;


public class ComplexSearchMapper {

    @SerializedName("addRecipeInformation")
    private boolean addRecipeInformation;

    @SerializedName("cuisine")
    private String cuisine;

    @SerializedName("diet")
    private String diet;

    @SerializedName("excludeIngredients")
    private String excludeIngredients;

    @SerializedName("fillIngredients")
    private boolean fillIngredients;

    @SerializedName("includeIngredients")
    private String includeIngredients;

    @SerializedName("instructionsRequired")
    private boolean instructionsRequired;

    @SerializedName("intolerances")
    private String intolerances;

    @SerializedName("limitLicense")
    private boolean limitLicense;

    @SerializedName("maxCalories")
    private Integer maxCalories;

    @SerializedName("maxCarbs")
    private Integer maxCarbs;

    @SerializedName("maxFat")
    private Integer maxFat;

    @SerializedName("maxProtein")
    private Integer maxProtein;

    @SerializedName("minCalories")
    private Integer minCalories;

    @SerializedName("minCarbs")
    private Integer minCarbs;

    @SerializedName("minFat")
    private Integer minFat;

    @SerializedName("minProtein")
    private Integer minProtein;

    @SerializedName("number")
    private Integer number;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("query")
    private String query;

    @SerializedName("ranking")
    private Integer ranking;

    @SerializedName("type")
    private String type;


    public ComplexSearchMapper() {
    }

    public ComplexSearchMapper(String cuisine, String diet, String includeIngredients, String intolerances,
                               Integer number, String query, Integer ranking, String type) {
        this.addRecipeInformation = false;
        this.cuisine = cuisine;
        this.diet = diet;
        this.excludeIngredients = null;
        this.fillIngredients = false;
        this.includeIngredients = includeIngredients;
        this.instructionsRequired = false;
        this.intolerances = intolerances;
        this.limitLicense = false;
        this.maxCalories = null;
        this.maxCarbs = null;
        this.maxFat = null;
        this.maxProtein = null;
        this.minCalories = null;
        this.minCarbs = null;
        this.minFat = null;
        this.minProtein = null;
        this.number = number;
        this.offset = 0;
        this.query = query;
        this.ranking = ranking;
        this.type = type;

    }


    public boolean getAddRecipeInformation() {
        return addRecipeInformation;
    }

    public void setAddRecipeInformation(boolean addRecipeInformation) {
        this.addRecipeInformation = addRecipeInformation;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getExcludeIngredients() {
        return excludeIngredients;
    }

    public void setExcludeIngredients(String excludeIngredients) {
        this.excludeIngredients = excludeIngredients;
    }

    public boolean isFillIngredients() {
        return fillIngredients;
    }

    public void setFillIngredients(boolean fillIngredients) {
        this.fillIngredients = fillIngredients;
    }

    public String getIncludeIngredients() {
        return includeIngredients;
    }

    public void setIncludeIngredients(String includeIngredients) {
        this.includeIngredients = includeIngredients;
    }

    public boolean isInstructionsRequired() {
        return instructionsRequired;
    }

    public void setInstructionsRequired(boolean instructionsRequired) {
        this.instructionsRequired = instructionsRequired;
    }

    public String getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(String intolerances) {

        this.intolerances = intolerances;
    }

    public boolean isLimitLicense() {
        return limitLicense;
    }

    public void setLimitLicense(boolean limitLicense) {
        this.limitLicense = limitLicense;
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(Integer maxFat) {
        this.maxFat = maxFat;
    }

    public Integer getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(Integer maxProtein) {
        this.maxProtein = maxProtein;
    }

    public Integer getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(Integer minCalories) {
        this.minCalories = minCalories;
    }

    public Integer getMinCarbs() {
        return minCarbs;
    }

    public void setMinCarbs(Integer minCarbs) {
        this.minCarbs = minCarbs;
    }

    public Integer getMinFat() {
        return minFat;
    }

    public void setMinFat(Integer minFat) {
        this.minFat = minFat;
    }

    public Integer getMinProtein() {
        return minProtein;
    }

    public void setMinProtein(Integer minProtein) {
        this.minProtein = minProtein;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "ComplexSearchMapper{" +
                "addRecipeInformation='" + addRecipeInformation + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", diet='" + diet + '\'' +
                ", excludeIngredients='" + excludeIngredients + '\'' +
                ", fillIngredients='" + fillIngredients + '\'' +
                ", includeIngredients='" + includeIngredients + '\'' +
                ", instructionsRequired=" + instructionsRequired +
                ", intolerances='" + intolerances + '\'' +
                ", limitLicense=" + limitLicense +
                ", maxCalories=" + maxCalories +
                ", maxCarbs=" + maxCarbs +
                ", maxFat=" + maxFat +
                ", maxProtein=" + maxProtein +
                ", minCalories=" + minCalories +
                ", minCarbs=" + minCarbs +
                ", minFat=" + minFat +
                ", minProtein='" + minProtein + '\'' +
                ", number='" + number + '\'' +
                ", offset=" + offset +
                ", query='" + query + '\'' +
                ", ranking='" + ranking + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
