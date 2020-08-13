package com.example.pro_q;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.pro_q.Prevalent.Prevalent;
import com.example.pro_q.model.Shops;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ShopDetailActivity extends AppCompatActivity
{

    private Button bookAppointment, location_map;
    private String Image;
    private TextView Sname;
    private TextView Sfulladdress;
    private TextView Saddress;
    private TextView Srating;
    private TextView Sgender;
    private String Sid = "";
    private String Scoordinates;

    private ViewPager shopImagesViewPager;
    private TabLayout viewpagerIndicator;
    private FloatingActionButton addToWishlistbtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;

    private ViewPager shopDetailsViewpager;
    private TabLayout shopDetailsTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        //For toolbar
//        Toolbar toolbar = findViewById(R.id.toolbar_shop_details);
//        setSupportActionBar(toolbar);

        //For back btn in toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Sid = getIntent().getStringExtra("Sid");
//        Image = getIntent().getStringExtra("Image");

        bookAppointment = (Button) findViewById(R.id.pd_add_to_cart_button);
        location_map = (Button) findViewById(R.id.shop_location_map);

        FloatingActionButton fab = findViewById(R.id.back_fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(ShopDetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Sname = (TextView) findViewById(R.id.shop_name_details);
        Sfulladdress = (TextView) findViewById(R.id.shop_fulladdress_details);
        Saddress = (TextView) findViewById(R.id.shop_address_details);
        Srating = (TextView) findViewById(R.id.tv_shoplayout_rating_miniview);
//        Swifi =(TextView) findViewById(R.id.shop_free_wifi_availability);
//        Sdepartments =(TextView) findViewById(R.id.shop_departments_availability);
//        Sbrands =(TextView) findViewById(R.id.shop_brands_availability);
//        Sworkingdays =(TextView) findViewById(R.id.shop_workingdays_availability);
        Sgender =(TextView) findViewById(R.id.shop_details_gender);
//        Stiming =(TextView) findViewById(R.id.timing);

        addToWishlistbtn =(FloatingActionButton) findViewById(R.id.add_to_whishlist_btn);

        //Shop image with indicator(Sliding image)
        shopImagesViewPager = findViewById(R.id.shop_image_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);

        //Shop detail layout(Description)
        shopDetailsViewpager = (ViewPager) findViewById(R.id.shop_details_viewpager);
        shopDetailsTablayout = (TabLayout) findViewById(R.id.shop_details_tablayout);

        List<Integer> shopImages = new ArrayList<>();
        shopImages.add(R.drawable.stpic);
        shopImages.add(R.drawable.ndpic);
        shopImages.add(R.drawable.rdpic);

        ImageAdapter shopImagesAdapter = new ImageAdapter(shopImages);
        shopImagesViewPager.setAdapter(shopImagesAdapter);

        viewpagerIndicator.setupWithViewPager(shopImagesViewPager,true);

        shopDetailsViewpager.setAdapter(new ShopDetailsAdapter(getSupportFragmentManager(),shopDetailsTablayout.getTabCount()));

        shopDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(shopDetailsTablayout));
        shopDetailsTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                shopDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        ShopServicesFragment shopServicesFragment = new ShopServicesFragment();

        addToWishlistbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (ALREADY_ADDED_TO_WISHLIST)
                {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishlistbtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#464545")));
                }
                else
                {
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistbtn.setSupportImageTintList(getResources().getColorStateList(R.color.whishlist_red));
                }
            }
        });

        getProductDetails(Sid);

        bookAppointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                bookAppointment();
            }
        });

        location_map.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String Salon = Sname.getText().toString();
                String address = "http://maps.google.com/maps?q="+ Scoordinates +"("+Salon+")&iwloc=A&hl=es";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                startActivity(intent);

            }
        });

    }

    //For back btn in toolbar
//    @Override
//    public boolean onSupportNavigateUp()
//    {
//        onBackPressed();
//        return true;
//    }

    // for editing toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.cart_icon, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

          if (id == R.id.product_cart_icon)
          {
              return true;
          }
          else if (id == R.id.back_icon)
          {
              Intent intent = new Intent(ShopDetailActivity.this,HomeActivity.class);
              startActivity(intent);
          }

        return super.onOptionsItemSelected(item);
    }

    private void bookAppointment()
    {
        String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM,DD,YYYY");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        String date[] = saveCurrentDate.split(",");
        String date1 = date[0]+date[1]+date[2];

        String time[] = saveCurrentTime.split(":");
        String time1 = time[0]+time[1]+time[2].substring(0,2);

        final String bookingID = "00000"+date1+time1;

        final DatabaseReference shopRef = FirebaseDatabase.getInstance().getReference().child("Shops").child(Sid);
        final HashMap<String, Object> shopmap = new HashMap<>();
        shopmap.put("BookingID", bookingID);
        shopRef.updateChildren(shopmap);


        final DatabaseReference appointmentbookingRef = FirebaseDatabase.getInstance().getReference().child("Appointments");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("Sid", Sid);
        cartMap.put("Sname", Sname.getText().toString());
        cartMap.put("Saddress", Saddress.getText().toString());
        cartMap.put("Sdate", saveCurrentDate);
        cartMap.put("Stime", saveCurrentTime);
        cartMap.put("BookingID", bookingID);
        cartMap.put("Scoordinates", Scoordinates);
//        cartMap.put("Simage", Image);

        appointmentbookingRef.child("User View").child(Prevalent.CurrentOnlineUser.getPhone())
                .child("Shops").child(bookingID).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            appointmentbookingRef.child("Admin View").child(Prevalent.CurrentOnlineUser.getPhone())
                                    .child("Shops").child(bookingID).updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {
                                            Toast.makeText(ShopDetailActivity.this, "Added for Appointment", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ShopDetailActivity.this , ReviewBookingActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                        }
                    }
                });
    }

    private void getProductDetails(String SID)
    {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Shops");
        productsRef.child(SID).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    Shops shops = dataSnapshot.getValue(Shops.class);

                    Sname.setText(shops.getSname());
                    Sfulladdress.setText(shops.getSfulladdress());
                    Saddress.setText(shops.getSaddress());
                    Srating.setText(shops.getSrating());
                    Scoordinates = shops.getScoordinates().toString();
                    Sgender.setText(shops.getGender());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

}
