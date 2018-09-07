package com.one.app.demo.registration.registration.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.one.app.demo.registration.base.controller.BaseController;
import com.one.app.demo.registration.base.fragments.FragmentPresenter;
import com.one.app.demo.registration.registration.controller.RegistrationController;

public class PersonalDetailsFragmentPresenter extends FragmentPresenter {

    private final PersonalDetailsFragmentPresenterView mView;
    private final RegistrationController mController;
    private FragmentActivity mActivity;


    private PersonalDetailsFragmentPresenter(PersonalDetailsFragmentPresenterView view,
                                             RegistrationController controller){
        mView = view;
        mController = controller;

    }

    public static PersonalDetailsFragmentPresenter newInstance(PersonalDetailsFragmentPresenterView view,
                                                               RegistrationController controller){
        return new PersonalDetailsFragmentPresenter(view, controller);

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
    public void showProgressDialog(BaseController controller) {
        super.showProgressDialog(controller);
    }

    @Override
    public void hideProgressDialog(BaseController controller) {
        super.hideProgressDialog(controller);
    }

    @Override
    public void showActionBar(BaseController controller) {
        super.showActionBar(controller);
    }

    @Override
    public void hideActionBar(BaseController controller) {
        super.hideActionBar(controller);
    }


    @Override
    protected FragmentActivity getActivity() {
        return mView.getActivity();

    }

    public interface PersonalDetailsFragmentPresenterView{
        FragmentActivity getActivity();

    }
}
