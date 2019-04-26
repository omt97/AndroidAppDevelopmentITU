package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private boolean done = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        System.out.println("hello");
        updateUI();
        done = true;

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        if (!done) CrimeLab.createRow();
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            System.out.println(2);
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else{
            System.out.println(4);
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Crime crimes;

        private TextView titleCrime;
        private TextView dateCrime;
        private ImageView solvedCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);
            titleCrime = itemView.findViewById(R.id.crime_title);
            dateCrime = itemView.findViewById(R.id.crime_date);
            solvedCrime = itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            crimes = crime;
            titleCrime.setText(crimes.getmTitle());
            dateCrime.setText(crimes.getmDate().toString());
            solvedCrime.setVisibility(crime.ismSolved() ? View.VISIBLE : View.GONE);
        }  @Override
        public void onClick(View v) {
            Intent intent = ViewPagerActivity.newIntent(getActivity(), crimes.getmId(), this.getLayoutPosition());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        public void setCrimes(List<Crime> crimes){
            mCrimes = crimes;
        }

        public void createRows() {
        }
    }
}
