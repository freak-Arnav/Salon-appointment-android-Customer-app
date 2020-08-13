package com.example.pro_q.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.Interface.ItemClickListner;
import com.example.pro_q.R;

public class My_bookigsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtShopName, txtShopAddress, txtBookingDate, txtBookingTime, txtShopDistance, txtBookingStatus;
    public ImageView imgShopUrl;
    private ItemClickListner itemClickListner;

    public My_bookigsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imgShopUrl = itemView.findViewById(R.id.my_bookings_image);
        txtShopName = itemView.findViewById(R.id.my_bookings_salon_name);
        txtShopAddress = itemView.findViewById(R.id.my_bookings_salon_address);
        txtBookingDate = itemView.findViewById(R.id.my_bookings_date);
        txtBookingTime = itemView.findViewById(R.id.my_bookings_time);
        txtShopDistance = itemView.findViewById(R.id.my_bookings_distance_txt);
        txtBookingStatus = itemView.findViewById(R.id.my_bookings_status);
    }

    @Override
    public void onClick(View view)
    {
        itemClickListner.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner)
    {
        this.itemClickListner = itemClickListner;
    }
}
