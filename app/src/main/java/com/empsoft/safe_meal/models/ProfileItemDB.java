package com.empsoft.safe_meal.models;

/**
 * Created by Rafaelle on 31/03/2017.
 */

import java.util.Random;

import java.util.Random;

public class ProfileItemDB {
    private int id;
    private String name;
    private DietDB diet;

    public ProfileItemDB(String name, DietDB diet) {
        this.name = name;
        this.diet = diet;
        final Random randomId = new Random();
        this.id = randomId.nextInt();
    }

    public ProfileItemDB(String name, DietDB diet, int id) {
        this.name = name;
        if (diet == null){
            this.diet = new DietDB(name);
        } else {
            this.diet = diet;
        }
        this.id = id;
    }
    public DietDB getDiet() {
        return diet;
    }

    public  Diet getDietR(){
        return new Diet(this.name, diet.getIntolerances(), diet.getDiets(), diet.getExcludeIngredients());
    }

    public String getName() {
        return name;
    }

    public void setDiet(DietDB diet) {
        this.diet = diet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(){
        final Random randomId = new Random();
        this.id = randomId.nextInt();
    }
}
