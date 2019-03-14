package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends /*AppCompatActivity*/ SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private static final String EXTRA_CRIME_POS = "com.bignerdranch.android.criminalintent.position";

    @Override
    protected Fragment createFragment() {
        UUID crimeID = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        int position = (int) getIntent().getSerializableExtra(EXTRA_CRIME_POS);
        return CrimeFragment.newInstance(crimeID, position);
    }

    public static Intent newIntent(Context context, UUID crimeID, int position) {
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        intent.putExtra(EXTRA_CRIME_POS, position);
        return intent;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }*/

}
