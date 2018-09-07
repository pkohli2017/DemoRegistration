package com.one.app.demo.registration.registration.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.base.activities.BaseActivity;
import com.one.app.demo.registration.base.fragments.BaseFragment;
import com.one.app.demo.registration.registration.activities.RegistrationActivity;
import com.one.app.demo.registration.registration.controller.RegistrationController;

import java.io.InputStream;


public class DocumentUploadFragment extends BaseFragment implements
        DocumentUploadFragmentPresenter.DocumentUploadFragmentPresenterView, View.OnClickListener {

    public static final String FRAGMENT_TAG = DocumentUploadFragment.class.getSimpleName();
    /*Request code*/
    private final int REQUEST_CODE_ADHAAR = 101;
    private final int REQUEST_CODE_PASSPORT = 102;
    private final int REQUEST_CODE_PAN = 103;
    private final int REQUEST_CODE_PHOTO = 104;
    private FragmentActivity mActivity;
    private RegistrationController mController;
    private MenuItem mNextButton;
    private DocumentUploadFragmentPresenter.DocumentUploadFragmentPresenterView mDocumentUploadFragmentPresenterView;
    private DocumentUploadFragmentPresenter mDocumentUploadFragmentPresenter;
    /*View*/
    private Button mAdharBtn, mPassportBtn, mPanCardBtn, mPanBtn;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TermsAndConditionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DocumentUploadFragment newInstance(DocumentUploadFragmentPresenter.DocumentUploadFragmentPresenterView view) {
        DocumentUploadFragment fragment = new DocumentUploadFragment();
        fragment.mDocumentUploadFragmentPresenterView = view;
        return fragment;
    }

    // TODO: Rename and change types and number of parameters
    public static DocumentUploadFragment newInstance() {
        DocumentUploadFragment fragment = new DocumentUploadFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mController = (RegistrationActivity) getController();
        mDocumentUploadFragmentPresenter = DocumentUploadFragmentPresenter.newInstance(this, mController);
        mDocumentUploadFragmentPresenter.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*         Inflate the layout for this fragment
         change the layout with the REAL ONE*/
        View view = inflater.inflate(R.layout.fragment_document_upload, container, false);
        mDocumentUploadFragmentPresenter.onCreateView(inflater, container, savedInstanceState);
        mController.initActionBar(R.string.fragment_document_upload_title, BaseActivity.INVALID_HOME_ICON, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {
        mAdharBtn = (Button) view.findViewById(R.id.upload_adhaar_card);
        mPassportBtn = (Button) view.findViewById(R.id.upload_passport);
        mPanCardBtn = (Button) view.findViewById(R.id.upload_pan_card);
        mPanBtn = (Button) view.findViewById(R.id.upload_photo);
        mAdharBtn.setOnClickListener(this);
        mPassportBtn.setOnClickListener(this);
        mPanCardBtn.setOnClickListener(this);
        mPanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_adhaar_card:
                fetchImage(REQUEST_CODE_ADHAAR);
                break;
            case R.id.upload_passport:
                fetchImage(REQUEST_CODE_PASSPORT);
                break;
            case R.id.upload_pan_card:
                fetchImage(REQUEST_CODE_PAN);
                break;
            case R.id.upload_photo:
                fetchImage(REQUEST_CODE_PHOTO);
                break;
        }
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
            mController.showPersonalDetailsFragment();
        }
        return true;
    }

    public void fetchImage(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getActivity().getResources().getString(R.string.select_image)), requestCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode >= REQUEST_CODE_ADHAAR && requestCode <= REQUEST_CODE_PHOTO && data != null) {
                Uri selectedImageUri = data.getData();
                final InputStream inputStream = getActivity().getContentResolver().openInputStream(selectedImageUri);
                final Bitmap imageMap = BitmapFactory.decodeStream(inputStream);
                setThumbNailOnButton(requestCode, imageMap);
            } else {
                Toast toast = Toast.makeText(getActivity(), "No Image is selected.", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setThumbNailOnButton(int requestCode, Bitmap imageMap) {
        try {
            switch (requestCode) {
                case REQUEST_CODE_ADHAAR:
                    mAdharBtn.setBackground(new BitmapDrawable(getResources(), imageMap));
                    mAdharBtn.setText(null);
                    break;
                case REQUEST_CODE_PASSPORT:
                    mPassportBtn.setBackground(new BitmapDrawable(getResources(), imageMap));
                    mPassportBtn.setText(null);
                    break;
                case REQUEST_CODE_PAN:
                    mPanCardBtn.setBackground(new BitmapDrawable(getResources(), imageMap));
                    mPanCardBtn.setText(null);
                    break;
                case REQUEST_CODE_PHOTO:
                    mPanBtn.setBackground(new BitmapDrawable(getResources(), imageMap));
                    mPanBtn.setText(null);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface DocumentUploadFragmentPresenterView {
    }
}