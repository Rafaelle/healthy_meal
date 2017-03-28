package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.models.Diet;
import com.empsoft.safe_meal.models.FilterRestrictionItem;
import com.empsoft.safe_meal.models.ProfileItem;
import com.empsoft.safe_meal.models.FilterItem;
import com.empsoft.safe_meal.models.ProfileViewItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by samirsmedeiros on 12/03/17.
 */


public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileItemHolder>{

    private static final String TAG = "profile_list_adapter ";
    private final List<ProfileItem> items;
    private final List<ProfileItem> selectedItems;
    private final Activity activity;
    private List<CheckBox> checkBoxItems;
    private Button checkAllBtn;



    public ProfileListAdapter(Activity activity, List<ProfileItem> items, List<ProfileItem> selectedItems) {
        this.items = items;
        this.selectedItems = selectedItems;
        this.activity = activity;
        checkBoxItems = new ArrayList<>();
    }
    @Override
    public ProfileItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfileItemHolder(new ProfileViewItem(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ProfileItemHolder holder,  final int position) {

        View currFilter = ((ProfileViewItem) holder.itemView);

        ((ProfileViewItem) holder.itemView).displayName(items.get(position).getName());
        ((ProfileViewItem) holder.itemView).displayCbox("SELECT", false);


        final CheckBox checkboxItem = ((ProfileViewItem) holder.itemView).getCheckBoxItem();


        if(!checkBoxItems.contains(checkboxItem))checkBoxItems.add(checkboxItem);


        Log.d("position", String.valueOf(items.size()));

        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checkboxItem.isChecked() && selectedItems.contains(items.get(position))) {
                    selectedItems.remove(items.get(position));
                } else if (checkboxItem.isChecked() && !selectedItems.contains(items.get(position))) {
                    selectedItems.add(items.get(position));
                }
            }
        });

        holder.cb.setChecked(selectedItems.contains(items.get(position)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<ProfileItem> getSelectedItens() {
        return selectedItems;
    }


    public static class ProfileItemHolder extends RecyclerView.ViewHolder {

        public CheckBox cb;
        public TextView tv;

        public ProfileItemHolder(View itemView) {
            super(itemView);
            cb = ((ProfileViewItem)itemView).getCheckBoxItem();
            tv = ((ProfileViewItem)itemView).getTextViewItem();
        }
    }



}




