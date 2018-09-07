package com.one.app.demo.registration.registration.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.registration.controller.RegistrationController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeclarationFragment.DeclarationFragmentView} interface
 * to handle interaction events.
 * Use the {@link DeclarationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeclarationFragment extends Fragment implements
        DeclarationFragmentPresenter.DeclarationFragmentPresenterView,
        CompoundButton.OnCheckedChangeListener {

    public static final String FRAGMENT_TAG = DeclarationFragment.class.getSimpleName();
    private FragmentActivity mActivity;
    private DeclarationFragmentPresenter mDeclarationFragmentPresenter;
    private DeclarationFragmentView mDeclarationFragmentView;
    private MenuItem mNextButton;
    private RegistrationController mController;
    private MenuItem mFinish;
    private CheckBox mCheckBoxUndersatnd, mCheckBoxAccepted, mCheckBoxAgree;

    public DeclarationFragment() {
        // Required empty public constructor
    }

    public static DeclarationFragment newInstance(DeclarationFragment.DeclarationFragmentView view) {
        DeclarationFragment fragment = new DeclarationFragment();
        fragment.mDeclarationFragmentView = view;
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DeclarationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeclarationFragment newInstance() {
        return new DeclarationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mController = getController();
        mDeclarationFragmentPresenter = DeclarationFragmentPresenter.newInstance(this, mController);
        mDeclarationFragmentPresenter.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_declaration, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mCheckBoxUndersatnd = (CheckBox) view.findViewById(R.id.checkbox_understand);
        mCheckBoxAccepted = (CheckBox) view.findViewById(R.id.checkbox_accepted);
        mCheckBoxAgree = (CheckBox) view.findViewById(R.id.checkbox_agree);
        mCheckBoxUndersatnd.setOnCheckedChangeListener(this);
        mCheckBoxAccepted.setOnCheckedChangeListener(this);
        mCheckBoxAgree.setOnCheckedChangeListener(this);
    }

    private RegistrationController getController() {
        return (RegistrationController) getActivity();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_menu, menu);
        mNextButton = menu.findItem(R.id.next);
        mFinish = menu.findItem(R.id.finish);
        mFinish.setVisible(true);
        mNextButton.setVisible(false);
        mFinish.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.finish) {
            openLoginActivity();
            return true;
        } else return super.onOptionsItemSelected(item);
    }

    private void openLoginActivity() {
        mDeclarationFragmentView.showLoginScreen();
    }

    private void checkAllCheckBox() {
        if (mCheckBoxUndersatnd.isChecked() && mCheckBoxAccepted.isChecked() && mCheckBoxAgree.isChecked())
            mFinish.setEnabled(true);
        else
            mFinish.setEnabled(false);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        checkAllCheckBox();
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
    public interface DeclarationFragmentView {
        void showLoginScreen();
    }
}
