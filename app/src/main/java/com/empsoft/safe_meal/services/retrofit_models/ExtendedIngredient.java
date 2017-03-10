package com.empsoft.safe_meal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by caynan on 17/02/17.
 */
public class ExtendedIngredient {

    @SerializedName("id")
    private Integer id;

    @SerializedName("aisle")
    private String aisle;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("amount")
    private Integer amount;

    @SerializedName("unit")
    private String unit;

    @SerializedName("unitShort")
    private String unitShort;

    @SerializedName("unitLong")
    private String unitLong;

    @SerializedName("originalString")
    private String originalString;

    @SerializedName("metaInformation")
    private List<String> metaInfortmation;

    //TODO: Never forget to include the default constructor when you overwrite it. Retrofit needs it.

    public ExtendedIngredient() {}

    public ExtendedIngredient(Integer id, String aisle, String image, String name, Integer amount, String unit, String unitShort, String unitLong, String originalString, List<String> metaInfortmation) {
        this.id = id;
        this.aisle = aisle;
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
        this.originalString = originalString;
        this.metaInfortmation = metaInfortmation;
    }

    public Integer getId() {
        return id;
    }

    public String getAisle() {
        return aisle;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public String getOriginalString() {
        return originalString;
    }

    public List<String> getMetaInfortmation() {
        return metaInfortmation;
    }

    @Override
    public String toString() {
        return "ExtendedIngredient{" +
                "id=" + id +
                ", aisle='" + aisle + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", unitShort='" + unitShort + '\'' +
                ", unitLong='" + unitLong + '\'' +
                ", originalString='" + originalString + '\'' +
                ", metaInfortmation=" + metaInfortmation +
                '}';
    }
}

