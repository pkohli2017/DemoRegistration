package com.one.app.demo.registration.registration.activities;

import android.content.Intent;
import android.os.Bundle;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;
import com.one.app.demo.registration.registration.controller.RegistrationController;
import com.one.app.demo.registration.registration.fragments.CreatePasscodeFragment;
import com.one.app.demo.registration.registration.fragments.DeclarationFragment;
import com.one.app.demo.registration.registration.fragments.DocumentUploadFragment;
import com.one.app.demo.registration.registration.fragments.PersonalDetailsFragment;
import com.one.app.demo.registration.registration.fragments.TermsAndConditionsFragment;
import com.one.app.demo.registration.registration.fragments.YourAddressFragment;

public class RegistrationActivity extends BaseActivity implements RegistrationController,
        TermsAndConditionsFragment.TermsAndConditionsFragmentView, DocumentUploadFragment.DocumentUploadFragmentPresenterView, DeclarationFragment.DeclarationFragmentView, YourAddressFragment.YourAddressFragmentView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration, null);
        hideFloatingButton();
        showTermsAndConditionsFragment();
    }

    @Override
    public void showDeclarationFragment() {
        DeclarationFragment fragment = DeclarationFragment.newInstance();
        initActionBar(R.string.fragment_declaration, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, DeclarationFragment.FRAGMENT_TAG, false, false);
    }

    @Override
    public void showTermsAndConditionsFragment() {
        TermsAndConditionsFragment fragment = TermsAndConditionsFragment.newInstance();
        initActionBar(R.string.fragment_tnc_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, TermsAndConditionsFragment.FRAGMENT_TAG, false, false);
    }

    @Override
    public void showPersonalDetailsFragment() {
        PersonalDetailsFragment fragment = PersonalDetailsFragment.newInstance();
        initActionBar(R.string.fragment_personal_detail_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, PersonalDetailsFragment.FRAGMENT_TAG, false, false);
    }

    @Override
    public void showYourAddressFragment() {
        YourAddressFragment fragment = YourAddressFragment.newInstance();
        initActionBar(R.string.fragment_your_address_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, YourAddressFragment.FRAGMENT_TAG, false, false);
    }

    @Override
    public void showDocumentUploadFragment() {
        DocumentUploadFragment fragment = DocumentUploadFragment.newInstance();
        initActionBar(R.string.fragment_document_upload_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, DocumentUploadFragment.FRAGMENT_TAG, true, false);
    }

    @Override
    public void showCreatePasscodeFragment() {
        CreatePasscodeFragment fragment = CreatePasscodeFragment.newInstance();
        initActionBar(R.string.fragment_create_passcode_title, INVALID_HOME_ICON, false);
        showFragment(R.id.main_content_view, fragment, CreatePasscodeFragment.FRAGMENT_TAG, false, false);
    }

    @Override
    public void showLoginScreen() {
        Intent intent = getIntent(this, LoginActivity.class);
        startActivityAndFinish(intent);
    }
}
