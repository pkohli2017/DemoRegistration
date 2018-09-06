package com.one.app.demo.registration.registration.LoginPresentator;

import com.one.app.demo.registration.base.activities.ActivityPresenter;

public class LoginPresenter extends ActivityPresenter {
    private final LoginPresenterView mView;


    public LoginPresenter(LoginPresenter.LoginPresenterView view) {
        mView = view;
    }

    public static LoginPresenter newInstance(LoginPresenter.LoginPresenterView view) {
        return new LoginPresenter(view);
    }

    public boolean checkPasscode(String passcode) {
        try {
            if (Integer.parseInt(passcode) == 123456)
                return true;
            else
                return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public interface LoginPresenterView {

    }
}
