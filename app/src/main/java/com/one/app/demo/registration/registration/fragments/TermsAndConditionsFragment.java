package com.one.app.demo.registration.registration.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;
import com.one.app.demo.registration.base.fragments.BaseFragment;
import com.one.app.demo.registration.registration.activities.RegistrationActivity;
import com.one.app.demo.registration.registration.controller.RegistrationController;

@SuppressLint("NewApi")
public class TermsAndConditionsFragment extends BaseFragment implements
        TermsAndConditionsFragmentPresenter.TermsAndConditionsFragmentPresenterView, View.OnScrollChangeListener {

    public static final String FRAGMENT_TAG = TermsAndConditionsFragment.class.getSimpleName();
    private FragmentActivity mActivity;
    private TermsAndConditionsFragmentView mTermsAndConditionsFragmentView;
    private TermsAndConditionsFragmentPresenter mTermsAndConditionsFragmentPresenter;
    private WebView mWebView;
    private MenuItem mNextButton;
    private RegistrationController mController;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TermsAndConditionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TermsAndConditionsFragment newInstance(TermsAndConditionsFragmentView view) {
        TermsAndConditionsFragment fragment = new TermsAndConditionsFragment();
        fragment.mTermsAndConditionsFragmentView = view;
        return fragment;
    }

    // TODO: Rename and change types and number of parameters
    public static TermsAndConditionsFragment newInstance() {
        TermsAndConditionsFragment fragment = new TermsAndConditionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mController = (RegistrationActivity) getController();
        mTermsAndConditionsFragmentPresenter = TermsAndConditionsFragmentPresenter.newInstance(this, mController);
        mTermsAndConditionsFragmentPresenter.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mNextButton = menu.findItem(R.id.next);
        mNextButton.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.next) {
            mController.showCreatePasscodeFragment();
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // change the layout with the REAL ONE
        View view = inflater.inflate(R.layout.fragment_terms_and_conditions, container, false);
        mWebView = view.findViewById(R.id.web_view);
        mTermsAndConditionsFragmentPresenter.onCreateView(inflater, container, savedInstanceState);
        mController.initActionBar(R.string.fragment_tnc_title, BaseActivity.INVALID_HOME_ICON, false);
        setListeners();
        loadTnC();
        return view;
    }

    @SuppressLint("NewApi")
    private void setListeners() {
        mWebView.setOnScrollChangeListener(this);
    }

    private void loadTnC() {
        mWebView.loadUrl("file:///android_asset/tnc.html");
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int height = (int) Math.floor(mWebView.getContentHeight() * mWebView.getScale());
        int webViewHeight = mWebView.getHeight();
        int cutoff = height - webViewHeight - 10; // Don't be too strict on the cutoff point
        if (scrollY >= cutoff) {
            mNextButton.setEnabled(true);
        }
    }

    public interface TermsAndConditionsFragmentView {

    }

}
