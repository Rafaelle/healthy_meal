package com.empsoft.safe_meal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projeto_les.easymeal.Globals;
import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;
import com.projeto_les.easymeal.services.retrofit_models.Step;

import java.util.ArrayList;
import java.util.Arrays;


public class RecipeStepsFragment extends Fragment {

    public static final String TAG = "STEPS_FRAGMENT";

    private RecyclerView mRecycleView;
    private View mview;
    private ListView mListView;
    private AnalyzedRecipeInstructions mAnalyzedRecipe;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeStepsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeStepsFragment getInstance() {
        RecipeStepsFragment fragment = new RecipeStepsFragment();
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

        mview = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        startAdapter();

        mListView = (ListView) mview.findViewById(R.id.recipe_steps_list);

        mAnalyzedRecipe = ((MainActivity) getActivity()).getGlobals().getAnalyzedRecipeInstructions();

        if (mAnalyzedRecipe != null) {

            ArrayList<String> steps = new ArrayList<>();
            for (Step s : mAnalyzedRecipe.getSteps()) {
                String[] stepsArray = s.getStep().split("\\.");

                steps = new ArrayList<String>(Arrays.asList(stepsArray));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                    android.R.layout.simple_list_item_1,
                    steps);
            mListView.setAdapter(adapter);
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
