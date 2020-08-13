package com.example.pro_q;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro_q.Prevalent.Prevalent;
import com.example.pro_q.ViewHolder.BannerViewHolder;
import com.example.pro_q.ViewHolder.ShopViewHolder;
import com.example.pro_q.model.Banners;
import com.example.pro_q.model.Shops;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private DatabaseReference ShopsRef;
    private DatabaseReference cartListRef;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView bannerrecyclerView;
    private RecyclerView.LayoutManager bannerlayoutManager;
    public TextView Bshops;

    //location
    public String Scoordinates;
    public double gpslat, gpslong;
    private FusedLocationProviderClient client;
    //location

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Banners
        bannerrecyclerView = findViewById(R.id.main_recycler);
        bannerrecyclerView.setHasFixedSize(true);
        bannerlayoutManager = new LinearLayoutManager(this);
        bannerrecyclerView.setLayoutManager(bannerlayoutManager);

        bannerrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        bannerrecyclerView.setItemAnimator(new DefaultItemAnimator());

//        String Bimages[] = new String[10];
//        BannerRef = FirebaseDatabase.getInstance().getReference().child("Banners");
//        BannerRef.child("BannerData").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//            {
//                if (dataSnapshot.exists())
//                {
//                    Banners banners = dataSnapshot.getValue(Banners.class);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        //Banners

        ShopsRef = FirebaseDatabase.getInstance().getReference().child("Shops");
        Paper.init(this);

        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(2).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(4).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(5).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(6).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(7).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(8).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(9).setActionView(R.layout.menu_image);
        navigationView.getMenu().getItem(10).setActionView(R.layout.menu_image);


        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        TextView useremailTextView = headerView.findViewById(R.id.user_profile_email);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        userNameTextView.setText(Prevalent.CurrentOnlineUser.getName());
        useremailTextView.setText(Prevalent.CurrentOnlineUser.getEmailID());
        Picasso.get().load(Prevalent.CurrentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //banner
        cartListRef = FirebaseDatabase.getInstance().getReference().child("Banners");

        FirebaseRecyclerOptions<Banners> banneroptions = new FirebaseRecyclerOptions.Builder<Banners>()
                .setQuery(cartListRef, Banners.class).build();

        FirebaseRecyclerAdapter<Banners, BannerViewHolder> banneradapter
                = new FirebaseRecyclerAdapter<Banners, BannerViewHolder>(banneroptions) {
            @Override
            protected void onBindViewHolder(@NonNull BannerViewHolder holder, int position, @NonNull final Banners items) {

                Picasso.get().load(items.getBimage()).into(holder.imgShopUrl);
                holder.Bshops.setText(items.getBshops());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, MyBookingsActivity.class);
                        intent.putExtra("Id", items.getBshops());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_layout, parent, false);
                BannerViewHolder holder = new BannerViewHolder(view);
                return holder;
            }

        };
        bannerrecyclerView.setAdapter(banneradapter);
        banneradapter.startListening();
        //banner

        //gps location
        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);
        //gps location


        ShopsRef = FirebaseDatabase.getInstance().getReference().child("Shops");

        FirebaseRecyclerOptions<Shops> options =
                new FirebaseRecyclerOptions.Builder<Shops>()
                        .setQuery(ShopsRef, Shops.class)
                        .build();


        FirebaseRecyclerAdapter<Shops, ShopViewHolder> adapter =
                new FirebaseRecyclerAdapter<Shops, ShopViewHolder>(options)
                {
                    @Override
                    protected void onBindViewHolder(@NonNull ShopViewHolder holder, int position, @NonNull final Shops model )
                    {

                        //Scoordinates to calculate distance
                        Scoordinates = model.getScoordinates();
                        //Scoordinates to calculate distance

                        holder.txtShopName.setText(model.getSname());
                        holder.txtShopAddress.setText(model.getSaddress());
                        holder.txtShopgender.setText(model.getGender());
                        holder.txtratingfront.setText(model.getSrating());
                        Picasso.get().load(model.getImage1()).into(holder.imageView);

                        //Distance Calculation
                        if (Scoordinates!=null)
                        {
                            String coordinate[] = Scoordinates.split(",");
                            double Slatitude = Double.parseDouble(coordinate[0]);
                            double Slongitude = Double.parseDouble(coordinate[1]);


                            //gps location
                            if (ActivityCompat.checkSelfPermission(HomeActivity.this,ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                            {
                                return;
                            }
                            client.getLastLocation().addOnSuccessListener(HomeActivity.this, new OnSuccessListener<Location>()
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
                            //gps location


                            String dis = Double.toString(distance(Slatitude, Slongitude, gpslat, gpslong))+"km";
                            holder.txtShopdistance.setText(dis);
                        }
                        //Distance Calculation

                        holder.itemView.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                Intent intent = new Intent(HomeActivity.this, ShopDetailActivity.class);
                                intent.putExtra("Sid", model.getSid());
//                                intent.putExtra("Image", model.getImage1());
                                startActivity(intent);
                            }
                        });


                    }

                    @NonNull
                    @Override
                    public ShopViewHolder onCreateViewHolder( ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout, parent, false);
                        ShopViewHolder holder = new ShopViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    //gps_location
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION},1);
    }
    //gps_location


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


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
            Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
            startActivity(intent);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

          if (id == R.id.notification_bell)
          {
              Intent intent = new Intent(HomeActivity.this,NotificationActivity.class);
              startActivity(intent);
          }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_upcoming_bookings)
        {
            Intent intent = new Intent(HomeActivity.this, UpcomingBookingActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_my_bookings)
        {
            Intent intent = new Intent(HomeActivity.this, MyBookingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.wishlist)
        {
            Intent intent = new Intent(HomeActivity.this, WishlistActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_proq_money)
        {
            Intent intent = new Intent(HomeActivity.this, ProQMoneyActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_refernearn)
        {
            Intent intent = new Intent(HomeActivity.this, RefernEarnActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_about_pro_q)
        {
            Intent intent = new Intent(HomeActivity.this, AboutProQActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_contacts)
        {
            Intent intent = new Intent(HomeActivity.this, ContactsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_faq)
        {
            Intent intent = new Intent(HomeActivity.this, FAQActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_tnc)
        {
            Intent intent = new Intent(HomeActivity.this, TnCActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_signout)
        {
            Paper.book().destroy();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
