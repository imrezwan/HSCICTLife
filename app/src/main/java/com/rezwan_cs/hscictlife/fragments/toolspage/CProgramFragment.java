package com.rezwan_cs.hscictlife.fragments.toolspage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.CodeEditorHelper;

public class CProgramFragment extends Fragment implements View.OnClickListener{
    private  static  final String TAG = "TAG_CProgramFragment";

    TextView mCodeLineNumber, mOutput;
    EditText mCodes;
    Button mRunBtn, mCodeFromPicBtn, mHideItBtn;



    public CProgramFragment() {
        // Required empty public constructor
    }


    public static CProgramFragment newInstance() {
        CProgramFragment fragment = new CProgramFragment();
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
        View view =  inflater.inflate(R.layout.fragment_c_program,
                container, false);

        findViews(view);

        liveUpdateCodeLineNumber();
        return view;
    }



    private void findViews(View view) {

        mOutput = view.findViewById(R.id.tv_cprogram_output);
        mCodeLineNumber = view.findViewById(R.id.tv_code_line_number);
        mCodes = view.findViewById(R.id.et_code);
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

    private void setUpOnClickListeners() {
        mHideItBtn.setOnClickListener(this);
        mCodeFromPicBtn.setOnClickListener(this);
        mRunBtn.setOnClickListener(this);
    }

    private void doCodeFromPicButton() {
    }

    private void doRunButton() {
    }

    private void doHideButton() {
    }

    public void liveUpdateCodeLineNumber() {
        mCodes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String lineCountText = CodeEditorHelper.getCodeLineCountText(
                        mCodes.getLineCount());
                mCodeLineNumber.setText(lineCountText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}