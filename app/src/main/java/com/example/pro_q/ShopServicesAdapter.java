package com.example.pro_q;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.model.ShopServices;

import java.util.List;

public class ShopServicesAdapter extends RecyclerView.Adapter<ShopServicesAdapter.ViewHolder>
{

    private List<ShopServices> shopServicesList;

    public ShopServicesAdapter(List<ShopServices> shopServicesList)
    {
        this.shopServicesList = shopServicesList;
    }

    @NonNull
    @Override
    public ShopServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_checkbox_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopServicesAdapter.ViewHolder holder, int position)
    {
        String servicesName = shopServicesList.get(position).getServicesName();
        String servicesPrice = shopServicesList.get(position).getServicesPrice();
        holder.setServices(servicesName,servicesPrice);
    }

    @Override
    public int getItemCount()
    {
        return shopServicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView servicesName;
        private TextView servicesPrice;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            servicesName = itemView.findViewById(R.id.services_name);
            servicesPrice = itemView.findViewById(R.id.services_price);
        }

        private void setServices(String serviceName,String servicePrice)
        {
            servicesName.setText(serviceName);
            servicesPrice.setText(servicePrice);
        }
    }
}
