package com.rezwan_cs.hscictlife.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.fragments.mainpage.HomeFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.MoreFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.QuizFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.RankingFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.ToolsFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavigationBar();
        // selectedFragment = new HomeFragment();
        navigation.setSelectedItemId(R.id.nav_home);
    }

    private void initBottomNavigationBar() {
        navigation =
                findViewById(R.id.nav_main_bottom_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }




    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_quiz:
                        fragment = new QuizFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_ranking:
                        fragment = new RankingFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_more:
                        fragment = new MoreFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_tools:
                        fragment = new ToolsFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        };



    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations
                (android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        transaction.replace(R.id.container_area, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}