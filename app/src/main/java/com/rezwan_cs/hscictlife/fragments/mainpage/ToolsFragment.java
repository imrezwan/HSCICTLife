package com.rezwan_cs.hscictlife.fragments.mainpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.adapters.AuthPagerAdapter;
import com.rezwan_cs.hscictlife.adapters.ToolsPagerAdapter;
import com.rezwan_cs.hscictlife.commons.ViewPagerHelper;

public class ToolsFragment extends Fragment {

    private static final String TAG = "TAG_ToolsFragment";

    ViewPager mToolsPager;
    ToolsPagerAdapter toolsPagerAdapter;
    TabLayout toolsTabLayout;
    TabItem tabSignUp, tabLogin;

    public ToolsFragment() {
        // Required empty public constructor
    }


    public static ToolsFragment newInstance() {
        ToolsFragment fragment = new ToolsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tools, container, false);
        findViews(view);
        getViewPagerReady();
        ViewPagerHelper.getTabOnClickAction(toolsTabLayout, mToolsPager);

        return view;
    }

    private void findViews(View v) {
        mToolsPager = v.findViewById(R.id.viewpager_tools_page);
        toolsTabLayout = v.findViewById(R.id.tablayout_tools);
    }

    private void getViewPagerReady() {
        toolsPagerAdapter = new ToolsPagerAdapter(getActivity()
                .getSupportFragmentManager(),
                2);
        mToolsPager.setAdapter(toolsPagerAdapter);
        mToolsPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(toolsTabLayout));
    }
}