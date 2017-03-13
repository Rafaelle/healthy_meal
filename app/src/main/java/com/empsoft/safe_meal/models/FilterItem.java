package com.empsoft.safe_meal.models;


/**
 * Created by samirsmedeiros on 27/02/17.
 */

public class FilterItem {
    private String nome;
    private int icon;

    public FilterItem(String nome, int icon){
        this.nome = nome;
        this.icon = icon;


    }

    public int getIcon() {
        return icon;
    }

    public String getNome() {
        return nome;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
