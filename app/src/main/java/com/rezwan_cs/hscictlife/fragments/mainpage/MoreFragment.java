package com.rezwan_cs.hscictlife.fragments.mainpage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.rezwan_cs.hscictlife.BuildConfig;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.activities.PrivacyActivity;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.dialogs.AboutDeveloperDiolog;


public class MoreFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "TAG_MoreFragment";
    Button mAboutAppDev, mPrivacyPolicy, mRateMyApp, mShareApp;

    public MoreFragment() {
        // Required empty public constructor
    }


    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
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
        View view =  inflater.inflate(R.layout.fragment_more, container, false);
        findViews(view);
        setUpLiseteners();

        return view;
    }

    private void setUpLiseteners() {
        mAboutAppDev.setOnClickListener(this);
        mPrivacyPolicy.setOnClickListener(this);
        mRateMyApp.setOnClickListener(this);
        mShareApp.setOnClickListener(this);
    }

    private void findViews(View view) {
        mAboutAppDev = view.findViewById(R.id.btn_about_app_developer);
        mPrivacyPolicy = view.findViewById(R.id.btn_privacy_policy);
        mRateMyApp = view.findViewById(R.id.btn_give_rating);
        mShareApp = view.findViewById(R.id.btn_share_app);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_about_app_developer:
                AboutDeveloperDiolog.showAboutDevDialog(getContext());
                break;
            case R.id.btn_privacy_policy:
                startActivity(new Intent(getContext(), PrivacyActivity.class));
                break;

            case R.id.btn_share_app:
                shareApp();
                break;
            case R.id.btn_give_rating:
                rateApp();
                break;
        }
    }

    private void shareApp(){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, Constants.MY_APP_NAME);
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" +
                    BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        catch(Exception e) {
            //e.toString();
        }
    }



    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url,
                getContext().getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }
}