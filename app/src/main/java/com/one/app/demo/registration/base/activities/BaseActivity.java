package com.one.app.demo.registration.base.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.controller.BaseController;

/**
 * Created by Pankaj Kohli on 3/28/2017.
 */

public class BaseActivity extends AppCompatActivity implements BaseController {
    private static final String TAG = BaseActivity.class.getSimpleName();
    public static int INVALID_HOME_ICON = -1;
    private ProgressDialog mProgressDialog;
    private BaseActivityView mBaseActivityView;

    public static Intent getIntent(Context context, Class tag) {
        return new Intent(context, tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setContentView(int layoutResID, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = vi.inflate(layoutResID, parent);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content_view);
        layout.addView(contentView);
    }

    public void initActionBar(int titleResId, int homeIcon, boolean displayHomeIcon) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(titleResId);
            if (displayHomeIcon && homeIcon != INVALID_HOME_ICON) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(homeIcon);
            } else {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }
    }

    public void showFragment(int fragmentContainerId, Fragment fragment, String fragmentTag,
                             boolean addToBackStack, boolean withAnimation) {
        showFragment(fragmentContainerId, fragment, fragmentTag, addToBackStack, withAnimation,
                android.R.anim.fade_in,
                android.R.anim.fade_out, android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    private void showFragment(
            int fragmentContainerId, Fragment fragment, String fragmentTag, boolean addToBackStack,
            boolean withAnimation, int animEnter, int animExit, int animPopEnter, int animPopExit) {
        Fragment previous = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (previous != null) {
            ft = previous.getActivity().getSupportFragmentManager().beginTransaction();
        }
        if (withAnimation) {
            ft.setCustomAnimations(animEnter, animExit, animPopEnter, animPopExit);
        }
        ft.replace(fragmentContainerId, fragment, fragmentTag);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    public void startActivityAndFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home: {
            }
        }
        return true;
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void hideActionBar() {
        getSupportActionBar().hide();
    }

    @Override
    public void showActionBar() {
        getSupportActionBar().show();
    }


    public void hideFloatingButton() {
        findViewById(R.id.fab).setVisibility(View.GONE);
    }

    public void showFloatingButton() {
        findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }

    public void onCreateView(BaseActivityView baseActivityView) {
        mBaseActivityView = baseActivityView;
    }

    public interface BaseActivityView {

    }
}

