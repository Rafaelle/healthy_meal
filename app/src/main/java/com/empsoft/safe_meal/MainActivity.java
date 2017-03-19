package com.empsoft.safe_meal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.empsoft.safe_meal.fragments.ProfilesFragment;
import com.empsoft.safe_meal.fragments.RecipeListFragment;
import com.empsoft.safe_meal.fragments.SelectFiltersFragment;
import com.empsoft.safe_meal.models.GeneralRecipe;
import com.empsoft.safe_meal.models.ProfileItem;
import com.empsoft.safe_meal.services.retrofit_models.ComplexSearchMapper;
import com.empsoft.safe_meal.services.retrofit_models.ComplexSearchResult;
import com.empsoft.safe_meal.services.retrofit_models.IngredientsMapper;
import com.empsoft.safe_meal.services.retrofit_models.Recipe;
import com.empsoft.safe_meal.services.retrofit_models.SpoonacularService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";
    private static final int numberResult = 5;
    private ProfilesFragment mProfilesFragment;
    private SelectFiltersFragment selectFiltersFragment;
    private SpoonacularService spoonacularService;
    private RecipeListFragment recipeListFragment;

    private List<ProfileItem> selectedProfiles;
    private List<String> recipeFiltersSelected;
    private List<String> selectedCuisineFilters;

    private List<GeneralRecipe> generalRecipes;
    private GeneralRecipe generalRecipeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProfilesFragment = ProfilesFragment.getInstance();
        selectFiltersFragment = SelectFiltersFragment.getInstance();
        recipeListFragment = RecipeListFragment.getInstance();

        selectedProfiles = new ArrayList<>();
        generalRecipes = new ArrayList<>();
        recipeFiltersSelected = new ArrayList<>();
        selectedCuisineFilters = new ArrayList<>();


        spoonacularService = new SpoonacularService(getString(R.string.SPOONACULATOR_API_KEY));
        changeFragment(mProfilesFragment, ProfilesFragment.TAG, true);
    }

    /**
     * Change the current displayed fragment by a new one.
     * - if the fragment is in backstack, it will pop it
     * - if the fragment is already displayed (trying to change the fragment with the same), it will not do anything
     *
     * @param frag            the new fragment to display
     * @param saveInBackstack if we want the fragment to be in backstack
     */
    public void changeFragment(Fragment frag, String tag, boolean saveInBackstack) {

        try {
            FragmentManager manager = getSupportFragmentManager();
            //fragment not in back stack, create it.
            FragmentTransaction transaction = manager.beginTransaction();


            transaction.replace(R.id.content_layout, frag, tag);

            if (saveInBackstack) {
                Log.d(TAG, "Change Fragment: addToBackTack " + tag);
                transaction.addToBackStack(tag);
            } else {
                Log.d(TAG, "Change Fragment: NO addToBackTack");
            }
            transaction.commit();
            // custom effect if fragment is already instanciated

        } catch (IllegalStateException exception) {
            Log.w(TAG, "Unable to commit fragment, could be activity as been killed in background. " + exception.toString());
        }
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }
        super.onBackPressed();
    }

    public void complexSearch(){
        /*
        String cuisine, String diet, String excludeIngredients,String intolerances,
                Integer number, String query, String type*/
        String query = "";
        ComplexSearchMapper complexSearchMapper = new ComplexSearchMapper(getCuisine(),getDiets(), getExcludeIngredients(),
                getIntolerances(), numberResult,query, getRecipeFilters());
        //complexSearchMapper.setLimitLicense(false);
        //complexSearchMapper.setNumber(5);

        spoonacularService.searchComplex(complexSearchMapper, new Callback<ComplexSearchResult>() {
            @Override
            public void onResponse(Call<ComplexSearchResult> call, Response<ComplexSearchResult> response) {

                ComplexSearchResult result = response.body();
                //Aqui é retornada uma lista com os objetos de receitas

                int i = 1;
                for(Recipe recipe : result.getResults()){
                    Log.d("COMPLEX_SEARCH-RECIPE "+i, recipe.toString());
                    i++;
                    generalRecipes.add(new GeneralRecipe(recipe));
                }
                changeFragment(RecipeListFragment.getInstance(), RecipeListFragment.TAG,true );
            }

            @Override
            public void onFailure(Call<ComplexSearchResult> call, Throwable t) {

            }
        });

    }


    public List<ProfileItem> getSelectedProfiles() {
        return selectedProfiles;
    }

    public void setSelectedProfiles(List<ProfileItem> selectedProfiles) {
        this.selectedProfiles = selectedProfiles;
    }

    public List<String> getSelectedCuisineFilters() {
        return selectedCuisineFilters;
    }

    public void setSelectedKitchenFilters(List<String> selectedCuisineFilters) {
        this.selectedCuisineFilters = selectedCuisineFilters;
    }

    public List<String> getRecipeFiltersSelected() {
        return recipeFiltersSelected;
    }

    public void setRecipeFiltersSelected(List<String> recipeFiltersSelected) {
        this.recipeFiltersSelected = recipeFiltersSelected;
    }

    public List<GeneralRecipe> getGeneralRecipes() {
        return generalRecipes;
    }

    private String getCuisine(){
        String filters = "";
        if (selectedCuisineFilters != null && selectedCuisineFilters.size() > 0){
            for ( String str : selectedCuisineFilters){
                filters += str + ",";
            }
            if (filters.endsWith(",")){
                filters =filters.substring(0, filters.length()-1);
            }

        } else {
            return null;
        }
        return filters;
    }

    private String getRecipeFilters(){
        String filters ="";

        if (recipeFiltersSelected != null && recipeFiltersSelected.size() > 0){
            for (String string : recipeFiltersSelected) {
                filters += string + ",";
            }
            if (filters.endsWith(",")){
                filters =filters.substring(0, filters.length()-1);
            }

        } else {
            return null;
        }

        return filters;
    }

    private String getDiets(){
        String diets = "";

        for (ProfileItem profileItem: selectedProfiles){
            if (!profileItem.getDiet().dietToString().equals("")){
                diets += profileItem.getDiet().dietToString() + ",";
            }
        }

        if (diets.equals("")){
            return null;
        } else {
            if (diets.endsWith(",")){
                diets = diets.substring(0, diets.length()-1);
            }
        }

        return diets;
    }

    private String getIntolerances(){
        String intolerances = "";

        for (ProfileItem profileItem: selectedProfiles){
            if (!profileItem.getDiet().intoleranceToString().equals("")){
                intolerances += profileItem.getDiet().intoleranceToString() + ",";
            }
        }

        if (intolerances.equals("")){
            return null;
        } else {
            if (intolerances.endsWith(",")){
                intolerances = intolerances.substring(0, intolerances.length()-1);
            }
        }

        return intolerances;
    }


    private String getExcludeIngredients(){
        String excludeIngredients = "";

        for (ProfileItem profileItem: selectedProfiles){
            if (!profileItem.getDiet().excludeIngredientsToString().equals("")){
                excludeIngredients += profileItem.getDiet().excludeIngredientsToString() + ",";
            }
        }

        if (excludeIngredients.equals("")){
            return null;
        } else {
            if (excludeIngredients.endsWith(",")){
                excludeIngredients = excludeIngredients.substring(0, excludeIngredients.length()-1);
            }
        }

        return excludeIngredients;
    }




}
