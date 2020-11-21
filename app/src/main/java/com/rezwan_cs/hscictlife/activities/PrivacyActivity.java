package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.rezwan_cs.hscictlife.R;

public class PrivacyActivity extends AppCompatActivity {
    WebView privacyWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        privacyWeb = findViewById(R.id.web_privacy);

        privacyWeb.loadUrl("file:///android_asset/privacy_policy.html");
        privacyWeb.getSettings().setJavaScriptEnabled(true);

    }
}