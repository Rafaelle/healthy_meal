package com.empsoft.safe_meal.models;

import java.util.Set;

/**
 * Created by michelly on 10/03/17.
 */

public class Diet {

    private String name;
    private Set<String> intolerances;
    private Set<String> diet;

    public Diet(String name, Set<String> intolerances, Set<String> diet) {
        this.name = name;
        this.intolerances = intolerances;
        this.diet = diet;
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

    @Override
    public String toString() {
        return "Diet{" +
                "name='" + name + '\'' +
                ", intolerances=" + intolerances +
                ", diet=" + diet +
                '}';
    }
}
