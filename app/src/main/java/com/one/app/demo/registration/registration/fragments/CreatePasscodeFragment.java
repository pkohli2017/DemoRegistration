package com.one.app.demo.registration.registration.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;
import com.one.app.demo.registration.base.fragments.BaseFragment;
import com.one.app.demo.registration.registration.activities.RegistrationActivity;
import com.one.app.demo.registration.registration.controller.RegistrationController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreatePasscodeFragment.CreatePasscodeFragmentView} interface
 * to handle interaction events.
 * Use the {@link CreatePasscodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePasscodeFragment extends BaseFragment implements
        CreatePasscodeFragmentPresenter.CreatePasscodeFragmentPresenterView {

    public static final String FRAGMENT_TAG = CreatePasscodeFragment.class.getSimpleName();
    private FragmentActivity mActivity;
    private CreatePasscodeFragmentPresenter mCreatePasscodeFragmentPresenter;
    private CreatePasscodeFragmentView mCreatePasscodeFragmentView;
    private MenuItem mNextButton;
    private RegistrationController mController;
    private TextInputEditText mTextInputEditTextPasscode;
    private TextInputEditText mTextInputEditTextReEnterPasscode;

    public CreatePasscodeFragment() {
        // Required empty public constructor
    }

    public static CreatePasscodeFragment newInstance(CreatePasscodeFragment.CreatePasscodeFragmentView view) {
        CreatePasscodeFragment fragment = new CreatePasscodeFragment();
        fragment.mCreatePasscodeFragmentView = view;
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CreatePasscodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePasscodeFragment newInstance() {
        return new CreatePasscodeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mController = (RegistrationActivity) getController();
        mCreatePasscodeFragmentPresenter = CreatePasscodeFragmentPresenter.newInstance(this, mController);
        mCreatePasscodeFragmentPresenter.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_passcode, container, false);
        mController.initActionBar(R.string.fragment_create_passcode_title, BaseActivity.INVALID_HOME_ICON, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mTextInputEditTextPasscode = (TextInputEditText) view.findViewById(R.id.textInputEditTextEnterPasscode);
        mTextInputEditTextReEnterPasscode = (TextInputEditText) view.findViewById(R.id.textInputEditTextReEnterPasscode);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        mNextButton = menu.findItem(R.id.next);
        mNextButton.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.next) {
            boolean isPasswordValid = isPasswordValid();
            if (isPasswordValid) {
                mController.showDocumentUploadFragment();
            }
        }
        return true;
    }

    private boolean isPasswordValid() {
        String newPassword = mTextInputEditTextPasscode.getText().toString();
        String confirmPassword = mTextInputEditTextReEnterPasscode.getText().toString();
        boolean isNewPasswordEmpty = TextUtils.isEmpty(newPassword);
        boolean isConfirmPasswordEmpty = TextUtils.isEmpty(confirmPassword);
        if (isNewPasswordEmpty && isConfirmPasswordEmpty) {
            showPasscodeErrorMessage();
            showReEnterPasscodeErrorMessage();
            return false;
        } else if (isNewPasswordEmpty) {
            showPasscodeErrorMessage();
            return false;
        } else if (isConfirmPasswordEmpty) {
            showReEnterPasscodeErrorMessage();
            return false;
        } else if (!isNewPasswordEmpty && !isConfirmPasswordEmpty) {
            if (!isReEnterPasscodeSame(newPassword, confirmPassword)) {
                mTextInputEditTextReEnterPasscode.setError(getString(R.string.re_enter_passcode_toast));
                return false;
            }
        }
        return true;
    }

    private boolean isReEnterPasscodeSame(String newPassword, String reEnterPassword) {
        return newPassword.equals(reEnterPassword);
    }

    private void showReEnterPasscodeErrorMessage() {
        mTextInputEditTextReEnterPasscode.setError(getString(R.string.re_enter_passcode_validation_message_text));
    }

    private void showPasscodeErrorMessage() {
        mTextInputEditTextPasscode.setError(getString(R.string.passcode_validation_message_text));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface CreatePasscodeFragmentView {

    }
}
