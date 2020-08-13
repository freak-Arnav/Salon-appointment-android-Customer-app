package com.example.pro_q.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.Interface.ItemClickListner;
import com.example.pro_q.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtNotificationTitle, txtNotificationSlogan, txtNotificationContent, txtNotificationDate, txtNotificationTime;
    public ImageView imgNotificationUrl;
    private ItemClickListner itemClickListner;

    public NotificationViewHolder(@NonNull View itemView)
    {
        super(itemView);
        txtNotificationTitle = itemView.findViewById(R.id.notification_title);
        txtNotificationSlogan =  itemView.findViewById(R.id.notification_catch_slogan);
        txtNotificationContent = itemView.findViewById(R.id.notification_content);
        txtNotificationDate = itemView.findViewById(R.id.notification_date);
        txtNotificationTime = itemView.findViewById(R.id.notification_time);
        imgNotificationUrl = itemView.findViewById(R.id.notification_img);

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
