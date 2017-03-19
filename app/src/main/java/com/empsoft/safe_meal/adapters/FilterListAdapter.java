package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.models.Filter;
import com.empsoft.safe_meal.models.FilterItem;
import com.empsoft.safe_meal.models.ProfileItem;

import java.util.ArrayList;
import java.util.List;


public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.FilterItemHolder> {

    private static final String TAG = "Filter_list_adapter ";
    private final List<Filter> items;
    private final List<String> selectedItems;
    private final Activity activity;
    private List<String> filterNames;
    private List<CheckBox> checkBoxItems;
    private FilterItemHolder holder;

    private Button checkAllBtn;

    public FilterListAdapter(Activity activity, List<Filter> items, Button checkAllBtn) {
        this.items = items;
        selectedItems = new ArrayList<>();
        this.activity = activity;
        this.checkAllBtn = checkAllBtn;
        checkBoxItems = new ArrayList<>();
        filterNames = new ArrayList<>();

    }


    @Override
    public FilterItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterItemHolder(new FilterItem(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final FilterItemHolder holder, final int position) {
        View currFilter = ((FilterItem) holder.itemView);
        this.holder = holder;
        ((FilterItem) holder.itemView).displayImg(items.get(position).getIcon());
        ((FilterItem) holder.itemView).displayCbox(items.get(position).getNome(), false);


        final CheckBox checkboxItem = ((FilterItem) holder.itemView).getCheckBoxItem();



        checkBoxItems.add(position, checkboxItem);


        Log.d("position", String.valueOf(position));

        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkboxItem.isChecked() && selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.remove(checkboxItem.getText().toString());
                    checkAllBtn.setText("check all");
                } else if (checkboxItem.isChecked() && !selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.add(checkboxItem.getText().toString());
                    if (allIschecked()) {
                        checkAllBtn.setText("uncheck all");
                    }
                }
            }
        });

        holder.cb.setChecked(selectedItems.contains(holder.cb.getText()));

    }

    private void setChecked() {
        for (CheckBox cb: checkBoxItems){
            for (String name: selectedItems) {
                if (cb.getText().equals(name))
                    cb.setChecked(true);
                else
                    cb.setChecked(false);
            }

        }


    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FilterItemHolder extends RecyclerView.ViewHolder {

        public CheckBox cb;

        public FilterItemHolder(View itemView) {
            super(itemView);
            cb = ((FilterItem)itemView).getCheckBoxItem();
        }
    }


    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void checkAll() {
        for (Filter filter : items) {
            if(!selectedItems.contains(filter.getNome())) {
                selectedItems.add(filter.getNome());
                holder.cb.setChecked(selectedItems.contains(holder.cb.getText()));
            }
        }
    }

    public void uncheckAll() {
        if(allIschecked()) {
            for (Filter filter : items) {
                if (selectedItems.contains(filter.getNome()))
                    selectedItems.remove(filter.getNome());
            }
        }
    }

    public boolean allIschecked() {
        return selectedItems.size() == items.size();
    }
}


