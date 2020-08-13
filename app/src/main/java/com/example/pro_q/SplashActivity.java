package com.example.pro_q;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity
{

    Thread objectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startSplash();
    }

    private void startSplash()
    {

        try
        {
            Animation objectAnimation = AnimationUtils.loadAnimation(this,R.anim.translate);

            objectAnimation.reset();
            ImageView objectImageView = findViewById(R.id.splashImg);

            objectImageView.clearAnimation();
            objectImageView.startAnimation(objectAnimation);


            objectThread = new Thread()
            {
                @Override
                public void run()
                {
                    int pauseIt=0;
                    while(pauseIt<3000)
                    {
                        try
                        {
                            sleep(100);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        pauseIt+=100;
                    }
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    SplashActivity.this.finish();
                }
            };
            objectThread.start();

        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
