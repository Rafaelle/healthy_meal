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
import com.empsoft.safe_meal.models.ProfileItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samirsmedeiros on 12/03/17.
 */


public class ProfileListAdapter extends ArrayAdapter<ProfileItem> {

    private static final String TAG = "profile_list_adapter ";
    private final List<ProfileItem> items;
    private final List<String> selectedItems;
    private final Activity activity;
    private List<CheckBox> checkBoxItems;
    private Button checkAllBtn;


    public ProfileListAdapter(Activity activity, List<ProfileItem> items, List<String> selectedItems) {
        super(activity, android.R.layout.simple_list_item_1,items );
        this.items = items;
        this.selectedItems = selectedItems;
        this.activity = activity;
        checkBoxItems = new ArrayList<>();
    }

    @Override
    public ProfileItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount(){
        return items.size();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final ProfileItem currProfile = items.get(position);

        if (v == null) {
            if(currProfile.getName().equals("ADD PROFILE")){
                LayoutInflater inflater = activity.getLayoutInflater();
                v = inflater.inflate(R.layout.profile_item_add, null);
            }else {
                LayoutInflater inflater = activity.getLayoutInflater();
                v = inflater.inflate(R.layout.profile_item, null);
            }
        }


        final CheckBox checkboxItem = (CheckBox) v.findViewById(R.id.checkbox_profile);
        checkboxItem.setText(currProfile.getName());

        checkBoxItems.add(checkboxItem);




        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!checkboxItem.isChecked() && selectedItems.contains(checkboxItem.getText().toString())){
                    selectedItems.remove(checkboxItem.getText().toString());

                }
                else if(checkboxItem.isChecked() && !selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.add(checkboxItem.getText().toString());

                }
            }
        });

        return v;
    }

    public List<String> getSelectedItems(){
        return selectedItems;
    }

    public void checkAll(){
        for (CheckBox cb : checkBoxItems){
            if (!cb.isChecked()) {
                cb.setChecked(true);
                if(!selectedItems.contains(cb.getText().toString())) selectedItems.add(cb.getText().toString());
            }

        }
    }
    public void uncheckAll(){
        for (CheckBox cb : checkBoxItems){
            if (cb.isChecked()) {
                cb.setChecked(false);
                if(selectedItems.contains(cb.getText().toString())) selectedItems.remove(cb.getText().toString());
            }

        }
    }

    public boolean allIschecked() {
        return selectedItems.size() == 11;
    }

    public ArrayList<ProfileItem> getSelectedItens(){
        ArrayList<ProfileItem> selected = new ArrayList<>();
        if (selectedItems != null){
            for (ProfileItem profile : items) {
                if (selectedItems.contains(profile.getName())){
                    selected.add(profile);
                }
            }
        }
        return selected;
    }

}
