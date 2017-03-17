package com.empsoft.safe_meal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.adapters.FilterListCuisineAdapter;
import com.empsoft.safe_meal.models.FilterItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SelectFiltersCuisineFragment extends Fragment {

    private static SelectFiltersCuisineFragment fragment;
    public static final String TAG = "SELECT_FILTERS_CUISINE_FRAGMENT";
    private List<FilterItem> filterCuisineList;
    private List<String> filterListName;
    private int[] filterListIcon;
    private List<String> selectedFilterCuisineList;

    public SelectFiltersCuisineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SelectFiltersCuisineFragment getInstance() {
        if (fragment == null ){
            fragment = new SelectFiltersCuisineFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_select_filters_cuisine_list, container, false);



      /*  filterListName = new ArrayList<>(Arrays.asList( "African", "Chinese",
                            "Japanese", "Korean", "Vietnamese",
                            "Thai", "Indian", "British",
                            "Irish", "French", "Italian",
                            "Mexican", "Spanish", "Middle Eastern",
                            "Jewish", "American", "Cajun",
                            "Southern", "Greek", "German",
                            "Nordic", "Eastern European", "Caribbean",
                            "Latin American")); */
        filterListName = new ArrayList<>(Arrays.asList( "African", "Chinese",
                "Japanese", "Korean", "Vietnamese",
                "Thai", "Indian", "British",
                "French", "Italian",
                "Mexican", "Spanish", "Middle Eastern",
                "American",
                "Southern", "Greek", "German",
                "Latin American"));

        filterListIcon = new int []{R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine};

        filterCuisineList = addItens(filterListName, filterListIcon);

        selectedFilterCuisineList = new ArrayList<>();

        final Button checkAllBtn = (Button) view.findViewById(R.id.select_all_filters);

        final FilterListCuisineAdapter mAdapter= new FilterListCuisineAdapter(getActivity(),filterCuisineList, selectedFilterCuisineList, checkAllBtn);

        final GridView checkboxListView = (GridView) view.findViewById(R.id.filter_list);
        checkboxListView.setAdapter(mAdapter);

        checkAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.allIschecked()){
                    mAdapter.uncheckAll();
                    checkAllBtn.setText("check all");
                }else {
                    mAdapter.checkAll();
                    checkAllBtn.setText("uncheck all");

                }
            }
        });

        final ImageButton searchBtn = (ImageButton) view.findViewById(R.id.search_recipes);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(SelectFiltersCuisineFragment.getInstance(),SelectFiltersCuisineFragment.TAG,true );
            }
        });




        checkAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.allIschecked()){
                    mAdapter.uncheckAll();
                    checkAllBtn.setText("check all");
                }else {
                    mAdapter.checkAll();
                    checkAllBtn.setText("uncheck all");

                }
            }
        });

        return view;

    }

    private List<FilterItem> addItens(List<String> filterListName, int[] filterListIcon) {
        ArrayList<FilterItem> filters = new ArrayList<>();
        for (int i = 0; i < filterListName.size(); i++) {
            filters.add(new FilterItem(filterListName.get(i),filterListIcon[i]));
        }
        return filters;
    }

    private String getStrigsFromList(List<String> selectedFilterList) {
        String str = "You have selected: ";
        for (String filter: selectedFilterList) {
            Log.d(TAG,filter);
            str = str + filter + ", ";
        }
        return str;
    }


}
