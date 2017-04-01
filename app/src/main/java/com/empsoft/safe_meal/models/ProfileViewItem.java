package com.empsoft.safe_meal.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.empsoft.safe_meal.R;

/**
 * Created by samirsmedeiros on 27/03/17.
 */

public class ProfileViewItem extends FrameLayout {
    private String nome;
    private int icon;
    private boolean isChecked;

    public ProfileViewItem(Context context) {
        super(context);
        initializeView(context);
        isChecked = false;
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.profile_item, this);
    }

    public void displayName(String name) {
        ((TextView)findViewById(R.id.profile_name)).setText(name);

    }
    public void displayCbox(String text, boolean ischeked) {
        ((CheckBox)findViewById(R.id.checkbox_profile)).setText(text);
        setChecked(ischeked);

    }

    public void setChecked(boolean ischeked) {
        this.isChecked = ischeked;
    }

    public boolean isChecked() {
        return isChecked;
    }




    public CheckBox getCheckBoxItem(){
        return ((CheckBox)findViewById(R.id.checkbox_profile));
    }

    public TextView getTextViewItem() {
        return ((TextView)findViewById(R.id.profile_name));
    }

    public ImageButton getUserbtn() {
        return ((ImageButton)findViewById(R.id.icon_im));
    }
}