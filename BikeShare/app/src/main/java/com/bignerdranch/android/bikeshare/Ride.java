package com.bignerdranch.android.bikeshare;

public class Ride {
    private String newBike;
    private String newPlace;

    public Ride(String newBike, String newPlace) {
        this.newBike = newBike;
        this.newPlace = newPlace;
    }

    public String getNewBike() {
        return newBike;
    }

    public void setNewBike(String newBike) {
        this.newBike = newBike;
    }

    public String getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(String newPlace) {
        this.newPlace = newPlace;
    }

    public String toString(){
        return newBike + " in: " + newPlace;
    }
}
