package com.example.pro_q;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro_q.Prevalent.Prevalent;
import com.example.pro_q.ViewHolder.My_bookigsViewHolder;
import com.example.pro_q.ViewHolder.NotificationViewHolder;
import com.example.pro_q.model.Appointments;
import com.example.pro_q.model.Notifications;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class NotificationActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //For toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_notifications);
        toolbar.setTitle("Notifications");
        setSupportActionBar(toolbar);

        //For back btn in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.notification_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    //For back btn in toolbar
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }

    protected void onStart()
    {
        super.onStart();

        final DatabaseReference notificationListRef = FirebaseDatabase.getInstance().getReference().child("Notifications");

        FirebaseRecyclerOptions<Notifications> options = new FirebaseRecyclerOptions.Builder<Notifications>()
                .setQuery(notificationListRef.child("UserView"), Notifications.class).build();

        FirebaseRecyclerAdapter<Notifications, NotificationViewHolder> adapter
                = new FirebaseRecyclerAdapter<Notifications, NotificationViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull NotificationViewHolder holder, int position, @NonNull final Notifications notifications)
            {

                holder.txtNotificationTitle.setText(notifications.getNotificationTitle());
                holder.txtNotificationSlogan.setText(notifications.getNotificationSlogan());
                holder.txtNotificationContent.setText(notifications.getNotificationContent());
                holder.txtNotificationDate.setText(notifications.getNotificationDate());
                holder.txtNotificationTime.setText(notifications.getNotificationTime());
                Picasso.get().load(notifications.getNotificationImg()).into(holder.imgNotificationUrl);

            }
            @NonNull
            @Override
            public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_layout, parent, false);
                NotificationViewHolder holder = new NotificationViewHolder(view);
                return holder;
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}
