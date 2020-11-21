package com.rezwan_cs.hscictlife.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.activities.McqExamPlayActivity;
import com.rezwan_cs.hscictlife.activities.McqPracticePlayActivity;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;

import java.util.ArrayList;

public class StartQuizExamDialog {

    private static String TAG = "TAG_StartQuizExamDialog";
    private static EditText mExamTime, mExamUserTotal;
    private static TextView mExamRealTotal, mExamChapterList;
    private static Button mStartExam;
    Context context;

    public static void showStartQuizExamDialog(final Context context, final ArrayList<Integer> arrayList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_start_quiz_exam, null);
        findViews(view);
        Dialog dialog = builder.setView(view).create();

        dialog.setCancelable(true);

        //setup chapter name
        String chapterList = "<b>পরীক্ষা :</b>";
        for(int i=0;i<arrayList.size();i++){
            chapterList += " অধ্যায় "+ LanguageChangeHelper.englishToBanglaNumber(arrayList.get(i)+"")+" ,";
        }
        chapterList = chapterList.substring(0, chapterList.length()-1);
        mExamChapterList.setText(Html.fromHtml(chapterList));

        //setup real total
        String realTotal = "<b>সর্বমোট প্রশ্ন আছে :</b> "+ arrayList.size()* Constants.PER_CHAPTER_QUESTIONS + " টি";
        mExamRealTotal.setText(Html.fromHtml(realTotal));

        builder.setView(view);
        Log.d(TAG,"Created");


        mExamUserTotal.setSelection(mExamUserTotal.getText().length());
        mExamTime.setSelection(mExamTime.getText().length());
        dialog.show();

        mStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mcqTotal = mExamUserTotal.getText().toString().trim();
                String mcqTime = mExamTime.getText().toString().trim();
                try{
                    if(mcqTime.isEmpty() || mcqTotal.isEmpty()){
                        Toast.makeText(context, "অনুগ্রহ করে সময় ও MCQ সংখ্যা নির্ধারণ করুন",Toast.LENGTH_SHORT).show();
                    }
                    else if(Integer.valueOf(mcqTotal)>(arrayList.size()*100)){
                        mExamUserTotal.setText((arrayList.size()*100)+"");
                    }
                    else{
                        dialog.dismiss();
                        Intent intent = new Intent(context, McqExamPlayActivity.class);
                        intent.putExtra(Constants.EXTRA_EXAM_MCQ_TOTAL, Integer.valueOf(mcqTotal));
                        intent.putExtra(Constants.EXTRA_EXAM_MCQ_TIME, Integer.valueOf(mcqTime));
                        intent.putExtra(Constants.EXTRA_CHAPTER_NUMBER_LIST, arrayList);
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    Toast.makeText(context, "অনুগ্রহ করে সময় ও MCQ সংখ্যাতে নাম্বার প্রবেশ করান", Toast.LENGTH_SHORT).show();
                }

                //context.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

    }


    private static void findViews(View view) {
        mExamRealTotal  = view.findViewById(R.id.tv_start_exam_dialog_real_total);
        mExamTime = view.findViewById(R.id.et_start_quiz_exam_user_time);
        mExamUserTotal = view.findViewById(R.id.et_start_quiz_exam_user_total);
        mExamChapterList = view.findViewById(R.id.tv_what_are_the_selected_chapters);
        mStartExam = view.findViewById(R.id.btn_start_quiz_exam_dialog_start);

    }
}
