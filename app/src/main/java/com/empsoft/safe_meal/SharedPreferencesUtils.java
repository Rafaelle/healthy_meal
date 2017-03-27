package com.empsoft.safe_meal;

import android.content.Context;
import android.content.SharedPreferences;

import com.empsoft.safe_meal.models.Diet;
import com.empsoft.safe_meal.models.ProfileItem;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * Created by rafaelle on 27/03/17.
 */
public class SharedPreferencesUtils {

    protected SharedPreferencesUtils() {}

    public static Diet getDiet(Context context, ProfileItem profileItem) {
        SharedPreferences settings = context.getSharedPreferences(profileItem.getName(), Context.MODE_PRIVATE);
//        return settings.getBoolean(String.valueOf(idFilme), false);

        //Creating a shared preference

        Gson gson = new Gson();
        String json = settings.getString("Diet", "");
        Diet diet = gson.fromJson(json, Diet.class);
        return diet;
    }

    public static void setDiet(Context context, ProfileItem profileItem){
        SharedPreferences settings = context.getSharedPreferences(profileItem.getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        //editor.putBoolean(String.valueOf(idFilme), isFavorito);
        editor.commit();
    }

}


