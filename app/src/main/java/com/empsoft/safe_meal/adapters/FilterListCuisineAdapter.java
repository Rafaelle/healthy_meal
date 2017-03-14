package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.models.FilterItem;

import java.util.ArrayList;
import java.util.List;

public class FilterListCuisineAdapter extends ArrayAdapter<FilterItem> {

    private static final String TAG = "Filter_list_cuisine_adapter ";
    private final List<FilterItem> items;
    private final List<String> selectedItems;
    private final Activity activity;
    private List<CheckBox> checkBoxItems;
    private Button checkAllBtn;


    public FilterListCuisineAdapter(Activity activity, List<FilterItem> items, List<String> selectedItems, Button checkAllBtn) {
        super(activity, android.R.layout.simple_list_item_1, items);
        this.items = items;
        this.selectedItems = selectedItems;
        this.activity = activity;
        this.checkAllBtn = checkAllBtn;
        checkBoxItems = new ArrayList<>();
    }

    @Override
    public FilterItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final FilterItem currFilter = items.get(position);

        if (v == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            v = inflater.inflate(R.layout.filter_list_item, null);
        }

        final CheckBox checkboxItem = (CheckBox) v.findViewById(R.id.checkbox);
        final ImageButton mImgBtn = (ImageButton) v.findViewById(R.id.icon_im);
        checkboxItem.setText(currFilter.getNome());
        mImgBtn.setImageResource(currFilter.getIcon());
        checkBoxItems.add(checkboxItem);


        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!checkboxItem.isChecked() && selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.remove(checkboxItem.getText().toString());
                    checkAllBtn.setText("check all");
                } else if (checkboxItem.isChecked() && !selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.add(checkboxItem.getText().toString());
                    if (allIschecked()) checkAllBtn.setText("uncheck all");
                }
            }
        });

        return v;
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void checkAll() {
        for (CheckBox cb : checkBoxItems) {
            if (!cb.isChecked()) {
                cb.setChecked(true);
                if (!selectedItems.contains(cb.getText().toString()))
                    selectedItems.add(cb.getText().toString());
            }

        }
    }

    public void uncheckAll() {
        for (CheckBox cb : checkBoxItems) {
            if (cb.isChecked()) {
                cb.setChecked(false);
                if (selectedItems.contains(cb.getText().toString()))
                    selectedItems.remove(cb.getText().toString());
            }

        }
    }

    public boolean allIschecked() {
        return selectedItems.size() == 24;
    }

}

