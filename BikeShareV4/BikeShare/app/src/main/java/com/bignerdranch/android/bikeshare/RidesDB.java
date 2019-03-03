package com.bignerdranch.android.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RidesDB {
    private static RidesDB ridesDB;
    private List<Ride> rides;
    private Ride lastRide;

    public static RidesDB get(Context context){
        if (ridesDB == null){
            ridesDB = new RidesDB(context);
        }
        return ridesDB;
    }

    private RidesDB(Context context){
        lastRide = new Ride ("","","");
        rides = new ArrayList<>();
        rides.add(new Ride("Chuck Norris bike", "ITU", "Fields"));
        rides.add(new Ride("Chick Norris bike", "Fields", "Kongens Nytorv"));
        rides.add(new Ride("Bruce Lee bike", "Kobenhavns Lufthavn", "Kobenhavns Hovedbanegard"));
    }

    public List<Ride> getRidesDB(){
        return rides;
    }

    public void addRide(String what, String where){
        rides.add(new Ride(what, where, ""));
    }

    public void endRide(String what, String where){
        for (Ride ride: rides){
            if (ride.getNewBike().equals(what) && ride.getEndPlace().length() == 0){
                ride.setEndPlace(where);
            }
        }
    }
}
