package com.example.parkingapplicatie2;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {

    private ArrayList<Parking> mParkingList;
    public ParkingAdapter(ArrayList<Parking> parkings)
    {
        mParkingList = parkings;
    }

    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_item, parent, false);
        ParkingViewHolder pvh = new ParkingViewHolder(v);
        return pvh;
    }

    public class ParkingViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewName;
        public TextView mTextViewNumber;

        public ParkingViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.name);
            mTextViewNumber = itemView.findViewById(R.id.numberOfPlaces);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        Parking currentParking = mParkingList.get(position);
        holder.mTextViewName.setText(currentParking.getName());
        holder.mTextViewNumber.setText(Integer.toString(currentParking.getParkingStatus().getAvailableCapacity()));

        double percentageFree = (double)currentParking.getParkingStatus().getAvailableCapacity()/(double)currentParking.getParkingStatus().getTotalCapacity();
        if(percentageFree > 0.75){
            holder.mTextViewNumber.setBackgroundColor(Color.rgb(0, 128, 0)); }
        else if(percentageFree < 0.05){
            holder.mTextViewNumber.setBackgroundColor(Color.RED);}
        else{
            int orange = Color.rgb(255, 165, 0);holder.mTextViewNumber.setBackgroundColor(orange); }

    }

    @Override
    public int getItemCount() {
        return mParkingList.size();
    }

}
