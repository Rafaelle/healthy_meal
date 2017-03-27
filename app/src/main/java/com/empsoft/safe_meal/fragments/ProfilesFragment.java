package com.empsoft.safe_meal.fragments;

import android.content.DialogInterface;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.adapters.FilterListAdapter;
import com.empsoft.safe_meal.adapters.ProfileListAdapter;
import com.empsoft.safe_meal.models.FilterItem;
import com.empsoft.safe_meal.models.ProfileItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfilesFragment extends Fragment {

    private static ProfilesFragment fragment;
    public static final String TAG = "PROFILES_FRAGMENT";
    private List<ProfileItem> mProfiles;
    private List<String> mSelectedProfiles;
    private Button mTypeBtn;
    private Button mCuisineBtn;

    private List<String> filterTypeListName;
    private List<String> filterCuisineListName;

    private List<FilterItem> filterTypeList;
    private List<FilterItem> filterCuisineList;

    private int[] filterTypeListIcon;
    private int[] filterCuisineListIcon;

    private List<String> selectedFilterTypeList;
    private List<String> selectedFilterCuisineList;

    public ProfilesFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static ProfilesFragment getInstance() {
        if (fragment == null ){
            fragment = new ProfilesFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profiles, container, false);

        mProfiles = new ArrayList<>(Arrays.asList(
                new ProfileItem("Samir", null),
                new ProfileItem("Martha", null),
                new ProfileItem("Rafaelle", null),
                new ProfileItem("Luiza", null),
                new ProfileItem("Igor", null),
                new ProfileItem("Khelvin", null),
                new ProfileItem("Maria", null),
                new ProfileItem("ADD PROFILE", null)));

        filterCuisineListName = new ArrayList<>(Arrays.asList( "African", "Chinese", "Japanese",
                  "Thai", "Indian", "British", "French", "Italian", "Mexican"
                , "American", "Greek", "Latin American"));

        filterTypeListName = new ArrayList<>(Arrays.asList( "Main Course", "Side Dish", "Dessert",
                "Appetizer", "Salad", "Breakfast", "Soup", "Beverage", "Sauce", "Drink"));

        filterTypeListIcon = new int []{R.drawable.ic_main_course, R.drawable.ic_side_dish,
                R.drawable.ic_dessert, R.drawable.ic_appetizer,
                R.drawable.ic_salad, R.drawable.ic_breakfast,
                R.drawable.ic_soup, R.drawable.ic_beverage, R.drawable.ic_sauce,
                R.drawable.ic_drink};

        filterCuisineListIcon = new int []{R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
        };

        filterTypeList = addItens(filterTypeListName, filterTypeListIcon);
        filterCuisineList = addItens(filterCuisineListName, filterCuisineListIcon);

        mSelectedProfiles = new ArrayList<>();
        mTypeBtn = (Button) view.findViewById(R.id.id_type);
        mCuisineBtn = (Button) view.findViewById(R.id.id_cuisine);

        selectedFilterTypeList = new ArrayList<>();
        selectedFilterCuisineList = new ArrayList<>();

        final ProfileListAdapter mAdapter = new ProfileListAdapter(getActivity(),mProfiles, mSelectedProfiles);

        final GridView checkboxGridView = (GridView) view.findViewById(R.id.profile_grid_view);
        checkboxGridView.setAdapter(mAdapter);

        final FloatingActionButton searchBtn = (FloatingActionButton) view.findViewById(R.id.user_settings_fab);
        modifyActioonBar();

        checkboxGridView.setAdapter(mAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setSelectedProfiles(mAdapter.getSelectedItens());
                Toast.makeText(getContext(), R.string.wait, Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).complexSearch();
            }
        });

        filterType();
        filterCuisine();
    return view;

    }


    private void filterType() {
        mTypeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View mView = View.inflate(getActivity(), R.layout.fragment_select_filters_list, null);
                final FilterListAdapter mAdapter= new FilterListAdapter(getActivity(), filterTypeList, selectedFilterTypeList);
                final GridView checkboxListView = (GridView) mView.findViewById(R.id.filter_list);
                checkboxListView.setAdapter(mAdapter);
                new AlertDialog.Builder(getContext())
                        .setTitle("Select recipe types")
                        .setView(mView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                selectedFilterTypeList = mAdapter.getSelectedItems();
                                ((MainActivity) getActivity()).setRecipeFiltersSelected(selectedFilterTypeList);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.ic_recipe_black)
                        .show();
            }
        });
    }

    private void filterCuisine() {
        mCuisineBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View mView = View.inflate(getActivity(), R.layout.fragment_select_filters_list, null);
                final FilterListAdapter mAdapter= new FilterListAdapter(getActivity(), filterCuisineList, selectedFilterCuisineList);
                final GridView checkboxListView = (GridView) mView.findViewById(R.id.filter_list);
                checkboxListView.setAdapter(mAdapter);
                new AlertDialog.Builder(getContext())
                        .setTitle("Select cuisines")
                        .setView(mView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                selectedFilterCuisineList = mAdapter.getSelectedItems();
                                ((MainActivity) getActivity()).setSelectedKitchenFilters(selectedFilterCuisineList);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.ic_cuisine_grey)
                        .show();
            }
        });
    }




    @Override
    public void onResume() {
        modifyActioonBar();
        super.onResume();
    }

    /**
     * Inicia as definições da ActionBar para esse fragment
     */
    private void modifyActioonBar() {
        android.support.v7.app.ActionBar mActionbar = ((MainActivity) this.getActivity()).getSupportActionBar();
        mActionbar.setTitle(R.string.app_name);
        mActionbar.setElevation(0);
        mActionbar.setDisplayHomeAsUpEnabled(false);//if true displays the left menu
    }

    private List<FilterItem> addItens(List<String> filterListName, int[] filterListIcon) {
        ArrayList<FilterItem> filters = new ArrayList<>();
        for (int i = 0; i < filterListName.size(); i++) {
            filters.add(new FilterItem(filterListName.get(i),filterListIcon[i]));
        }
        return filters;
    }

}
