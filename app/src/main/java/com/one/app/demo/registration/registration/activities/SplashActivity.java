package com.one.app.demo.registration.registration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;

/**
 * Created by Pankaj Kohli on 3/28/2017.
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final long DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate()");
        setContentView(R.layout.activity_splash);
        setSplashTimer();
        showProgressDialog();
    }


    private void setSplashTimer() {
        // set the timer for the Splash Screen. Modify the DELAY variable to change the sleep timer.
        Log.i(TAG, "setSplashTimer()");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgressDialog();
                Intent intent = getIntent(SplashActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY);
    }
}
