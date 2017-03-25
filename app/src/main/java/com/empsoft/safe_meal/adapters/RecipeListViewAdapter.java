package com.empsoft.safe_meal.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.empsoft.safe_meal.DownloadImageTask;
import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.fragments.RecipeDetailsFragment;
import com.empsoft.safe_meal.models.GeneralRecipe;
import java.util.List;

public class RecipeListViewAdapter extends ArrayAdapter {
    public static final String TAG = "RECIPE_LIST_VIEW_ADAPTER";


    private List<GeneralRecipe> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<GeneralRecipe> items) {
        super(activity, android.R.layout.simple_list_item_1,items );

        this.items = items;
        this.activity = activity;
    }

    @Override
    public GeneralRecipe getItem(int position) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final GeneralRecipe generalRecipe = items.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_item_name);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipe_image);

        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.recipe_ll);

        if (generalRecipe.getImage() == null){
            DownloadImageTask downloadImageTask = new DownloadImageTask(recipeImage);
            downloadImageTask.execute(generalRecipe.getRecipe().getImage());
            ((MainActivity)activity).setImageGeneralRecipe(generalRecipe.getRecipe().getId(),downloadImageTask.getImage());

            //items.get(position).setImage(downloadImageTask.getImage());
            Log.d(TAG, "Download de imagem");

        } else {
            recipeImage.setImageBitmap(generalRecipe.getImage());
        }

        recipeName.setText(generalRecipe.getRecipe().getTitle());

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TO DO carregar as informações
                ((MainActivity) activity).changeFragment(RecipeDetailsFragment.getInstance(),RecipeDetailsFragment.TAG,true );
            }
        });

        return convertView;
    }

}
