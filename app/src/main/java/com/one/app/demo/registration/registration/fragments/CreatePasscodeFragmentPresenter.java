package com.one.app.demo.registration.registration.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.one.app.demo.registration.base.fragments.FragmentPresenter;
import com.one.app.demo.registration.registration.controller.RegistrationController;

public class CreatePasscodeFragmentPresenter extends FragmentPresenter {

    private final CreatePasscodeFragmentPresenter.CreatePasscodeFragmentPresenterView mView;
    private final RegistrationController mController;
    private FragmentActivity mActivity;

    /**
     * @param view       view
     * @param controller controller
     */
    private CreatePasscodeFragmentPresenter(CreatePasscodeFragmentPresenter.CreatePasscodeFragmentPresenterView view,
                                            RegistrationController controller) {
        mView = view;
        mController = controller;
    }


    /**
     * @param view       view
     * @param controller controller
     * @return instance
     */
    public static CreatePasscodeFragmentPresenter newInstance(CreatePasscodeFragmentPresenter.CreatePasscodeFragmentPresenterView view,
                                                                  RegistrationController controller) {
        return new CreatePasscodeFragmentPresenter(view, controller);
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

    public interface CreatePasscodeFragmentPresenterView {

        FragmentActivity getActivity();
    }
}
