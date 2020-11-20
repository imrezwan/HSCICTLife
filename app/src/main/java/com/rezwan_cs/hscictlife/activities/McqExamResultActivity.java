package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.LanguageChangeHelper;

import java.util.ArrayList;

public class McqExamResultActivity extends AppCompatActivity {

    ArrayList<Integer> examChapterList = new ArrayList<>();
    long totalQuestion = 0, answeredQuestion = 0,  currectAnswer = 0;
    String timeTxt = "";

    //
    TextView mChapterListTxt, mTimeTxt , mCurrectAnswerTxt, mAnsweredTxt, mWrongAnswerTxt;

    Button mHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_exam_result);
        retriveDataFromPrevious();
        findViews();
        setUpText();

        mHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(McqExamResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpText() {
        //setup chapter name
        String chapterList = "<b>পরীক্ষা :</b>";
        for(int i=0;i<examChapterList.size();i++){
            chapterList += " অধ্যায় "+ LanguageChangeHelper
                    .englishToBanglaNumber(examChapterList.get(i)+"")+" ,";
        }
        chapterList = chapterList.substring(0, chapterList.length()-1);
        mChapterListTxt.setText(Html.fromHtml(chapterList));
        mTimeTxt.setText("সময়ঃ "+timeTxt+" মিনিট");
        mCurrectAnswerTxt.setText("সঠিকঃ "+currectAnswer);
        mWrongAnswerTxt.setText("ভুলঃ "+(answeredQuestion-currectAnswer));
        mAnsweredTxt.setText("উত্তর করেছেনঃ "+answeredQuestion+" / "+totalQuestion);

    }

    private void findViews() {
        mChapterListTxt= findViewById(R.id.tv_mcq_exam_result_chapter_name);
        mTimeTxt = findViewById(R.id.tv_mcq_exam_result_time);
        mCurrectAnswerTxt = findViewById(R.id.tv_mcq_exam_result_currect);
        mWrongAnswerTxt = findViewById(R.id.tv_mcq_exam_result_wrong);
        mAnsweredTxt = findViewById(R.id.tv_mcq_exam_result_answered_question);
        mHomePage = findViewById(R.id.btn_mcq_exam_result_home_page);
    }

    private void retriveDataFromPrevious() {
        if(getIntent()!=null){
            examChapterList = getIntent().getIntegerArrayListExtra(Constants.EXTRA_CHAPTER_NUMBER_LIST);
            timeTxt = getIntent().getStringExtra(Constants.EXTRA_EXAM_MCQ_TIME);
            totalQuestion = getIntent().getLongExtra(Constants.EXTRA_EXAM_MCQ_TOTAL, 10);
            currectAnswer = getIntent().getLongExtra(Constants.EXTRA_EXAM_CURRECT_ANSWER, 10);
            answeredQuestion = getIntent().getLongExtra(Constants.EXTRA_EXAM_TOTAL_ANSWERED, 10);
        }
    }
}