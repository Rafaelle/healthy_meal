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
import com.empsoft.safe_meal.services.retrofit_models.Recipe;
import com.empsoft.safe_meal.services.retrofit_models.SpoonacularService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";
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
        String cuisine, String diet, String intolerances,
                Integer number, String query, String type*/

        ComplexSearchMapper complexSearchMapper = new ComplexSearchMapper();
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
                    //generalRecipes.add(new GeneralRecipe(recipe));
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
}
