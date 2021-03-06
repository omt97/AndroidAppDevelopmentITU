package com.bignerdranch.android.bikeshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class BikeShareFragment extends Fragment {
    private static final int REQUEST_CODE_START = 0;
    private static final int REQUEST_CODE_END = 1;
    private static final String KEY_CLICK = "click";

    private Button startRide;
    private Button endRide;
    private Button listRide;
    private RideArrayAdapter adapter;
    private ListView listRides;

    private List<Ride> rides;
    private boolean clicked = false;

    private static RidesDB sRidesDB ;

    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bike_share, container, false);

        if (savedInstanceState != null) {
            clicked = savedInstanceState.getBoolean(KEY_CLICK, false);
        }

        sRidesDB = RidesDB.get(getContext());

        startRide = v.findViewById(R.id.start_ride);
        endRide = v.findViewById(R.id.end_ride);
        listRide = v.findViewById(R.id.list_of_rides);

        rides = sRidesDB.getRidesDB();
        adapter = new RideArrayAdapter(getContext(), rides);
        if(clicked) updateUI();
        
        startRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), StartRideActivity.class);
                startActivity(intent);
            }
        });

        endRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), EndRideActivity.class);
                startActivity(intent);
            }
        });

        listRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                updateUI();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CLICK, clicked);
    }
    
    private void updateUI() {
        listRides = v.findViewById(R.id.list_rides);
        listRides.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rides.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        listRides.setAdapter(adapter);
    }
}
