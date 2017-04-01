package com.empsoft.safe_meal.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.empsoft.safe_meal.DB.DBUtils;
import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.adapters.FilterListAdapter;
import com.empsoft.safe_meal.adapters.ProfileListAdapter;
import com.empsoft.safe_meal.adapters.RestrictionListAdapter;
import com.empsoft.safe_meal.models.Diet;

import com.empsoft.safe_meal.DB.DatabaseConnector;

import com.empsoft.safe_meal.models.DietDB;
import com.empsoft.safe_meal.models.FilterItem;
import com.empsoft.safe_meal.models.ProfileItem;
import com.empsoft.safe_meal.models.ProfileItemDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfilesFragment extends Fragment {

    private static ProfilesFragment fragment;
    public static final String TAG = "PROFILES_FRAGMENT";
    private List<ProfileItem> mProfiles;
    private List<ProfileItem> mSelectedProfiles;

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

    private RecyclerView checkboxGridView;
    private ProfileListAdapter mAdapter;


    private List<String> selectedFilterIntoleranceList = new ArrayList<>();
    private List<String> selectedFilterDietList = new ArrayList<>();

    private EditText mName;
    private Diet mDiet;
    private DietDB dietDB;

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

        mAdapter = new ProfileListAdapter(getActivity(),((MainActivity)getActivity()).getProfiles(), mSelectedProfiles);

        checkboxGridView = (RecyclerView) view.findViewById(R.id.profile_grid_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        checkboxGridView.setLayoutManager(llm);
        checkboxGridView.setAdapter(mAdapter);

        final FloatingActionButton searchBtn = (FloatingActionButton) view.findViewById(R.id.search_fab);
        modifyActioonBar();
        searchBtn.setClickable(true);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setSelectedProfiles(mAdapter.getSelectedItens());
                Toast.makeText(getContext(), R.string.wait, Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).complexSearch();
                searchBtn.setClickable(false);
            }
        });
        final Button addUser = (Button) view.findViewById(R.id.add_user);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        filterType();
        filterCuisine();
    return view;

    }


    private void createProfile(final RecyclerView mGrid, final ProfileListAdapter mNAdapter) {

        List<String> filterIntoleranceListName = new ArrayList<>(Arrays.asList("Dairy", "Egg",
                "Gluten", "Peanut", "Sesame", "Seafood", "Shellfish", "Soy", "Sulfite", "Tree", "Nut", "Wheat"));

        List<String> filterDietListName = new ArrayList<>(Arrays.asList("Lacto Vegetarian",
                "Ovo Vegetarian", "Vegan", "Paleo", "Primal", "Vegetarian"));

        int[] filterIntoleranceListIcon = new int[]{R.drawable.ic_dairy, R.drawable.ic_egg,
                R.drawable.ic_gluten, R.drawable.ic_peanut, R.drawable.ic_sesame,
                R.drawable.ic_seafood, R.drawable.ic_shellfish, R.drawable.ic_soy, R.drawable.ic_sulfite,
                R.drawable.ic_tree, R.drawable.ic_nut, R.drawable.ic_wheat
        };

        int[] filterDietListIcon = new int[]{R.drawable.ic_lacto_vegetarian,
                R.drawable.ic_ovo_vegetarian, R.drawable.ic_vegan, R.drawable.ic_paleo,
                R.drawable.ic_primal, R.drawable.ic_vegetarian
        };

        List<FilterItem> filterDietList = addItens(filterDietListName, filterDietListIcon);
        List<FilterItem> filterIntoleranceList = addItens(filterIntoleranceListName, filterIntoleranceListIcon);

        final View mView = View.inflate(getActivity(), R.layout.fragment_create_profile, null);

        mName = (EditText) mView.findViewById(R.id.name_input);
        mName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {


                    // Perform action on key press
                    nameRestrictions(mName.getText().toString());

                    return true;
                }
                return false;
            }
        });

        final RestrictionListAdapter mDietAdapter = new RestrictionListAdapter(getActivity(), filterDietList, true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        final RecyclerView checkboxDietListView = (RecyclerView) mView.findViewById(R.id.filter_list_diet);
        checkboxDietListView.setLayoutManager(llm);
        checkboxDietListView.setAdapter(mDietAdapter);

        final RestrictionListAdapter mIntoleranceAdapter = new RestrictionListAdapter(getActivity(), filterIntoleranceList,false);
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
                        //Verifica se e nulo, vazio ou contém números
                        if (nameRestrictions(mName.getText().toString())){
                            selectedFilterDietList = mDietAdapter.getSelectedItems();
                            selectedFilterIntoleranceList = mIntoleranceAdapter.getSelectedItems();
                            mDiet = new Diet(mName.getText().toString(), ListToSet(selectedFilterIntoleranceList), ListToSet(selectedFilterDietList), null);
                            ProfileItem mProfile = new ProfileItem(mName.getText().toString(), mDiet);

                            dietDB = new DietDB(mName.getText().toString(), ListToSet(selectedFilterIntoleranceList), ListToSet(selectedFilterDietList), null);

                            ProfileItemDB profileItem = new ProfileItemDB(mName.getText().toString(), dietDB);

                            Log.d(TAG, "id"+ profileItem.getId());

                            if(DBUtils.addProfile(getContext(), profileItem)){
                                //((MainActivity) getActivity()).addProfile(mProfile);
                                //mGrid.setAdapter(mNAdapter);
                                ((MainActivity)getActivity()).setmProfilesDB(DBUtils.getAllProfiles(getContext()));
                                mGrid.setAdapter(mNAdapter);
                                Toast.makeText(getContext(), R.string.add_profile, Toast.LENGTH_SHORT).show();

                            } else{
                                Toast.makeText(getContext(), R.string.error_add_in_DB, Toast.LENGTH_SHORT).show();

                            }

/*

                            if (mName.getText().length() != 0){ // will only save if name at least is present
                                // none of AsyncTask's generic parameters are used
                                // nothing passed to doInBackground or returned from onPostExecute and no progress tracked
                                // why? a Save for ADD needs nothing passed and a Save for EDIT uses the class level rowID set in onCreate
                                AsyncTask<Object, Object, Object> saveProfileTask = new AsyncTask<Object, Object, Object>()
                                {
                                    @Override
                                    protected Object doInBackground(Object... params){
                                        saveProfile(); // save contact to the database NOT on main/GUI thread
                                        return null; 	//see notes immediately above
                                    }

                                }; // end AsyncTask

                                // save the contact to the database using a separate thread
                                saveProfileTask.execute((Object[]) null);
                            }
                            else{
                                // create a new AlertDialog Builder
                                //android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext()); // need fully qualified Activity ref since in an inner class

                                // set dialog title & message, and provide Button to dismiss
                                //builder.setTitle(R.string.errorTitle);
                                //builder.setMessage(R.string.errorMessage);
                                //builder.setPositiveButton(R.string.errorButton, null);
                                //builder.show(); // display the Dialog
                            } */
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.drawable.ic_add_user)
                .show();
    }


    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    // saves contact information to the database
    // no parameters required as ADD doesn't need them, and EDIT uses the class level rowID set in onCreate
    private void saveProfile(){ //NOT on main/GUI thread
        // get DatabaseConnector to interact with the SQLite database
        DatabaseConnector databaseConnector = new DatabaseConnector(getContext());

        // db open and close for insert (add) and update (edit) done in called methods in DatabaseConnector class
        if (getActivity().getIntent().getExtras() == null){  // this is an ADD (_id is an autoincrement field see DatabaseConnector class)
            databaseConnector.insertProfile(
                    mName.getText().toString(),
                    mDiet.dietToString(),
                    mDiet.intoleranceToString());
        }
        else{ 								// this is an EDIT (need to pass a primary key - rowID)
            databaseConnector.updateProfile((long) checkboxGridView.getAdapter().getItemCount(),
                    mName.getText().toString(),
                    mDiet.dietToString(),
                    mDiet.intoleranceToString());
        }

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
                        .setTitle("Select recipe type")
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
                        .setIcon(R.drawable.ic_preparing_blue)
                        .show();
            }
        });
    }




    @Override
    public void onResume() {
        modifyActioonBar();
        super.onResume();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.next_button) {
            createProfile(checkboxGridView, mAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.next_button_action_bar, menu);
    }

    /**
     * Inicia as definições da ActionBar para esse fragment
     */
    private void modifyActioonBar() {
        android.support.v7.app.ActionBar mActionbar = ((MainActivity) this.getActivity()).getSupportActionBar();
        mActionbar.setTitle(R.string.app_name);
        mActionbar.setElevation(2);
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

    private boolean nameRestrictions(String ingredient){
        ingredient = ingredient.trim();

        if (ingredient==null || ingredient.equals("")){
            Toast.makeText(getContext(), R.string.empty_name, Toast.LENGTH_SHORT).show();
            return false;
        } if (ingredient.matches(".*\\d.*")){
            Toast.makeText(getContext(), R.string.contain_number, Toast.LENGTH_SHORT).show();
            return false;
        }/* if (ingredient.matches(".*\\W.*") ){
            Toast.makeText(getContext(), R.string.contain_metacharacter, Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return  true;
    }



}
