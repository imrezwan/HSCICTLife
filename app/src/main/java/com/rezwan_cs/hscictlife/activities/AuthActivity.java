package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.adapters.AuthPagerAdapter;
import com.rezwan_cs.hscictlife.commons.ViewPagerHelper;

public class AuthActivity extends AppCompatActivity {
    ViewPager mAuthPager;
    AuthPagerAdapter authPagerAdapter;
    TabLayout authTabLayout;
    TabItem tabSignUp, tabLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        findViews();
        getViewPagerReady();
        ViewPagerHelper.getTabOnClickAction(authTabLayout, mAuthPager);

    }

    private void findViews() {
        mAuthPager = findViewById(R.id.viewpager_registration_page);
        authTabLayout = findViewById(R.id.tablayout_registration);
    }

    private void getViewPagerReady() {
        authPagerAdapter = new AuthPagerAdapter(getSupportFragmentManager(),
                2);
        mAuthPager.setAdapter(authPagerAdapter);
        mAuthPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(authTabLayout));
    }

}