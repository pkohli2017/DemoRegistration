package com.one.app.demo.registration.registration.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.registration.activities.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity
        implements LoginPresenter.LoginPresenterView, View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginPresenter mLoginPresenter;
    private EditText mPasscodeEditText;
    private TextView mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = LoginPresenter.newInstance(this);
        prepareUi();
    }

    public void prepareUi() {
        mSignInButton = (TextView) findViewById(R.id.txt_sign_in);
        mPasscodeEditText = (EditText) findViewById(R.id.edit_passcode);
        mSignInButton.setOnClickListener(this);
    }

    public void clearPasscodeText() {
        mPasscodeEditText.setText("");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.txt_sign_in:
                String mPasscodeEditTextString = mPasscodeEditText.getText().toString();
                if (!TextUtils.isEmpty(mPasscodeEditTextString) && mPasscodeEditTextString.length() == 6) {
                    try {
                        int passcode = Integer.parseInt(mPasscodeEditTextString);
                        boolean isValidPasscode = mLoginPresenter.checkPasscode(passcode);
                        if (isValidPasscode) {
                            Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_SHORT).show();
                        } else {
                            showLoginError(R.string.loginError);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(this, getString(R.string.passcode_empty_error), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showLoginError(R.string.passcode_empty_error);
                }
                break;
        }
    }

    private void showLoginError(int stringId) {
        mPasscodeEditText.setError(getString(stringId));
        clearPasscodeText();
    }
}
