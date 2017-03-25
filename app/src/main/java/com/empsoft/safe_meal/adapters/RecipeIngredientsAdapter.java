package com.empsoft.safe_meal.adapters;

/**
 * Created by samirsmedeiros on 25/03/17.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.empsoft.safe_meal.models.ChipViewShow;

import java.util.List;

/**
 * Created by samirsmedeiros on 06/03/17.
 */

public class RecipeIngredientsAdapter extends RecyclerView.Adapter {

    private List<String> items;

    public RecipeIngredientsAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChipViewHolder(new ChipViewShow(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        View mView =  ((ChipViewShow)holder.itemView);
        ((ChipViewShow)holder.itemView).displayItem(items.get(position));



    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ChipViewHolder extends RecyclerView.ViewHolder {

        public ChipViewHolder(View itemView) {
            super(itemView);
        }
    }
}



