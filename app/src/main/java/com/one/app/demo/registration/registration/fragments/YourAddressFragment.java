package com.one.app.demo.registration.registration.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.registration.controller.RegistrationController;

public class YourAddressFragment extends Fragment implements
        YourAddressFragmentPresenter.YourAddressFragmentPresenterView, View.OnFocusChangeListener {

    public static final String FRAGMENT_TAG = YourAddressFragment.class.getSimpleName();
    private FragmentActivity mActivity;

    private YourAddressFragmentPresenter mYourAddressFragmentPresenter;
    private YourAddressFragmentView mYourAddressFragmentView;
    private MenuItem mNextButton;
    private RegistrationController mController;
    private EditText mCountryEditText;
    private EditText mStateEditText;
    private EditText mCityEditText;
    private EditText mAddressLine1EditText;
    private EditText mAddressLine2EditText;
    private EditText mAddressLine3EditText;
    private EditText mPostlCodeEditText;
    private final String mRegexStr = ".*\\d+.*";
    private final String mRegexName = "[a-zA-Z]+";

    public YourAddressFragment() {
        // Required empty public constructor
    }

    public static YourAddressFragment newInstance(YourAddressFragment.YourAddressFragmentView view) {
        YourAddressFragment fragment = new YourAddressFragment();
        fragment.mYourAddressFragmentView = view;
        return fragment;
    }

    public static YourAddressFragment newInstance() {
        return new YourAddressFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mController = getController();
        mYourAddressFragmentPresenter = YourAddressFragmentPresenter.newInstance(this, mController);
        mYourAddressFragmentPresenter.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_address, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mAddressLine1EditText = (EditText) view.findViewById(R.id.edt_address_line1);
        mAddressLine2EditText = (EditText) view.findViewById(R.id.edt_address_line2);
        mAddressLine3EditText = (EditText) view.findViewById(R.id.edt_address_line3);
        mCountryEditText = (EditText) view.findViewById(R.id.edt_country);
        mStateEditText = (EditText) view.findViewById(R.id.edt_state);
        mCityEditText = (EditText) view.findViewById(R.id.edt_city);
        mPostlCodeEditText = (EditText) view.findViewById(R.id.edt_postal_code);

        setOnFocusChangeListener();
    }

    private void setOnFocusChangeListener() {
        mAddressLine1EditText.setOnFocusChangeListener(this);
        mCityEditText.setOnFocusChangeListener(this);
        mStateEditText.setOnFocusChangeListener(this);
        mCountryEditText.setOnFocusChangeListener(this);
    }

    private RegistrationController getController() {
        return (RegistrationController) getActivity();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
        mNextButton = menu.findItem(R.id.next);
        mNextButton.setEnabled(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.next) {
            if (mNextButton.isEnabled()) {
                //try {
                if (validateInput())
                    getController().showDeclarationFragment();
                //} catch (ParseException e) {
                //   e.printStackTrace();
                //}
                return super.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        validateInput();
    }

    private boolean validateInput() {
        if (mCityEditText.getText().toString().matches(mRegexName) &&
                mStateEditText.getText().toString().matches(mRegexName) &&
                mCountryEditText.getText().toString().matches(mRegexName)) {
            return true;
        } else {
            if (TextUtils.isEmpty(mAddressLine1EditText.getText().toString())) {
                mAddressLine1EditText.setError(getResources().getString(R.string.address_line1_error_msg));
            }
            if (TextUtils.isEmpty(mCityEditText.getText().toString())) {
                mCityEditText.setError(getResources().getString(R.string.city_error_msg));
            } else if (mCityEditText.getText().toString().matches(mRegexStr)) {
                mCityEditText.setError(getResources().getString(R.string.numeric_string));
            }
            if (TextUtils.isEmpty(mStateEditText.getText().toString())) {
                mStateEditText.setError(getResources().getString(R.string.state_error_msg));
            } else if (mStateEditText.getText().toString().matches(mRegexStr)) {
                mStateEditText.setError(getResources().getString(R.string.numeric_string));
            }
            if (TextUtils.isEmpty(mCountryEditText.getText().toString())) {
                mCountryEditText.setError(getResources().getString(R.string.country_error_msg));
            } else if (mCountryEditText.getText().toString().matches(mRegexStr)) {
                mCountryEditText.setError(getResources().getString(R.string.numeric_string));
            }
            return false;
        }
    }

    public interface YourAddressFragmentView {

    }
}
