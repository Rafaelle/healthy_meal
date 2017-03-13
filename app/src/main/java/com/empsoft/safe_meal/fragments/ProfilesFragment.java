package com.empsoft.safe_meal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.empsoft.safe_meal.MainActivity;
import com.empsoft.safe_meal.R;
import com.empsoft.safe_meal.adapters.ProfileListAdapter;
import com.empsoft.safe_meal.models.ProfileItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfilesFragment extends Fragment {

    private static ProfilesFragment fragment;
    public static final String TAG = "PROFILES_FRAGMENT";
    private List<ProfileItem> mProfiles;
    private List<String> mSelectedProfiles;



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

        mProfiles = new ArrayList<>(Arrays.asList( new ProfileItem("Samir", null), new ProfileItem("Martha", null),
                new ProfileItem("Rafaelle", null),new ProfileItem("Luiza", null),new ProfileItem("Igor", null),new ProfileItem("Khelvin", null)));

        mSelectedProfiles = new ArrayList<>();

        ProfileListAdapter mAdapter = new ProfileListAdapter(getActivity(),mProfiles, mSelectedProfiles);

        final GridView checkboxGridView = (GridView) view.findViewById(R.id.profile_grid_view);
        checkboxGridView.setAdapter(mAdapter);

        modifyActioonBar();


    return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.next_button_action_bar, menu);
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
            ((MainActivity) this.getActivity()).changeFragment(SelectFiltersFragment.getInstance(), SelectFiltersFragment.TAG, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inicia as definições da ActionBar para esse fragment
     */
    private void modifyActioonBar() {
        android.support.v7.app.ActionBar mActionbar = ((MainActivity) this.getActivity()).getSupportActionBar();
        mActionbar.setTitle(R.string.app_name);
        mActionbar.setDisplayHomeAsUpEnabled(false);//if true displays the left menu
    }


}
