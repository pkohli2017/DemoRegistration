package com.one.app.demo.registration.registration.activities.presenter;

import com.one.app.demo.registration.base.activities.ActivityPresenter;

public class LoginPresenter extends ActivityPresenter {
    private static final int DUMMY_PASSCODE = 123456;
    private final LoginPresenterView mView;

    public LoginPresenter(LoginPresenter.LoginPresenterView view) {
        mView = view;
    }

    public static LoginPresenter newInstance(LoginPresenter.LoginPresenterView view) {
        return new LoginPresenter(view);
    }

    public boolean checkPasscode(int passcode) {
        if (passcode == DUMMY_PASSCODE)
            return true;
        return false;
    }

    public interface LoginPresenterView {

    }
}
