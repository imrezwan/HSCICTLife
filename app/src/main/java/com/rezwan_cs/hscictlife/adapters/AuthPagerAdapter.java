package com.rezwan_cs.hscictlife.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rezwan_cs.hscictlife.fragments.authpage.LoginFragment;
import com.rezwan_cs.hscictlife.fragments.authpage.SignUpFragment;

public class AuthPagerAdapter extends FragmentStatePagerAdapter {
    private static String TAG= "TAG_AuthPagerAdapter";
    private int numOfTabs;

    public AuthPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numOfTabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return SignUpFragment.newInstance();
            case 1:
                return LoginFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

