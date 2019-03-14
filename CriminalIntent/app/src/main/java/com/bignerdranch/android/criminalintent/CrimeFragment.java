package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String ARG_CRIME_POS = "crime_pos";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.criminalintent.crime_pos";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private int position;

    public static CrimeFragment newInstance(UUID crimeId, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        args.putSerializable(ARG_CRIME_POS, position);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCrime = new Crime();
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        position = (int) getArguments().getSerializable(ARG_CRIME_POS);
        returnResult();
        mCrime = CrimeLab.get(getActivity()).getCrimeById(crimeID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getmTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getmDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });

        return v;
    }

    public void returnResult() {
            Intent data = new Intent();
            data.putExtra(EXTRA_ANSWER_SHOWN, position);
            getActivity().setResult(Activity.RESULT_OK, data);
    }
}
