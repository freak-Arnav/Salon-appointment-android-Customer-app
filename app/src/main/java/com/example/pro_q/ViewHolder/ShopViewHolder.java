package com.example.pro_q.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.Interface.ItemClickListner;
import com.example.pro_q.R;

public class ShopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtShopName, txtShopAddress, txtShopgender, txtratingfront, txtShopdistance;
    public ImageView imageView;
    public ItemClickListner listner;

    public ShopViewHolder(View itemView)
    {
        super(itemView);
        txtShopName = (TextView) itemView.findViewById(R.id.shop_name);
        txtShopAddress = (TextView) itemView.findViewById(R.id.shop_address);
        txtShopgender = (TextView) itemView.findViewById(R.id.shop_gender);
        txtratingfront = (TextView) itemView.findViewById(R.id.tv_shoplayout_rating);
        txtShopdistance = (TextView) itemView.findViewById(R.id.shop_layout_distance_txt);
        imageView = (ImageView) itemView.findViewById(R.id.shop_image);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
