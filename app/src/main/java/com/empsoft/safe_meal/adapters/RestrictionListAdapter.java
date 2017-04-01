package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.empsoft.safe_meal.models.FilterItem;
import com.empsoft.safe_meal.models.FilterRestrictionItem;

import java.util.ArrayList;
import java.util.List;

public class RestrictionListAdapter extends RecyclerView.Adapter<RestrictionListAdapter.FilterItemHolder>{

    private static final String TAG = "Intolerance_list_adapter ";
    private final List<FilterItem> items;
    private final List<String> selectedItems;
    private final Activity activity;
    private List<CheckBox> checkBoxItems;
    private List<String> filterNames;
    private FilterItemHolder holder;



    public RestrictionListAdapter(Activity activity, List<FilterItem> items) {
        this.items = items;
        selectedItems = new ArrayList<>();
        this.activity = activity;
        checkBoxItems = new ArrayList<>();
        filterNames = new ArrayList<>();
    }

    @Override
    public FilterItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterItemHolder(new FilterRestrictionItem(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final FilterItemHolder holder, final int position) {
        View currFilter = ((FilterRestrictionItem) holder.itemView);
        this.holder = holder;
        ((FilterRestrictionItem) holder.itemView).displayCbox(items.get(position).getIcon());
        ((FilterRestrictionItem) holder.itemView).displayText(items.get(position).getNome());


        final CheckBox checkboxItem = ((FilterRestrictionItem) holder.itemView).getCheckBoxItem();


        checkBoxItems.add(position, checkboxItem);


        Log.d("position", String.valueOf(position));

        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkboxItem.isChecked() && selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.remove(checkboxItem.getText().toString());
                } else if (checkboxItem.isChecked() && !selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.add(checkboxItem.getText().toString());
                }
            }
        });

        holder.cb.setChecked(selectedItems.contains(holder.cb.getText()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FilterItemHolder extends RecyclerView.ViewHolder {

        public CheckBox cb;

        public FilterItemHolder(View itemView) {
            super(itemView);
            cb = ((FilterRestrictionItem)itemView).getCheckBoxItem();
        }
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

}

