package com.rezwan_cs.hscictlife.fragments.toolspage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.CodeEditorHelper;
import com.rezwan_cs.hscictlife.commons.CodeFromPicFragmentCommons;
import com.rezwan_cs.hscictlife.commons.CodeFromPicHelper;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class HTMLFragment extends CodeFromPicFragmentCommons implements View.OnClickListener{
    private static final String TAG = "TAG_HTMLFragment";

    WebView mOutput;

    Button mRunBtn, mCodeFromPicBtn, mHideItBtn;

    ProgressBar progressBar;

    public HTMLFragment() {
        // Required empty public constructor
    }


    public static HTMLFragment newInstance() {
        HTMLFragment fragment = new HTMLFragment();
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
        View view =  inflater.inflate(R.layout.fragment_h_t_m_l, container, false);

        findViews(view);
        setUpOnClickListeners();
        liveUpdateCodeLineNumber();
        mCodes.setText("<html>\n<body>\nYou scored <b>hello world</b> points.\n</body>\n</html>");
        //resetCodeLineNumberText();
        mCodeLineNumber.setText("1\n2\n3\n4\n5\n6");
        return view;
    }

    private void setUpOnClickListeners() {
        mHideItBtn.setOnClickListener(this);
        mCodeFromPicBtn.setOnClickListener(this);
        mRunBtn.setOnClickListener(this);
    }

    private void findViews(View view) {
        mCodeLineNumber = view.findViewById(R.id.tv_code_line_number);
        mCodes = view.findViewById(R.id.et_code);
        mOutput = view.findViewById(R.id.webview_html_code_output);
        mHideItBtn = view.findViewById(R.id.btn_hide);
        mCodeFromPicBtn = view.findViewById(R.id.btn_code_from_pic);
        mRunBtn = view.findViewById(R.id.btn_run);
        progressBar = view.findViewById(R.id.pb_code_in_progress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hide:
                doHideButton();
                break;
            case R.id.btn_code_from_pic:
                doCodeFromPicButton();
                break;
            case R.id.btn_run:
                doRunButton();
                break;
            default:
                break;
        }
    }

    private void doCodeFromPicButton() {
        Log.d(TAG, "button clicked");
        CropImage.activity().start(getActivity(), this);
    }

    private void doRunButton() {
        Log.d(TAG, "do RUN");
        String code = mCodes.getText().toString().trim();
        Log.d(TAG, code);
        code = code.replaceAll("\n", "");
        Log.d(TAG, code);
        WebSettings webSettings = mOutput.getSettings();
        webSettings.setJavaScriptEnabled(true);
        progressBar.setVisibility(View.VISIBLE);
        mOutput.loadData(code,"text/html", null);
        progressBar.setVisibility(View.GONE);
    }

    private void doHideButton() {
    }



}