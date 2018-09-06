package com.one.app.demo.registration.registration.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.registration.LoginPresentator.LoginPresenter;

public class LoginActivity extends AppCompatActivity
        implements LoginPresenter.LoginPresenterView, View.OnClickListener {
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

    public void showLoginError() {
        Toast.makeText(getApplicationContext(), getString(R.string.loginError), Toast.LENGTH_SHORT).show();
        clearPasscodeText();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String mPasscodeEditTextString = mPasscodeEditText.getText().toString();

        switch (id) {
            case R.id.txt_sign_in:
                if (mPasscodeEditTextString.isEmpty() || mPasscodeEditTextString.length() > 3) {
                    if (mLoginPresenter.checkPasscode(mPasscodeEditTextString))
                        mPasscodeEditText.setError(getString(R.string.login_successful));
                    else
                        showLoginError();
                } else
                    showEmptyError();
                break;
        }
    }

    private void showEmptyError() {
        Toast.makeText(getApplicationContext(), getString(R.string.passcode_empty_error), Toast.LENGTH_SHORT).show();
    }
}
