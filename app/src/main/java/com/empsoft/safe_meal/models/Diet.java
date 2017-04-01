package com.empsoft.safe_meal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.empsoft.safe_meal.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by michelly on 10/03/17.
 */

public class Diet implements Parcelable {

    private String name;
    private Set<String> intolerances;
    private Set<String> diet;
    private Set<String> excludeIngredients;
    private int icon;


    public Diet( String name, Set<String> intolerances, Set<String> diet, Set<String> excludeIngredients) {
        this.name = name;
        this.intolerances = intolerances;
        this.icon = icon;
        this.diet = diet;
        this.excludeIngredients = excludeIngredients;
    }

    public Diet(String name){
        this.name = name;
        this.intolerances = new HashSet<>();
        this.diet = new HashSet<>();
        this.excludeIngredients = new HashSet<>();

    }

    public Diet(){
        this.intolerances = new HashSet<>();
        this.diet = new HashSet<>();
        this.excludeIngredients = new HashSet<>();
    }


    protected Diet(Parcel in) {
        List<String> intolerancesList = new ArrayList<>();
        List<String> dietList = new ArrayList<>();
        List<String> excludeIngredientsList = new ArrayList<>();

        name = in.readString();
        in.readStringList(intolerancesList);
        in.readStringList(dietList);
        in.readStringList(excludeIngredientsList);

        intolerances = new HashSet<String>(intolerancesList);
        diet = new HashSet<String>(dietList);
        excludeIngredients = new HashSet<String>(excludeIngredientsList);
    }

    public static final Creator<Diet> CREATOR = new Creator<Diet>() {
        @Override
        public Diet createFromParcel(Parcel in) {
            return new Diet(in);
        }

        @Override
        public Diet[] newArray(int size) {
            return new Diet[size];
        }
    };

    public Set<String> getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(Set<String> intolerances) {
        this.intolerances = intolerances;
    }

    public Set<String> getDiet() {
        return diet;
    }

    public void setDiet(Set<String> diet) {
        this.diet = diet;
    }

    public Set<String> getExcludeIngredients() {
        return excludeIngredients;
    }

    public void setExcludeIngredients(Set<String> excludeIngredients) {
        this.excludeIngredients = excludeIngredients;
    }

    public String dietToString(){
        String str = "";
        if (diet!= null){
            for (String s : diet) {
                str += s + ",";
            }
            if (str.endsWith(",")){
                str =str.substring(0, str.length()-1);
            }
        }
        return str;
    }

    public int getIcon() {
        return icon;
    }

    public String intoleranceToString(){
        String str = "";
        if (intolerances!=null){
            for (String s : intolerances) {
                str += s + ",";
            }
            if (str.endsWith(",")){
                str =str.substring(0, str.length()-1);
            }
        }
        return str;
    }

    public String excludeIngredientsToString(){
        String str = "";
        if (excludeIngredients != null){
            for (String s : excludeIngredients) {
                str += s + ",";
            }
            if (str.endsWith(",")){
                str =str.substring(0, str.length()-1);
            }
        }
        return str;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "intolerances=" + intolerances +
                ", diet=" + diet +
                ", exclude Ingredients=" + excludeIngredients +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        ArrayList<String> intolerancesList = new ArrayList<String>();
        ArrayList<String> dietList = new ArrayList<String>();
        ArrayList<String> excludeIngredientsList = new ArrayList<String>();

        for (String str : intolerances)
            intolerancesList.add(str);
        for (String str : diet)
            intolerancesList.add(str);
        for (String str : excludeIngredients)
            intolerancesList.add(str);

        dest.writeString(name);
        dest.writeStringList(intolerancesList);
        dest.writeStringList(dietList);
        dest.writeStringList(excludeIngredientsList);
    }

    public static int getIconByName(String iconName){
        switch (iconName){
            case "Dairy": return R.drawable.ic_dairy;
            case "Egg": return R.drawable.ic_egg;
            case "Gluten": return R.drawable.ic_gluten;
            case "Peanut": return R.drawable.ic_peanut;
            case "Sesame": return R.drawable.ic_sesame;
            case "Seafood": return R.drawable.ic_seafood;
            case "Soy": return R.drawable.ic_soy;
            case "Sulfite": return R.drawable.ic_sulfite;
            case "Shellfish": return R.drawable.ic_shellfish;
            case "Tree": return R.drawable.ic_tree;
            case "Nut": return R.drawable.ic_nut;
            case "Wheat": return R.drawable.ic_wheat;
            default: return  R.drawable.ic_diet;

        }
    }
}
