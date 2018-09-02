package com.one.app.demo.registration.registration.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.registration.controller.RegistrationController;

public class TermsAndConditionsFragment extends Fragment implements
        TermsAndConditionsFragmentPresenter.TermsAndConditionsFragmentPresenterView {

    public static final String FRAGMENT_TAG = TermsAndConditionsFragment.class.getSimpleName();
    private FragmentActivity mActivity;
    private TermsAndConditionsFragmentView mTermsAndConditionsFragmentView;
    private TermsAndConditionsFragmentPresenter mTermsAndConditionsFragmentPresenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TermsAndConditionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TermsAndConditionsFragment newInstance(TermsAndConditionsFragmentView view) {
        TermsAndConditionsFragment fragment = new TermsAndConditionsFragment();
        fragment.mTermsAndConditionsFragmentView = view;
        return fragment;
    }

    // TODO: Rename and change types and number of parameters
    public static TermsAndConditionsFragment newInstance() {
        TermsAndConditionsFragment fragment = new TermsAndConditionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mTermsAndConditionsFragmentPresenter = TermsAndConditionsFragmentPresenter.newInstance(this, getController());
        mTermsAndConditionsFragmentPresenter.onCreate(savedInstanceState);
    }

    private RegistrationController getController() {
        return (RegistrationController) getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // change the layout with the REAL ONE
        View view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false);
        mTermsAndConditionsFragmentPresenter.onCreateView(inflater, container, savedInstanceState);

        return view;
    }

    public interface TermsAndConditionsFragmentView {

    }

}
