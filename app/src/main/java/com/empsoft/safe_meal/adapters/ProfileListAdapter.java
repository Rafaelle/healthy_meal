package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import java.util.HashSet;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.models.Diet;
import com.empsoft.safe_meal.models.ProfileItem;
import com.empsoft.safe_meal.models.FilterItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    private List<String> selectedFilterIntoleranceList = new ArrayList<>();
    private List<String> selectedFilterDietList = new ArrayList<>();


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

                if(currProfile.getName().equals("ADD PROFILE")){
                    createProfile();
                }
            }
        });

        return v;
    }

    private void createProfile(){

        List<String> filterIntoleranceListName = new ArrayList<>(Arrays.asList("Dairy", "Egg",
                "Gluten", "Peanut", "Sesame", "Seafood", "Shellfish", "Soy", "Sulfite", "Tree", "Nut", "Wheat"));

        List<String> filterDietListName = new ArrayList<>(Arrays.asList( "Pescetarian", "Lacto Vegetarian",
                "Ovo Vegetarian", "Vegan", "Paleo", "Primal", "Vegetarian"));

        int[] filterIntoleranceListIcon = new int []{R.drawable.ic_dairy, R.drawable.ic_egg,
                R.drawable.ic_gluten, R.drawable.ic_peanut, R.drawable.ic_sesame,
                R.drawable.ic_seafood, R.drawable.ic_shellfish, R.drawable.ic_soy, R.drawable.ic_sulfite,
                R.drawable.ic_tree, R.drawable.ic_nut, R.drawable.ic_wheat
        };

        int[] filterDietListIcon = new int []{R.drawable.ic_diet, R.drawable.ic_diet,
                R.drawable.ic_diet, R.drawable.ic_diet, R.drawable.ic_diet,
                R.drawable.ic_diet, R.drawable.ic_diet,
        };

        List<FilterItem> filterDietList = addItens(filterDietListName, filterDietListIcon);
        List<FilterItem> filterIntoleranceList = addItens(filterIntoleranceListName, filterIntoleranceListIcon);

        View mView = View.inflate(activity, R.layout.fragment_create_profile, null);

        final EditText mName =  (EditText) mView.findViewById(R.id.name_input);

        final RestrictionListAdapter mDietAdapter= new RestrictionListAdapter(activity, filterDietList);
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        final RecyclerView checkboxDietListView = (RecyclerView) mView.findViewById(R.id.filter_list_diet);
        checkboxDietListView.setLayoutManager(llm);
        checkboxDietListView.setAdapter(mDietAdapter);

        final RestrictionListAdapter mIntoleranceAdapter= new RestrictionListAdapter(activity, filterIntoleranceList);
        LinearLayoutManager llm2 = new LinearLayoutManager(activity);
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);

        final RecyclerView checkboxIntoleranceListView = (RecyclerView) mView.findViewById(R.id.filter_list_intolerance);
        checkboxIntoleranceListView.setLayoutManager(llm2);
        checkboxIntoleranceListView.setAdapter(mIntoleranceAdapter);

        new AlertDialog.Builder(getContext())
                .setTitle("Create profile")
                .setView(mView)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFilterDietList = mDietAdapter.getSelectedItems();
                        selectedFilterIntoleranceList = mIntoleranceAdapter.getSelectedItems();
                        Diet mDiet = new Diet(mName.getText().toString(),ListToSet(selectedFilterDietList), ListToSet(selectedFilterIntoleranceList), null);
                        ProfileItem mProfile = new ProfileItem(mName.getText().toString(), mDiet);
                        items.add(mProfile);

                        notifyDataSetChanged();




                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.drawable.ic_add_user)
                .show();
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

    private List<FilterItem> addItens(List<String> filterListName, int[] filterListIcon) {
        ArrayList<FilterItem> filters = new ArrayList<>();
        for (int i = 0; i < filterListName.size(); i++) {
            filters.add(new FilterItem(filterListName.get(i),filterListIcon[i]));
        }
        return filters;
    }

    private Set<String> ListToSet(List<String> list){
        Set<String> set = new HashSet<String>(list);

        for (String temp : set){
            System.out.println(temp);
        }
        return set;
    }

}
