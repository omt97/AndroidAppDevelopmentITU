package com.bignerdranch.android.bikeshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartRideActivity extends AppCompatActivity {

    private Button addBikeButton;
    private EditText lastRide;
    private EditText newBike;
    private EditText newPlace;

    private Ride ride;
    private static RidesDB sRidesDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);

        sRidesDB = RidesDB.get(this);

        lastRide = findViewById(R.id.last_ride_start);
        newBike = findViewById(R.id.new_bike_start);
        newPlace = findViewById(R.id.new_place_start);

        addBikeButton = findViewById(R.id.add_button_start);
        addBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newBike.getText().length() > 0 && newPlace.getText().length() > 0){
                    ride = new Ride(newBike.getText().toString().trim(), newPlace.getText().toString().trim(), "");
                    sRidesDB.addRide(newBike.getText().toString().trim(), newPlace.getText().toString().trim());
                    updateUI();
                }
                else{
                    System.out.println("error");
                    Toast.makeText(StartRideActivity.this, "Fill everything", Toast.LENGTH_SHORT).show();
                }
                newBike.setText("");
                newPlace.setText("");
            }
        });
    }

    private void updateUI() {
        lastRide.setText(ride.toString());
    }
}
