package com.one.app.demo.registration.registration.fragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.one.app.demo.registration.R;
import com.one.app.demo.registration.registration.controller.RegistrationController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PersonalDetailsFragment extends Fragment implements
        PersonalDetailsFragmentPresenter.PersonalDetailsFragmentPresenterView, View.OnFocusChangeListener {

    public static final String FRAGMENT_TAG = PersonalDetailsFragment.class.getSimpleName();
    private FragmentActivity mActivity;
    private PersonalDetailsFragmentView mPersonalDetailsFragmentView;
    private PersonalDetailsFragmentPresenter mPersonalDetailsFragmentPresenter;

    private Spinner mTitle;
    private EditText mDateOfBirth, mFirstName, mMiddleName, mSurname, mNationality;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private MenuItem mNextButton;
    private final String myFormat = "MM/dd/yy";
    private final String mRegexStr = ".*\\d+.*";
    private final String mRegexName = "[a-zA-Z]+";

    public PersonalDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalDetailsFragment newInstance() {
        PersonalDetailsFragment fragment = new PersonalDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mPersonalDetailsFragmentPresenter = PersonalDetailsFragmentPresenter.newInstance(this, getController());
        mPersonalDetailsFragmentPresenter.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private RegistrationController getController() {
        return (RegistrationController) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);
        mPersonalDetailsFragmentPresenter.onCreateView(inflater, container, savedInstanceState);
        initializeViews(view);
        setTitleSpinner(view);
        setListeners();
        return view;
    }

    private void setListeners() {
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                try {
                    doValidation();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        mDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDateOfBirth(v, mOnDateSetListener);
            }
        });
    }

    private void getDateOfBirth(View view, DatePickerDialog.OnDateSetListener date) {
        new DatePickerDialog(view.getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        mDateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    private void setTitleSpinner(View view) {
        String[] stringArray = getResources().getStringArray(R.array.title_spinner);
        List<String> titleList = new ArrayList<>(Arrays.asList(stringArray));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                view.getContext(), android.R.layout.simple_list_item_1, titleList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        mTitle.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);
        mNextButton = menu.findItem(R.id.next);
        mNextButton.setEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.next) {
            if (mNextButton.isEnabled()) {
                try {
                    if (doValidation())
                        getController().showTermsAndConditionsFragment();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return super.onOptionsItemSelected(item);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean doValidation() throws ParseException {
        String title = mTitle.getSelectedItem().toString();
        String firstName = mFirstName.getText().toString();
        String middleName = mMiddleName.getText().toString();
        String surname = mSurname.getText().toString();
        String nationality = mNationality.getText().toString();
        String dob = mDateOfBirth.getText().toString();
        Date dateInput = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        Date dateCurrent = sdf.parse(getCurrentDate());

        if (!(dob.isEmpty()))
            dateInput = sdf.parse(dob);

        if (!(title.equals(getResources().getString(R.string.personal_detail_title))) &&
                firstName.matches(mRegexName) &&
                (middleName.matches(mRegexName) ||
                        middleName.equalsIgnoreCase("")) &&
                surname.matches(mRegexName) &&
                surname.matches(mRegexName) &&
                (dateInput.compareTo(dateCurrent) <= 0)
                ) {
            return true;
        } else {
            if (title.equals(getResources().getString(R.string.personal_detail_title)))
                ((TextView) mTitle.getSelectedView()).setError(getResources().getString(R.string.select_title));

            if (firstName.isEmpty())
                mFirstName.setError(getResources().getString(R.string.empty_string));

            if (firstName.matches(mRegexStr))
                mFirstName.setError(getResources().getString(R.string.numeric_string));

            if (middleName.matches(mRegexStr))
                mMiddleName.setError(getResources().getString(R.string.numeric_string));

            if (surname.isEmpty())
                mSurname.setError(getResources().getString(R.string.empty_string));

            if (surname.matches(mRegexStr))
                mSurname.setError(getResources().getString(R.string.numeric_string));

            if (nationality.isEmpty())
                mNationality.setError(getResources().getString(R.string.empty_string));

            if (nationality.matches(mRegexStr))
                mNationality.setError(getResources().getString(R.string.numeric_string));

            if (dob.isEmpty())
                mDateOfBirth.setError(getResources().getString(R.string.date_empty));
            else if (dateInput.compareTo(dateCurrent) > 0)
                mDateOfBirth.setError(getResources().getString(R.string.dob_is_greater));
            else
                mDateOfBirth.setError(null);

            return false;
        }
    }

    private void initializeViews(View view) {
        mTitle = view.findViewById(R.id.spinner_title);
        mDateOfBirth = view.findViewById(R.id.edit_date_of_birth);
        mFirstName = view.findViewById(R.id.edit_first_name);
        mMiddleName = view.findViewById(R.id.edit_middle_name);
        mSurname = view.findViewById(R.id.edit_surname);
        mNationality = view.findViewById(R.id.edit_nationality);

        mTitle.setOnFocusChangeListener(this);
        mDateOfBirth.setOnFocusChangeListener(this);
        mFirstName.setOnFocusChangeListener(this);
        mMiddleName.setOnFocusChangeListener(this);
        mSurname.setOnFocusChangeListener(this);
        mNationality.setOnFocusChangeListener(this);
        myCalendar = Calendar.getInstance();
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        try {
            doValidation();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public interface PersonalDetailsFragmentView {
    }
}
