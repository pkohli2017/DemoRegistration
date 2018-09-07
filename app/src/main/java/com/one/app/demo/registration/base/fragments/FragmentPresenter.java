package com.one.app.demo.registration.base.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.one.app.demo.registration.base.controller.BaseController;


/**
 * Presenter default implementation (MVP pattern), for use with Fragments.
 */
public abstract class FragmentPresenter {

    /**
     * @param view       view
     * @param controller controller
     */

    private FragmentPresenterView mFragmentPresenterView;

    protected abstract void onAttach(Activity activity);

    protected abstract void onCreate(
            Bundle savedInstanceState);

    protected abstract void onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState);

    protected abstract void onActivityCreated(Bundle savedInstanceState);

    protected void onViewStateRestored(Bundle savedInstanceState) {
    }

    protected abstract void onResume();

    protected abstract void onPause();

    protected abstract void onDestroy();

    protected abstract FragmentActivity getActivity();


    public void showProgressDialog(BaseController controller) {
        controller.showProgressDialog();
    }

    public void hideProgressDialog(BaseController controller) {
        controller.hideProgressDialog();
    }

    public void showActionBar(BaseController controller) {
        controller.showActionBar();
    }

    public void hideActionBar(BaseController controller) {
        controller.hideActionBar();
    }


    interface FragmentPresenterView {

    }

}
