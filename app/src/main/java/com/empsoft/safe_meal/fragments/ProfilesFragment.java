package com.empsoft.safe_meal.fragments;

import android.content.DialogInterface;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.adapters.ProfileListAdapter;
import com.empsoft.safe_meal.adapters.RestrictionListAdapter;
import com.empsoft.safe_meal.models.Diet;
import com.empsoft.safe_meal.models.FilterItem;
import com.empsoft.safe_meal.models.ProfileItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfilesFragment extends Fragment {

    private static ProfilesFragment fragment;
    public static final String TAG = "PROFILES_FRAGMENT";
    private List<ProfileItem> mProfiles;
    private List<String> mSelectedProfiles;


    private List<String> selectedFilterIntoleranceList = new ArrayList<>();
    private List<String> selectedFilterDietList = new ArrayList<>();

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
          //      new ProfileItem("ADD PROFILE", null),
                new ProfileItem("Samir", null),
                new ProfileItem("Martha", null),
                new ProfileItem("Rafaelle", null),
                new ProfileItem("Luiza", null),
                new ProfileItem("Igor", null),
                new ProfileItem("Khelvin", null),
                new ProfileItem("Maria", null)));

        mSelectedProfiles = new ArrayList<>();

        final ProfileListAdapter mAdapter = new ProfileListAdapter(getActivity(),mProfiles, mSelectedProfiles);

        final GridView checkboxGridView = (GridView) view.findViewById(R.id.profile_grid_view);
        checkboxGridView.setAdapter(mAdapter);

        final FloatingActionButton nextBtn = (FloatingActionButton) view.findViewById(R.id.user_settings_fab);
        modifyActioonBar();

        checkboxGridView.setAdapter(mAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setSelectedProfiles(mAdapter.getSelectedItens());
                ((MainActivity) getActivity()).changeFragment(SelectFiltersFragment.getInstance(),SelectFiltersFragment.TAG,true );
            }
        });
        final Button addUser = (Button) view.findViewById(R.id.add_user);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createProfile(checkboxGridView, mAdapter);
                //((MainActivity) getActivity()).changeFragment(RecipeListFragment.getInstance(),RecipeListFragment.TAG,true );
            }
        });
    return view;

    }


    private void createProfile(final GridView mGrid, final ProfileListAdapter mNAdapter){

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

        final View mView = View.inflate(getActivity(), R.layout.fragment_create_profile, null);

        final EditText mName =  (EditText) mView.findViewById(R.id.name_input);

        final RestrictionListAdapter mDietAdapter= new RestrictionListAdapter(getActivity(), filterDietList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        final RecyclerView checkboxDietListView = (RecyclerView) mView.findViewById(R.id.filter_list_diet);
        checkboxDietListView.setLayoutManager(llm);
        checkboxDietListView.setAdapter(mDietAdapter);

        final RestrictionListAdapter mIntoleranceAdapter= new RestrictionListAdapter(getActivity(), filterIntoleranceList);
        LinearLayoutManager llm2 = new LinearLayoutManager(getActivity());
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
                        mProfiles.add(mProfile);

                        mGrid.setAdapter(mNAdapter);




                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.drawable.ic_add_user)
                .show();
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
    private Set<String> ListToSet(List<String> list){
        Set<String> set = new HashSet<String>(list);

        for (String temp : set){
            System.out.println(temp);
        }
        return set;
    }


}
