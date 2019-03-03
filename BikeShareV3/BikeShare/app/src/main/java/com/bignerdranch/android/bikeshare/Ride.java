package com.bignerdranch.android.bikeshare;

public class Ride {
    private String newBike;
    private String newPlace;
    private String endPlace;

    public Ride(String newBike, String newPlace, String endPlace) {
        this.newBike = newBike;
        this.newPlace = newPlace;
        this.endPlace = endPlace;
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

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String toString(){
        return newBike + " in: " + newPlace;
    }
}
