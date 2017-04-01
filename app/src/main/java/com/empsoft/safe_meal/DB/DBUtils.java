package com.empsoft.safe_meal.DB;

/**
 * Created by Rafaelle on 31/03/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PointF;

import com.empsoft.safe_meal.models.Diet;
import com.empsoft.safe_meal.models.DietDB;
import com.empsoft.safe_meal.models.ProfileItem;
import com.empsoft.safe_meal.models.ProfileItemDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DBUtils {
    public static SQLiteDatabase getReadableDatabase(Context context) {
        DBOpenHelper openHelper = new DBOpenHelper(context);
        return openHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        DBOpenHelper openHelper = new DBOpenHelper(context);
        return openHelper.getWritableDatabase();
    }

    public static boolean addProfile(Context context, ProfileItemDB profileItem){
        SQLiteDatabase db = getWritableDatabase(context);
        Table profileTable = DBOpenHelper.getProfileTable();
        boolean result = false;
        // inicia a transação no banco
        db.beginTransaction();
        try {
            if (getIdProfileByName(context, profileItem.getName()) != -1){

                if (addIntolerance(context,profileItem.getDiet().getIntolerances(), profileItem.getId()) &&
                addDiet(context, profileItem.getDiet().getDiets(), profileItem.getId())){

                    ContentValues values = new ContentValues();
                    values.put("id", profileItem.getId());
                    values.put("name", profileItem.getName());

                    db.insert(profileTable.getName(), null, values);
                    db.setTransactionSuccessful();
                    result = true;
                }
            }
        } finally {
            db.endTransaction();
        }
        db.close();
        return result;
    }

    private static boolean addIntolerance(Context context, Set<String> intolerances, int id) {
        SQLiteDatabase db = getWritableDatabase(context);
        Table dietTable = DBOpenHelper.getIntoleranceTable();
        boolean result = false;

        // inicia a transação no banco
        db.beginTransaction();
        try {
            HashSet<String> intolerancesTB = getDietById(context, id);
            for (String intolerance : intolerances) {
                if (!intolerancesTB.contains(intolerance)){
                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("diet", intolerance);
                    db.insert(dietTable.getName(), null, values);
                }

            }
            db.setTransactionSuccessful();
            result = true;

        } finally {
            db.endTransaction();
        }
        db.close();

        return result;
    }

    private static boolean addDiet(Context context, Set<String> diets, int id) {
        SQLiteDatabase db = getWritableDatabase(context);
        Table dietTable = DBOpenHelper.getDietTable();
        boolean result = false;

        // inicia a transação no banco
        db.beginTransaction();
        try {
            HashSet<String> dietsTB = getIntolerancesById(context, id);
            for (String diet : diets) {
                if (!dietsTB.contains(diet)){
                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("diet", diet);
                    db.insert(dietTable.getName(), null, values);
                }

            }
            db.setTransactionSuccessful();
            result = true;

        } finally {
            db.endTransaction();
        }
        db.close();

        return result;
    }

    private static int getIdProfileByName(Context context, String profileName) {
        SQLiteDatabase db = getReadableDatabase(context);

        String[] args = {profileName};

        Cursor c = db.rawQuery("SELECT id FROM profile WHERE name = ?", args);
        int idx = c.getColumnIndex("id");

        int id = -1;

        if (c != null){
            if (c.moveToFirst()){
                id = c.getInt(idx);
            }
        }

        c.close();
        db.close();
        return id;
    }

    private static String getNameProfileById(Context context, int id) {
        SQLiteDatabase db = getReadableDatabase(context);

        String[] args = {String.valueOf(id)};
        Cursor c = db.rawQuery("SELECT name FROM profile WHERE id = ?", args);
        int idx = c.getColumnIndex("name");

        String name = null;

        if (c != null){
            if (c.moveToFirst()){
                name = c.getString(idx);
            }
        }
        c.close();
        db.close();
        return name;
    }

    private static HashSet<String> getIntolerancesById(Context context, int id) {
        SQLiteDatabase db = getReadableDatabase(context);
        HashSet<String> intolerances = new HashSet<>();
        String[] args = {String.valueOf(id)};

        Cursor c = db.rawQuery("SELECT intolerance FROM intolerance WHERE id = ?", args);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String intolerance = c.getString(c.getColumnIndex("intolerance"));
            intolerances.add(intolerance);
            c.moveToNext();
        }

        c.close();
        db.close();
        return intolerances;
    }

    private static HashSet<String> getDietById(Context context, int id) {
        SQLiteDatabase db = getReadableDatabase(context);
        HashSet<String> diets = new HashSet<>();
        String[] args = {String.valueOf(id)};

        Cursor c = db.rawQuery("SELECT diet FROM diet WHERE id = ?", args);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String diet = c.getString(c.getColumnIndex("diet"));
            diets.add(diet);
            c.moveToNext();
        }

        c.close();
        db.close();
        return diets;
    }


    public static boolean updateProfile(Context context, ProfileItemDB profileItem){
        SQLiteDatabase db = getWritableDatabase(context);
        Table profileTable = DBOpenHelper.getProfileTable();
        boolean result = false;
        // inicia a transação no banco
        db.beginTransaction();
        try {
            if (getNameProfileById(context, profileItem.getId())!= null){

                if (updateIntolerances(context, (HashSet<String>) profileItem.getDiet().getIntolerances(), profileItem.getId()) &&
                        updateDiets(context, (HashSet<String>) profileItem.getDiet().getDiets(), profileItem.getId())){

                    ContentValues values = new ContentValues();
                    values.put("id", profileItem.getId());
                    values.put("name", profileItem.getName());

                    db.update(profileTable.getName(), values, "id = ?", new String[]{String.valueOf(profileItem.getId())});
                    db.setTransactionSuccessful();
                    result = true;
                }
            }
        } finally {
            db.endTransaction();
        }
        db.close();
        return result;
    }

    private static boolean updateIntolerances(Context context, HashSet<String> intolerances, int id){
        SQLiteDatabase db = getWritableDatabase(context);
        Table intolerancesTable = DBOpenHelper.getIntoleranceTable();
        boolean result = false;
        // inicia a transação no banco
        db.beginTransaction();
        try {
            HashSet<String> intolerancesList = getIntolerancesById(context, id);

            for (String intolerance : intolerancesList) {
                //caso a intolerância já existente no banco do perfil determinado não exista mais a atualização, deleta.
                if (!(intolerances.contains(intolerance))) {

                    db.delete(intolerancesTable.getName(),"intolerance = ? AND id = ?, ", new String[]{intolerance, String.valueOf(id)});
                }
            }
            for (String intolerance : intolerances) {
                //caso a intolerância não exista nas intolerâncias já existentes no banco do perfil, adiciona.
                if (!(intolerancesList.contains(intolerance))){
                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("intolerance", intolerance);

                    db.insert(intolerancesTable.getName(), null, values);
                    db.setTransactionSuccessful();
                    result = true;
                }


            }
        } finally {
            db.endTransaction();
        }
        db.close();
        return result;
    }

    private static boolean updateDiets(Context context, HashSet<String> diets, int id){
        SQLiteDatabase db = getWritableDatabase(context);
        Table dietsTable = DBOpenHelper.getDietTable();
        boolean result = false;
        // inicia a transação no banco
        db.beginTransaction();
        try {
            HashSet<String> dietsList = getDietById(context, id);

            for (String diet : dietsList) {
                //caso a intolerância já existente no banco do perfil determinado não exista mais a atualização, deleta.
                if (!(diets.contains(diet))) {

                    db.delete(dietsTable.getName(),"diet = ? AND id = ?, ", new String[]{diet, String.valueOf(id)});
                }
            }
            for (String diet : diets) {
                //caso a intolerância não exista nas intolerâncias já existentes no banco do perfil, adiciona.
                if (!(dietsList.contains(diet))){
                    ContentValues values = new ContentValues();
                    values.put("id", id);
                    values.put("diet", diet);

                    db.insert(dietsTable.getName(), null, values);
                    db.setTransactionSuccessful();
                    result = true;
                }


            }
        } finally {
            db.endTransaction();
        }
        db.close();
        return result;
    }

    //TODO
    public static boolean deleteProfile(Context context, ProfileItemDB profileItem){
        SQLiteDatabase db = getWritableDatabase(context);
        Table locationsTable = DBOpenHelper.getProfileTable();
        boolean result = false;

        return result;

    }

    public static ProfileItemDB getProfileById(Context context, int id){
        SQLiteDatabase db = getReadableDatabase(context);

        String[] args = {String.valueOf(id)};
        Cursor c = db.rawQuery("SELECT name FROM profile WHERE id = ?", args);

        String name = null;
        HashSet<String> intolerances = new HashSet<>();
        HashSet<String> diets = new HashSet<>();


        if (c != null){
            if (c.moveToFirst()){
                name = c.getString(c.getColumnIndex("name"));
                intolerances = getIntolerancesById(context,id);
                diets = getDietById(context,id);
            }
        }
        c.close();
        db.close();

        ProfileItemDB profileItem = new ProfileItemDB(name,new DietDB(name,intolerances,diets,null),id);
        return profileItem;
    }


    public static ArrayList<ProfileItemDB> getAllProfiles(Context context) {
        SQLiteDatabase db = getReadableDatabase(context);
        Table profileTable = DBOpenHelper.getProfileTable();
        ArrayList<ProfileItemDB> profiles = new ArrayList<>();

        String[] columns = {"id", "name"};
        Cursor c = db.query(true, profileTable.getName(), columns, null, null, null, null, null, null);

        int idIndex = c.getColumnIndex("id");
        int nameIndex = c.getColumnIndex("name");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            DietDB diet = new DietDB(c.getString(nameIndex), getIntolerancesById(context,c.getInt(idIndex)),
                        getDietById(context, c.getInt(idIndex)), null);

            ProfileItemDB prof = new ProfileItemDB(c.getString(nameIndex),diet,c.getInt(idIndex));
            profiles.add(prof);

            c.moveToNext();
        }

        c.close();
        db.close();

        return profiles;
    }
}