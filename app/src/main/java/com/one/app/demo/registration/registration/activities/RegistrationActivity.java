package com.one.app.demo.registration.registration.activities;

import android.os.Bundle;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;
import com.one.app.demo.registration.registration.fragments.DocumentUploadFragment;
import com.one.app.demo.registration.registration.controller.RegistrationController;
import com.one.app.demo.registration.registration.fragments.TermsAndConditionsFragment;

public class RegistrationActivity extends BaseActivity implements RegistrationController,
        TermsAndConditionsFragment.TermsAndConditionsFragmentView, DocumentUploadFragment.DocumentUploadFragmentPresenterView {

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
        showFragment(R.id.main_content_view, fragment, TermsAndConditionsFragment.FRAGMENT_TAG, false, false);
    }

    @Override
    public void showDocumentUploadFragment() {
        DocumentUploadFragment fragment = DocumentUploadFragment.newInstance();
        initActionBar(R.string.fragment_document_upload_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, DocumentUploadFragment.FRAGMENT_TAG, true, false);
    }
}
