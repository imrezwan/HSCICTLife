package com.rezwan_cs.hscictlife.fragments.toolspage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.CodeEditorHelper;
import com.rezwan_cs.hscictlife.commons.CodeFromPicFragmentCommons;
import com.rezwan_cs.hscictlife.modelclasses.CompilerFinal;
import com.rezwan_cs.hscictlife.repositories.CompilerRepository;
import com.theartofdev.edmodo.cropper.CropImage;

public class CProgramFragment extends CodeFromPicFragmentCommons implements View.OnClickListener{
    private  static  final String TAG = "TAG_CProgramFragment";

    TextView mOutput;

    Button mRunBtn, mCodeFromPicBtn, mHideItBtn;

    ProgressBar progressBar;

    EditText mInput;

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

        setDefaultCode();
        setUpOnClickListeners();

        return view;
    }

    private void setDefaultCode() {
        mCodes.setText("#include<stdio.h>\n" +
                "\n" +
                        "int main() {\n" +
                        "    int i = 0;\n" +
                        "    for (i=0;i<10;i++){\n" +
                        "      printf(\"%d\\n\", i);\n" +
                        "    }\n" +
                        "    return 0;\n" +
                        "}");
        mCodeLineNumber.setText("1\n2\n3\n4\n5\n6\n7\n8\n9");
    }


    private void findViews(View view) {
        mHideItBtn = view.findViewById(R.id.btn_hide);
        mOutput = view.findViewById(R.id.tv_cprogram_output);
        mCodeLineNumber = view.findViewById(R.id.tv_code_line_number);
        mCodes = view.findViewById(R.id.et_code);
        mCodeFromPicBtn = view.findViewById(R.id.btn_code_from_pic);
        mRunBtn = view.findViewById(R.id.btn_run);
        mInput = view.findViewById(R.id.et_cprogram_input);

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

    private void setUpOnClickListeners() {
        mHideItBtn.setOnClickListener(this);
        mCodeFromPicBtn.setOnClickListener(this);
        mRunBtn.setOnClickListener(this);
    }

    private void doCodeFromPicButton() {
        CropImage.activity().start(getContext(), this);
    }

    private void doRunButton() {
        CompilerRepository compilerRepository = new CompilerRepository(new CompilerRepository.OnCompilerRepository() {
            @Override
            public void onFinalCompilerResult(CompilerFinal compilerFinal) {
                if(compilerFinal.getStatus().equals("IN-QUEUE")){
                    doRunButton();
                }
                //Log.d("Compiler Out:: ", compilerFinal.getOutput());
                mOutput.setText(compilerFinal.getOutput()!=null?compilerFinal.getOutput().replace("\\n", "\n"):"Error");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        Log.d("CC", mCodes.getText().toString().trim());
        progressBar.setVisibility(View.VISIBLE);
        compilerRepository.getFinalCompilerResult(mCodes.getText().toString().trim(),
                mInput.getText().toString().trim());
    }

    private void doHideButton() {
    }







}