package com.one.app.demo.registration.registration.activities;

import android.os.Bundle;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;
import com.one.app.demo.registration.registration.controller.RegistrationController;
import com.one.app.demo.registration.registration.fragments.TermsAndConditionsFragment;

public class RegistrationActivity extends BaseActivity implements RegistrationController,
        TermsAndConditionsFragment.TermsAndConditionsFragmentView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration, null);
        hideFloatingButton();
        showTermsAndConditionsFragment();
    }

    @Override
    public void showTermsAndConditionsFragment() {
        TermsAndConditionsFragment fragment = TermsAndConditionsFragment.newInstance();
        initActionBar(R.string.fragment_tnc_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, TermsAndConditionsFragment.FRAGMENT_TAG, true, false);
    }
}
