package com.example.pro_q;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.model.ShopUtilities;

import java.util.List;

public class ShopUtilityAdapter extends RecyclerView.Adapter<ShopUtilityAdapter.ViewHolder>
{
    private List<ShopUtilities> shopUtilityList;

    public ShopUtilityAdapter(List<ShopUtilities> shopUtilityList)
    {
        this.shopUtilityList = shopUtilityList;
    }

    @NonNull
    @Override
    public ShopUtilityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_utilities_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopUtilityAdapter.ViewHolder holder, int position)
    {
        String utilityTitle = shopUtilityList.get(position).getUtilityName();
        String utilityDetail = shopUtilityList.get(position).getUtilityValue();
        holder.setUtility(utilityTitle,utilityDetail);
    }

    @Override
    public int getItemCount()
    {
        return shopUtilityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView utilityName;
        private TextView utilityValue;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            utilityName = itemView.findViewById(R.id.utility_name);
            utilityValue = itemView.findViewById(R.id.utility_value);
        }
        private void setUtility(String utilityTitle,String utilityDetail)
        {
            utilityName.setText(utilityTitle);
            utilityValue.setText(utilityDetail);
        }
    }
}
