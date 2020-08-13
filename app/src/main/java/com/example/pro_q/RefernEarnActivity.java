package com.example.pro_q;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class RefernEarnActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refern_earn);

        //For toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_refer_n_earn);
        toolbar.setTitle("Refer n Earn");
        setSupportActionBar(toolbar);

        //For back btn in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //For back btn in toolbar
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}
