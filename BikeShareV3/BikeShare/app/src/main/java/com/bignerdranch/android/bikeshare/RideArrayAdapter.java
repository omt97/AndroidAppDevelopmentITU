package com.bignerdranch.android.bikeshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RideArrayAdapter extends ArrayAdapter<Ride> {

    private final Context context;
    private final List<Ride> values;

    public RideArrayAdapter(Context context, List<Ride> values) {
        super(context, R.layout.list_item_ride, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View concertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_ride, parent, false);
        TextView bikeView = (TextView) rowView.findViewById(R.id.bike_name);
        bikeView.setText(values.get(position).getNewBike());
        TextView startView = (TextView) rowView.findViewById(R.id.start_place);
        startView.setText(values.get(position).getNewPlace());
        TextView endView = (TextView) rowView.findViewById(R.id.end_place);
        endView.setText(values.get(position).getEndPlace());
        return rowView;
    }
}
