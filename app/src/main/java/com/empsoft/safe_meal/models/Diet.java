package com.empsoft.safe_meal.models;

import java.util.Set;

/**
 * Created by michelly on 10/03/17.
 */

public class Diet {

    private String name;
    private Set<String> intolerances;
    private Set<String> diet;
    private Set<String> excludeIngredients;

    public Diet(String name, Set<String> intolerances, Set<String> diet, Set<String> excludeIngredients) {
        this.name = name;
        this.intolerances = intolerances;
        this.diet = diet;
        this.excludeIngredients = excludeIngredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
                "name='" + name + '\'' +
                ", intolerances=" + intolerances +
                ", diet=" + diet +
                ", exclude Ingredients=" + excludeIngredients +
                '}';
    }
}
