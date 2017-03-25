package com.empsoft.safe_meal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.empsoft.safe_meal.models.GeneralRecipe;
import com.empsoft.safe_meal.services.retrofit_models.RecipeInformation;
import com.empsoft.safe_meal.R;


public class RecipeNutritionFragment extends Fragment {

    public static final String TAG = "DESCRIPTION_FRAGMENT";
    private View mview;
    private TextView mTitle;
    private TextView mReadyInMinutes;
    private RecipeInformation mRecipe;
    private GeneralRecipe generalRecipe;


    public RecipeNutritionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeNutritionFragment getInstance() {
        RecipeNutritionFragment fragment = new RecipeNutritionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_recipe_nutrition, container, false);
        startAdapter();

        mTitle = (TextView) mview.findViewById(R.id.Title);
        mReadyInMinutes = (TextView) mview.findViewById(R.id.readyInMinutes);

       // mRecipe = ((MainActivity) getActivity()).getGlobals().getRecipeInformation();

//        generalRecipe = ((MainActivity) getActivity()).getGeneralRecipeSelected();

        if (generalRecipe != null){
            mTitle.setText(generalRecipe.getRecipe().getTitle());
        }
        if (mRecipe != null) {
            mReadyInMinutes.setText("Ready in " + String.valueOf(mRecipe.getReadyInMinutes()) + " minutes.");
        }

        return mview;
    }

    private void startAdapter() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
