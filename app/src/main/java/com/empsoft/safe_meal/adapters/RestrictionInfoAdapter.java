package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.models.Diet;
import com.empsoft.safe_meal.models.FilterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samirsmedeiros on 31/03/17.
 */
    public class RestrictionInfoAdapter extends ArrayAdapter<String> {

        private static final String TAG = "Filter_list_adapter ";
        private final List<String> items;
        private final Activity activity;


        public RestrictionInfoAdapter(Activity activity, List<String> items) {
            super(activity, android.R.layout.simple_list_item_1, items);
            this.items = items;
            this.activity = activity;
        }

        @Override
        public String getItem(int position) {

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
            final String currFilter = items.get(position);

            if (v == null) {
                LayoutInflater inflater = activity.getLayoutInflater();
                v = inflater.inflate(R.layout.user_restriction_item, null);
            }

            ImageButton intoleranceBtn = (ImageButton) v.findViewById(R.id.ib_intolerances);
            intoleranceBtn.setImageResource(Diet.getIconByName(currFilter));
            TextView intolelanceName = (TextView) v.findViewById(R.id.intolerance_name);
            intolelanceName.setText(currFilter);

            return v;
        }


    }

