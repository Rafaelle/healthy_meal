package com.empsoft.safe_meal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.adapters.RecipeListViewAdapter;
import com.empsoft.safe_meal.models.GeneralRecipe;
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
        final ArrayList<GeneralRecipe> recipeList = (ArrayList<GeneralRecipe>) ((MainActivity) getActivity()).getGeneralRecipes();

        ListView recipesListView = (ListView) view.findViewById(R.id.list_result);

        if(recipeList==null || recipeList.isEmpty()){
            view.findViewById(R.id.no_result).setVisibility(View.VISIBLE);
            view.findViewById(R.id.recipes_list).setVisibility(View.GONE);
        }
        else {
            view.findViewById(R.id.no_result).setVisibility(View.GONE);
            view.findViewById(R.id.recipes_list).setVisibility(View.VISIBLE);

            recipesListView.setAdapter(new RecipeListViewAdapter(getActivity(), recipeList));
            ListUtils.setDynamicHeight(recipesListView);

        }

        return view;
    }

    // Para adaptar o scroll view ao tamanho da lista de receitas
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter listAdapter = mListView.getAdapter();
            if (listAdapter != null) {

                int numberOfItems = listAdapter.getCount();

                // Get total height of all items.
                int totalItemsHeight = 0;
                for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                    View item = listAdapter.getView(itemPos, null, mListView);

                    float px = 500 * (mListView.getResources().getDisplayMetrics().density);
                    item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

                    totalItemsHeight += item.getMeasuredHeight();
                }

                // Get total height of all item dividers.
                int totalDividersHeight = mListView.getDividerHeight() *
                        (numberOfItems - 1);
                // Get padding
                int totalPadding = mListView.getPaddingTop() + mListView.getPaddingBottom();

                // Set list height.
                ViewGroup.LayoutParams params = mListView.getLayoutParams();
                params.height = totalItemsHeight + totalDividersHeight + totalPadding + 50;
                mListView.setLayoutParams(params);
                mListView.requestLayout();
            }
        }
    }

}
