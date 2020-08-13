package com.example.pro_q;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pro_q.Prevalent.Prevalent;
import com.example.pro_q.ViewHolder.My_bookigsViewHolder;
import com.example.pro_q.model.Appointments;
import com.example.pro_q.model.Shops;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MyBookingsActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    public String Scoordinates;
    public double gpslat, gpslong;
    private RecyclerView.LayoutManager layoutManager;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MyBookingsActivity.this,ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        client.getLastLocation().addOnSuccessListener(MyBookingsActivity.this, new OnSuccessListener<Location>()
        {
                    @Override
                    public void onSuccess(Location location)
                    {
                        if (location != null)
                        {
                            gpslat = location.getLatitude();
                            gpslong = location.getLongitude();
                        }
                        else
                        {
                            gpslat = 25.466617;
                            gpslong = 81.850811;
                        }
                    }
        });

        //For toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_my_bookings);
        toolbar.setTitle("My Bookings");
        setSupportActionBar(toolbar);
        //for toolbar

        //For back btn in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //For back btn in toolbar

        recyclerView = findViewById(R.id.my_bookings_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    //gps_location
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }
    //gps_location

    //For back btn in toolbar
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
    //For back btn in toolbar

    protected void onStart()
    {
        super.onStart();

        //Retrieving data from Appointments and displaying in MyBookings layouts

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Appointments");

        FirebaseRecyclerOptions<Appointments> options = new FirebaseRecyclerOptions.Builder<Appointments>()
                .setQuery(cartListRef.child("User View").child(Prevalent.CurrentOnlineUser.getPhone())
                        .child("Shops"), Appointments.class).build();

        FirebaseRecyclerAdapter<Appointments, My_bookigsViewHolder> adapter
                = new FirebaseRecyclerAdapter<Appointments, My_bookigsViewHolder>(options)
        {
            @Override
            protected void onBindViewHolder(@NonNull My_bookigsViewHolder holder, int position, @NonNull final Appointments appointments)
            {

                //Scoordinates to calculate distance
                Scoordinates = appointments.getScoordinates();
                //Scoordinates to calculate distance


                holder.txtShopName.setText(appointments.getSname());
                holder.txtShopAddress.setText(appointments.getSaddress());
                holder.txtBookingDate.setText(appointments.getSdate());
                holder.txtBookingTime.setText(appointments.getStime());
                holder.txtBookingStatus.setText(appointments.getBookingStatus());
                Picasso.get().load(appointments.getSimage()).into(holder.imgShopUrl);

                //Distance Calculation
                if (Scoordinates!=null)
                {
                    String coordinate[] = Scoordinates.split(",");
                    double Slatitude = Double.parseDouble(coordinate[0]);
                    double Slongitude = Double.parseDouble(coordinate[1]);

                    String dis = Double.toString(distance(Slatitude, Slongitude, gpslat, gpslong))+"km";
                    holder.txtShopDistance.setText(dis);
                }
                //Distance Calculation

                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(MyBookingsActivity.this, ReviewBookingActivity.class);
                        startActivity(intent);
                    }
                });

            }
            @NonNull
            @Override
            public My_bookigsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_bookings_layout, parent, false);
                My_bookigsViewHolder holder = new My_bookigsViewHolder(view);
                return holder;
            }

        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    //Method for distance calculation
    private double distance(double lat1, double long1, double lat2, double long2)
    {
        double theta = long1 - long2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble((df.format(dist)));
    }

    private double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad)
    {
        return (rad * 180.0 / Math.PI);
    }
    //Method for distance calculation






//    {
//        double PI_RAD = Math.PI / 180.0;
//        double phi1 = lat1 * PI_RAD;
//        double phi2 = lat2 * PI_RAD;
//        double lam1 = long1 * PI_RAD;
//        double lam2 = long2 * PI_RAD;
//
//        double dist = 6371.01 * acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lam2 - lam1));
//        DecimalFormat df = new DecimalFormat("#.#");
//        return Double.parseDouble((df.format(dist)));
//    }


//    private double distance(double lat1, double long1, double lat2, double long2)
//    {
//
//        double earthRadius = 6371000; //meters
//        double dLat = Math.toRadians(lat2-lat1);
//        double dLng = Math.toRadians(long2-long1);
//        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
//                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
//                        Math.sin(dLng/2) * Math.sin(dLng/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        double dist = (double) (earthRadius * c);
//
//        DecimalFormat df = new DecimalFormat("#.#");
//        return Double.parseDouble((df.format(dist)));
//    }


}
