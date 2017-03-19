package com.empsoft.safe_meal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.services.retrofit_models.Recipe;

import java.util.ArrayList;

public class RecipeListFragment extends Fragment {

    private static RecipeListFragment fragment;
    public static final String TAG = "LIST_RECIPES_FRAGMENT";

    public RecipeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static RecipeListFragment getInstance() {
        if (fragment == null) {
            fragment = new RecipeListFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        //TODO pegar receitas compatíveis com a pesquisa
        //final ArrayList<Recipe> recipeList = (ArrayList<Recipe>) ((MainActivity) getActivity()).getRecipes();


        return view;
    }
}
