package com.bignerdranch.android.bikeshare;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class BikeShareActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new BikeShareFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

   /* private static final int REQUEST_CODE_START = 0;
    private static final int REQUEST_CODE_END = 1;

    private Button startRide;
    private Button endRide;
    private RideArrayAdapter adapter;
    private ListView listRides;

    private Ride ride;
    private List<Ride> rides;

    private static RidesDB sRidesDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);

        sRidesDB = RidesDB.get(this);

        startRide = findViewById(R.id.start_ride);
        endRide = findViewById(R.id.end_ride);

        startRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BikeShareActivity.this, StartRideActivity.class);
                startActivity(intent);
            }
        });

        endRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(BikeShareActivity.this, EndRideActivity.class);
                startActivity(intent);
            }
        });

        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        rides = sRidesDB.getRidesDB();
        adapter = new RideArrayAdapter(this, rides);
        listRides = findViewById(R.id.list_rides);
        listRides.setAdapter(adapter);
    }*/
}
