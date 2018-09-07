package com.one.app.demo.registration.base.controller;

public interface BaseController {
    void showProgressDialog();

    void hideProgressDialog();

    void hideActionBar();

    void showActionBar();

    void initActionBar(int titleResId, int homeIcon, boolean displayHomeIcon);
}
