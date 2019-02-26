package com.bignerdranch.android.bikeshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BikeShareActivity extends AppCompatActivity {

    private Button addBikeButton;
    private EditText lastRide;
    private EditText newBike;
    private EditText newPlace;

    private Ride ride;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);

        lastRide = findViewById(R.id.last_ride);
        newBike = findViewById(R.id.new_bike);
        newPlace = findViewById(R.id.new_place);

        addBikeButton = findViewById(R.id.add_button);
        addBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newBike.getText().length() > 0 && newPlace.getText().length() > 0){
                    ride = new Ride(newBike.getText().toString().trim(), newPlace.getText().toString().trim());
                }
                else{
                    Toast.makeText(BikeShareActivity.this, "Fill everything", Toast.LENGTH_SHORT).show();
                }
                newBike.setText("");
                newPlace.setText("");
                updateUI();
            }
        });
    }

    private void updateUI() {
        lastRide.setText(ride.toString());
    }
}
