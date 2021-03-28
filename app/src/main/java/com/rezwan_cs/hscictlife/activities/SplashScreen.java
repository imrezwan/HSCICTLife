package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.utilities.DefaultTransition;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();
            }
        }, 0);

    }

    private void goToMainActivity() {
        startActivity(new Intent(SplashScreen.this, InputNameActivity.class));
        finish();
        DefaultTransition.activityTransition(this);
    }
}