package com.empsoft.safe_meal.models;

/**
 * Created by samirsmedeiros on 12/03/17.
 */

public class ProfileItem {
    private String name;
    private Diet diet;

    public ProfileItem(String name, Diet diet) {
        this.name = name;
        this.diet = diet;
    }

    public Diet getDiet() {
        return diet;
    }

    public String getName() {
        return name;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public void setName(String name) {
        this.name = name;
    }
}
