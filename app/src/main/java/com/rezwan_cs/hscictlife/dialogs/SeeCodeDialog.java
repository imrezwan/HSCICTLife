package com.rezwan_cs.hscictlife.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;
import com.rezwan_cs.hscictlife.modelclasses.CCode;

public class SeeCodeDialog extends Dialog {
    Context context;
    CCode cCode;

    TextView mCodeSerial, mCodes, mInputs, mOutputs;

    public SeeCodeDialog(@NonNull Context context, CCode cCode) {
        super(context);
        this.context = context;
        this.cCode = cCode;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_code_layout);

        mCodes = findViewById(R.id.tv_code_snippet);
        mCodeSerial  = findViewById(R.id.tv_code_serial);

        mInputs = findViewById(R.id.tv_code_input);
        mOutputs = findViewById(R.id.tv_code_output);

        mCodeSerial.setText("কোড "+ LanguageChangeHelper.englishToBanglaNumber(cCode.getId()+""));

        mCodes.setText(cCode.getCodes());
        mInputs.setText(cCode.getInputs());
        mOutputs.setText(cCode.getOutputs());
    }
}
