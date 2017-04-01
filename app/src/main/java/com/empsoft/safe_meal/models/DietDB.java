package com.empsoft.safe_meal.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafaelle on 31/03/17.
 */

public class DietDB implements Parcelable {

    private String name;
    private Set<String> intolerances;
    private Set<String> diets;
    private Set<String> excludeIngredients;


    public DietDB( String name, Set<String> intolerances, Set<String> diets, Set<String> excludeIngredients) {
        this.name = name;
        if (intolerances ==null){
            this.intolerances = new HashSet<>();
        } else {
            this.intolerances = intolerances;
        }

        if (diets ==null){
            this.diets = new HashSet<>();
        } else {
            this.diets = diets;
        }
        if (excludeIngredients ==null){
            this.excludeIngredients = new HashSet<>();
        } else {
            this.excludeIngredients = excludeIngredients;
        }
    }

    public DietDB(String name){
        this.name = name;
        this.intolerances = new HashSet<>();
        this.diets = new HashSet<>();
        this.excludeIngredients = new HashSet<>();

    }


    protected DietDB(Parcel in) {
        List<String> intolerancesList = new ArrayList<>();
        List<String> dietList = new ArrayList<>();
        List<String> excludeIngredientsList = new ArrayList<>();

        name = in.readString();
        in.readStringList(intolerancesList);
        in.readStringList(dietList);
        in.readStringList(excludeIngredientsList);

        intolerances = new HashSet<String>(intolerancesList);
        diets = new HashSet<String>(dietList);
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

    public Set<String> getDiets() {
        return diets;
    }

    public void setDiets(Set<String> diets) {
        this.diets = diets;
    }

    public Set<String> getExcludeIngredients() {
        return excludeIngredients;
    }

    public void setExcludeIngredients(Set<String> excludeIngredients) {
        this.excludeIngredients = excludeIngredients;
    }

    public String dietToString(){
        String str = "";
        if (diets != null){
            for (String s : diets) {
                str += s + ",";
            }
            if (str.endsWith(",")){
                str =str.substring(0, str.length()-1);
            }
        }
        return str;
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
                ", diets=" + diets +
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
        for (String str : diets)
            intolerancesList.add(str);
        for (String str : excludeIngredients)
            intolerancesList.add(str);

        dest.writeString(name);
        dest.writeStringList(intolerancesList);
        dest.writeStringList(dietList);
        dest.writeStringList(excludeIngredientsList);
    }
}
