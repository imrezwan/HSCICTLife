package com.rezwan_cs.hscictlife.commons;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rezwan_cs.hscictlife.R;

public class CodeEditorHelper {


    public static String getCodeLineCountText(int line) {
        String lineText = "";
        for (int i = 1;i<=line;i++){
            lineText += i+"\n";
        }
        return lineText;
    }



}
