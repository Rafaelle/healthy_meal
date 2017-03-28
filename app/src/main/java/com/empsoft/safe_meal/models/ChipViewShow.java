package com.empsoft.safe_meal.models;

/**
 * Created by Luiza on 25/03/2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.empsoft.safe_meal.DownloadImageTask;
import com.empsoft.safe_meal.R;

public class ChipViewShow extends FrameLayout {

    public ChipViewShow(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.chip_view_show, this);
    }

    public void displayItem(String text) {
        ((TextView)findViewById(R.id.chipTextViewShow)).setText(text);
    }


    public void displayImg(String s) {
        new DownloadImageTask( ((ImageButton)findViewById(R.id.iv_ingredients))).execute(s);

    }




}