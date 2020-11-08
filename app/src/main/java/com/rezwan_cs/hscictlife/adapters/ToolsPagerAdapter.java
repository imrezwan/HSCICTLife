package com.rezwan_cs.hscictlife.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rezwan_cs.hscictlife.fragments.authpage.LoginFragment;
import com.rezwan_cs.hscictlife.fragments.authpage.SignUpFragment;
import com.rezwan_cs.hscictlife.fragments.toolspage.CProgramFragment;
import com.rezwan_cs.hscictlife.fragments.toolspage.HTMLFragment;

public class ToolsPagerAdapter extends FragmentStatePagerAdapter {
    private static String TAG= "TAG_AuthPagerAdapter";
    private int numOfTabs;

    public ToolsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numOfTabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return CProgramFragment.newInstance();
            case 1:
                return HTMLFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

