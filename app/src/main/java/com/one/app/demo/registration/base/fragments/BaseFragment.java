package com.one.app.demo.registration.base.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.one.app.demo.registration.R;

public class BaseFragment extends Fragment {
    public static final String FRAGMENT_TAG = BaseFragment.class.getSimpleName();

    // TODO: Rename and change types and number of parameters
    public static BaseFragment newInstance() {
        BaseFragment fragment = new BaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    public FragmentActivity getController() {
        return getActivity();
    }
}

