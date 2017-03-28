package com.empsoft.safe_meal.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.empsoft.safe_meal.fragments.RecipeNutritionFragment;
import com.empsoft.safe_meal.fragments.RecipeIngredientsFragment;
import com.empsoft.safe_meal.fragments.RecipeStepsFragment;


/**
 * Classe que serve como FoundFeedCardAdapter da ViewPager do FeedFragment.
 * Este FoundFeedCardAdapter é o reponsável por fazera troca entre lista de objetos encontrados
 * e lista de objetos perdidos no feed inicial.
 */
public class RecipeSwipeAdapter extends FragmentPagerAdapter {

    static final int NUM_ITEMS = 3;

    public RecipeSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Retorna o Fragment da respectiva posição
     */
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0 : return RecipeIngredientsFragment.getInstance();
            case 1 : return RecipeStepsFragment.getInstance();
            case 2 : return RecipeNutritionFragment.getInstance();
        }
        return null;
    }

    /**
     * Retorna o número de Fragments que serão mostrados na ViewPager
     */
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    /**
     * Este método retorna o título da tab de acordo com a posição.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Ingredients";
            case 1 :
                return "Directions";

            case 2 :
                return "Nutrition";
        }
        return null;
    }

}
