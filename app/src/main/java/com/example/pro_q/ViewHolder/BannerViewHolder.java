package com.example.pro_q.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.Interface.ItemClickListner;
import com.example.pro_q.R;

public class BannerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public ImageView imgShopUrl;
    public TextView Bshops;
    private ItemClickListner itemClickListner;

    public BannerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imgShopUrl = itemView.findViewById(R.id.icon);
        Bshops = itemView.findViewById(R.id.Id);
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






























//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.maps_trial.models.Items;
//
//import java.util.ArrayList;
//
//class BannerViewHolder extends RecyclerView.Adapter<BannerViewHolder.viewHolder>
//{
//
//    Context context;
//    ArrayList<Items> arrayList;
//
//    public BannerViewHolder(Context context, ArrayList<Items> arrayList)
//    {
//        this.context = context;
//        this.arrayList = arrayList;
//    }
//
//    @NonNull
//    @Override
//    public BannerViewHolder.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
//    {
//        View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
//        return new viewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull viewHolder holder, int position)
//    {
//        viewHolder.icon.setImageResource(arrayList.get(position).getImage());
//    }
//
//    @Override
//    public int getItemCount()
//    {
//        return arrayList.size();
//    }
//
//    public static class viewHolder extends RecyclerView.ViewHolder
//    {
//        public static ImageView icon;
//        public viewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            icon = (ImageView) itemView.findViewById(R.id.icon);
//        }
//    }
//}
