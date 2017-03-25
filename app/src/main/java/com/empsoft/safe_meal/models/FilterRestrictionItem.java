package com.empsoft.safe_meal.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.empsoft.safe_meal.R;


/**
 * Created by michelly on 25/03/17.
 */

public class FilterRestrictionItem extends FrameLayout{
    private String nome;
    private int icon;
    private boolean isChecked;

    public FilterRestrictionItem(Context context) {
        super(context);
        initializeView(context);
        isChecked = false;
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.filter_list_create_profile_item, this);
    }

    public void displayImg(int icon) {
        ((ImageView)findViewById(R.id.icon_im)).setImageResource(icon);

    }
    public void displayCbox(String text, boolean ischeked) {
        ((CheckBox)findViewById(R.id.checkbox)).setText(text);
        setChecked(ischeked);

    }

    public void setChecked(boolean ischeked) {
        this.isChecked = ischeked;
    }

    public boolean isChecked() {
        return isChecked;
    }


    public CheckBox getCheckBoxItem(){
        return ((CheckBox)findViewById(R.id.checkbox));
    }
}
