package com.rezwan_cs.hscictlife.fragments.mainpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rezwan_cs.hscictlife.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingFragment extends Fragment {
    private static final String TAG = "TAG_RankingFragment";



    public RankingFragment() {
        // Required empty public constructor
    }


    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
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
        View view =  inflater.inflate(R.layout.fragment_ranking, container, false);
        findViews(view);
        return view;
    }

    private void findViews(View view) {

    }
}