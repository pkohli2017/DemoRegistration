package com.one.app.demo.registration.registration.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.one.app.demo.registration.base.fragments.FragmentPresenter;
import com.one.app.demo.registration.registration.controller.RegistrationController;

/**
 * Created by MedhaKalamkar on 06-09-2018.
 */

public class YourAddressFragmentPresenter extends FragmentPresenter {
    private final YourAddressFragmentPresenter.YourAddressFragmentPresenterView mView;
    private final RegistrationController mController;
    private FragmentActivity mActivity;

    public YourAddressFragmentPresenter(YourAddressFragmentPresenter.YourAddressFragmentPresenterView view,
                                        RegistrationController controller) {
        mView = view;
        mController = controller;
    }

    public static YourAddressFragmentPresenter newInstance(YourAddressFragmentPresenter.YourAddressFragmentPresenterView view,
                                                           RegistrationController controller) {
        return new YourAddressFragmentPresenter(view, controller);
    }

    @Override
    protected void onAttach(Activity activity) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = getActivity();
    }

    @Override
    protected void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {

    }

    @Override
    protected void onPause() {

    }

    @Override
    protected void onDestroy() {

    }

    @Override
    protected FragmentActivity getActivity() {
        return mView.getActivity();
    }

    public interface YourAddressFragmentPresenterView {
        FragmentActivity getActivity();
    }
}
