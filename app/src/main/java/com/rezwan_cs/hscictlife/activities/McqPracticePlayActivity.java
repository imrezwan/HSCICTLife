package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.adapters.McqNavigationAdapter;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.ProgressHelper;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;
import com.rezwan_cs.hscictlife.modelclasses.PracticeMcqModel;

import java.util.ArrayList;

public class McqPracticePlayActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "TAG_McqPracticePlay";

    ArrayList<IMcqModel> allQuestionArrayList = new ArrayList<>();
    ArrayList<IMcqModel> newQuestionArrayList = new ArrayList<>();
    ArrayList<IMcqModel> failedquestionArrayList = new ArrayList<>();

    //layout variables
    TextView mNewQuestionOrNotFlagTxt, mPracticePlayInstructionTxt,
            mPracticeQuestionTxt, mExplanationTxt, mRemainingQuestionTxt;
    LinearLayout mMcqPracticeTopPartLinear, mOption1Linear, mOption2Linear,
            mOption3Linear, mOption4Linear;
    LinearLayout mNextQuestionSectionLinear;
    TextView mOption1Txt, mOption2Txt, mOption3Txt, mOption4Txt;
    TextView mOption1CircleMaker, mOption2CircleMaker, mOption3CircleMaker, mOption4CircleMaker;
    Button mNextQuestionBtn;

    //toolbar varibale
    Toolbar toolbar;
    TextView mToolbarTitleTxt;

    //Content container
    DrawerLayout mDrawerLayout;
    RecyclerView mMcqListRecyler;
    McqNavigationAdapter mcqNavigationAdapter;

    //mcq play live info variable
    long totalQuestion = 0, answeredQuestion = 0;
    PracticeMcqModel currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_play);
        findViews();
        setUpToolbar();
        getMcqDataReady();

        totalQuestion = allQuestionArrayList.size();
        newQuestionArrayList.addAll(allQuestionArrayList);
        setDrawerLayout();
        setUpNextQuestion();
        setAllListeners();

    }

    private void setAllListeners() {
        mOption1Linear.setOnClickListener(this);
        mOption2Linear.setOnClickListener(this);
        mOption3Linear.setOnClickListener(this);
        mOption4Linear.setOnClickListener(this);
        mNextQuestionBtn.setOnClickListener(this);
    }

    private void getMcqDataReady() {
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allQuestionArrayList.add(new PracticeMcqModel("What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));


    }

    private void findViews() {
        mNewQuestionOrNotFlagTxt = findViewById(R.id.tv_mcq_practice_play_new_question_or_not);
        mPracticePlayInstructionTxt = findViewById(R.id.tv_mcq_practice_play_instructions);
        mPracticeQuestionTxt = findViewById(R.id.tv_mcq_practice_play_question);

        mMcqPracticeTopPartLinear = findViewById(R.id.ll_mcq_guide_play_top_part);
        mOption1Linear = findViewById(R.id.ll_mcq_guide_play_option_1);
        mOption2Linear = findViewById(R.id.ll_mcq_guide_play_option_2);
        mOption3Linear = findViewById(R.id.ll_mcq_guide_play_option_3);
        mOption4Linear = findViewById(R.id.ll_mcq_guide_play_option_4);

        mExplanationTxt = findViewById(R.id.tv_mcq_guide_play_explanation);

        mNextQuestionSectionLinear = findViewById(R.id.ll_mcq_play_guide_next_question_section);

        mRemainingQuestionTxt = findViewById(R.id.tv_mcq_guide_play_question_remaining);
        mNextQuestionBtn = findViewById(R.id.btn_mcq_guide_play_next_question);



        //toolbar find
        toolbar = findViewById(R.id.toolbar);
        mToolbarTitleTxt = findViewById(R.id.tv_subject_activity_toolbar_title);

        //drawer and content
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mMcqListRecyler = findViewById(R.id.recycler_mcq_list);

        //option childs find
        mOption1CircleMaker = (TextView) mOption1Linear.getChildAt(0);
        mOption2CircleMaker = (TextView) mOption2Linear.getChildAt(0);
        mOption3CircleMaker = (TextView) mOption3Linear.getChildAt(0);
        mOption4CircleMaker = (TextView) mOption4Linear.getChildAt(0);

        mOption1Txt= (TextView) mOption1Linear.getChildAt(1);
        mOption2Txt= (TextView) mOption2Linear.getChildAt(1);
        mOption3Txt= (TextView) mOption3Linear.getChildAt(1);
        mOption4Txt= (TextView) mOption4Linear.getChildAt(1);
    }

    private void setUpToolbar() {
        toolbar.setTitle("");
        mToolbarTitleTxt.setText(Constants.MCQ_PRACTICE_TOOLBAR_TITLE);
        setSupportActionBar(toolbar);
    }

    private void setDrawerLayout() {

        setUpdrawerRecycler();

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,mDrawerLayout, toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_new_hambuger_nav);
        mDrawerToggle.syncState();

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void setUpdrawerRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMcqListRecyler.setLayoutManager(layoutManager);
        mcqNavigationAdapter = new McqNavigationAdapter(
                this, allQuestionArrayList);
        mMcqListRecyler.setAdapter(mcqNavigationAdapter);
    }

    private void setUpNextQuestion() {
        enableMcqOptionsClick();
        setUpDefaultOptionMarkerCircle();
        setUpDefaultOptionTextColor();
        hideNextQuestionSection();
        hideExplanation();

        if(!newQuestionArrayList.isEmpty()){
            currentQuestion = (PracticeMcqModel) newQuestionArrayList.get(0);
            setUpQuestionText();
            setUpAllOptionText();
            ///toper part
            setUpNewQuestionTopInstructionSection();

        }
        else if(!failedquestionArrayList.isEmpty()) {
            currentQuestion = (PracticeMcqModel) failedquestionArrayList.get(0);
            setUpQuestionText();
            setUpAllOptionText();
            ///toper part
            setUpOldQuestionTopInstructionSection();
        }
        else{
            Toast.makeText(this, "Question sesh", Toast.LENGTH_LONG).show();
        }
        setUpRemainingQuestionCount();
        setUpQuestionTextChangeAnimation();
    }

    private void setUpRemainingQuestionCount() {
        String remainingValue = newQuestionArrayList.size()+failedquestionArrayList.size()+"";
        String progressString = ProgressHelper.getMcqPlayRemainingQuestionText(remainingValue);
        mRemainingQuestionTxt.setText(progressString);
    }


    private void setUpQuestionTextChangeAnimation() {
        slideAnimation(mPracticeQuestionTxt);
        slideAnimation(mOption1Txt);
        slideAnimation(mOption2Txt);
        slideAnimation(mOption3Txt);
        slideAnimation(mOption4Txt);
        slideAnimation(mOption1CircleMaker);
        slideAnimation(mOption2CircleMaker);
        slideAnimation(mOption3CircleMaker);
        slideAnimation(mOption4CircleMaker);
    }

    private void setUpQuestionText() {
        mPracticeQuestionTxt.setText(Html.fromHtml(
                currentQuestion.getQuestion()));
    }

    private void setUpOldQuestionTopInstructionSection() {
        mPracticePlayInstructionTxt.setText(Constants.PREVIOUSLY_WRONG_ANSWERED_INSTRUCTION);
        mNewQuestionOrNotFlagTxt.setText(Constants.EXAM_AGAIN);
        setUpRepeatedQuestionTopSectionColor();

    }

    private void setUpRepeatedQuestionTopSectionColor() {
        mMcqPracticeTopPartLinear.setBackgroundColor(getResources().getColor(R.color.colorRepeatedQuestionInstructionBackground));
        mPracticePlayInstructionTxt.setTextColor(getResources().getColor(R.color.colorRepeatedQuestionInstructionTopperText));
        mNewQuestionOrNotFlagTxt.setTextColor(getResources().getColor(R.color.colorRepeatedQuestionInstructionTopperText));
    }

    private void setUpNewQuestionTopInstructionSection() {
        mPracticePlayInstructionTxt.setText(Constants.PICK_CORRECT_ANSWER_INSTRUCTION);
        mNewQuestionOrNotFlagTxt.setText(Constants.NEW_QUESTION_INFO_FLAG);
        setUpNewQuestionTopSectionColor();
    }

    private void setUpNewQuestionTopSectionColor() {

        mPracticePlayInstructionTxt.setTextColor(getResources().getColor(R.color.colorNewQuestionInstructionTopperText));
        mNewQuestionOrNotFlagTxt.setTextColor(getResources().getColor(R.color.colorNewQuestionInstructionTopperText));
        mMcqPracticeTopPartLinear.setBackgroundColor(getResources().getColor(R.color.colorNewQuestionInstructionBackground));
    }

    private void setUpAllOptionText() {
        mOption1Txt.setText(Html.fromHtml(currentQuestion.getOption1()));
        mOption2Txt.setText(Html.fromHtml(currentQuestion.getOption2()));
        mOption3Txt.setText(Html.fromHtml(currentQuestion.getOption3()) );
        mOption4Txt.setText(Html.fromHtml(currentQuestion.getOption4()));
    }

    private void disableMcqOptionsClick(){
        mOption1Linear.setClickable(false);
        mOption2Linear.setClickable(false);
        mOption3Linear.setClickable(false);
        mOption4Linear.setClickable(false);
    }

    private void enableMcqOptionsClick(){
        mOption1Linear.setClickable(true);
        mOption2Linear.setClickable(true);
        mOption3Linear.setClickable(true);
        mOption4Linear.setClickable(true);
    }

    private void setUpDefaultOptionMarkerCircle(){
        setUpSingleOptionCircle(mOption1CircleMaker);
        setUpSingleOptionCircle(mOption2CircleMaker);
        setUpSingleOptionCircle(mOption3CircleMaker);
        setUpSingleOptionCircle(mOption4CircleMaker);
    }

    private void setUpSingleOptionCircle(TextView mOptionCircleMaker) {
        mOptionCircleMaker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_circle);
        mOptionCircleMaker.setTextColor(Color.parseColor("#3E3939"));
    }

    private void setUpDefaultOptionTextColor(){
        setUpSingleOptionTextColor(mOption1Txt);
        setUpSingleOptionTextColor(mOption2Txt);
        setUpSingleOptionTextColor(mOption3Txt);
        setUpSingleOptionTextColor(mOption4Txt);
    }

    private void setUpSingleOptionTextColor(TextView mOptionTxt) {
        mOptionTxt.setTextColor(getResources().getColor(R.color.colorTextPrimary));
    }

    private void hideNextQuestionSection(){
        mNextQuestionSectionLinear.setVisibility(View.GONE);
    }

    private void showNextQuestionButton(){
        mNextQuestionSectionLinear.setVisibility(View.VISIBLE);
        slideAnimation(mNextQuestionSectionLinear);
    }

    private void slideAnimation(View mNextQuestionSectionLinear) {
        Animation animation = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        animation.setDuration(300);
        mNextQuestionSectionLinear.startAnimation(animation);
    }


    private void showExplanation() {
        if(!currentQuestion.getExplanation().isEmpty()){
            mExplanationTxt.setVisibility(View.VISIBLE);
            mExplanationTxt.setText("ব্যাখ্যাঃ "+currentQuestion.getExplanation());
        }
    }

    private void hideExplanation(){
        mExplanationTxt.setVisibility(View.GONE);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_mcq_guide_play_option_1:
                valueChangeAccordingly(1);
                break;
            case R.id.ll_mcq_guide_play_option_2:
                valueChangeAccordingly(2);
                break;
            case R.id.ll_mcq_guide_play_option_3:
                valueChangeAccordingly(3);
                break;

            case R.id.ll_mcq_guide_play_option_4:
                valueChangeAccordingly(4);
                break;

            case R.id.btn_mcq_guide_play_next_question:
                hideNextQuestionSection();
                if(totalQuestion != answeredQuestion){
                    setUpNextQuestion();
                }
                break;

            default:
                break;
        }
    }

    private void valueChangeAccordingly(int i) {
        long selectedAnswer = 1;
        Log.d(TAG, "TOP: "+i);
        showExplanation();
        if(i==1){
            selectedAnswer = 1;
            setUpSingleOptionMarkerWrongInitially(mOption1CircleMaker, mOption1Txt);
            Log.d(TAG, "Inside: "+i);
        }
        else if(i==2){
            selectedAnswer = 2;
            setUpSingleOptionMarkerWrongInitially(mOption2CircleMaker, mOption2Txt);
            Log.d(TAG, "Inside: "+i);
        }
        else if(i==3){
            selectedAnswer = 3;
            setUpSingleOptionMarkerWrongInitially(mOption3CircleMaker, mOption3Txt);
            Log.d(TAG, "Inside: "+i);
        }
        else if(i==4){
            selectedAnswer = 4;
            setUpSingleOptionMarkerWrongInitially(mOption4CircleMaker, mOption4Txt);
            Log.d(TAG, "Inside: "+i);
        }

        setUpTheCurrectAnswerColor();

        makeMcqDataCircularDependingOnAnswerIsCurrectOrNot(selectedAnswer);


        showExplanation();
        showNextQuestionButton();
        disableMcqOptionsClick();
    }

    private void makeMcqDataCircularDependingOnAnswerIsCurrectOrNot(long selectedAnswer) {
        if(currentQuestion.getCorrectanswer() == selectedAnswer){
           /* Log.d(TAG, "Question array befor: "+ questionArrayList.size() + "  >> "
            + questionArrayList.get(0).getQuestion());*/

            //answered question increased by 1 beacuse this answer is correct
            answeredQuestion++;

            if(newQuestionArrayList.indexOf(currentQuestion) == -1){
                ////not exist in question array list means this question from failed array list
                failedquestionArrayList.remove(currentQuestion);
            }
            else{
                //exist in question array list means it shows up for the first time on screen
                newQuestionArrayList.remove(currentQuestion);
            }

            /*Log.d(TAG, "Question array after: "+ questionArrayList.size() + "  >> "
                    + questionArrayList.get(0).getQuestion());*/
            mPracticePlayInstructionTxt.setText(Constants.THIS_QUESTION_WONT_BE_ASKED_AGAIN);
            mNewQuestionOrNotFlagTxt.setText(Constants.CORRECT_ANSWER);
            setUpNewQuestionTopSectionColor();

        }
        else{
            //Answer not correct section

            if(failedquestionArrayList.indexOf(currentQuestion) == -1){
                ////not exist in failed question array list means this question shows for the first time
                newQuestionArrayList.remove(currentQuestion);
                failedquestionArrayList.add(currentQuestion);
            }
            else{
                //exist in failed question array list means this failed question have to be repeat later
                //so just simply remove this item and add again for asking later again
                failedquestionArrayList.remove(currentQuestion);
                failedquestionArrayList.add(currentQuestion);
            }
            mPracticePlayInstructionTxt.setText(Constants.THIS_QUESTION_WILL_BE_ASKED_AGAIN);
            mNewQuestionOrNotFlagTxt.setText(Constants.WRONG_ANSWER);
            setUpOldQuestionTopInstructionSection();
        }
    }

    private void setUpTheCurrectAnswerColor() {
        long ans = currentQuestion.getCorrectanswer();

        if(ans ==1){
            setUpSingleOptionCurrectColor(mOption1CircleMaker,mOption1Txt);
            //Log.d(TAG, "Currect1: "+ mOption1Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
        else if(ans ==2){
            setUpSingleOptionCurrectColor(mOption2CircleMaker,mOption2Txt);
            //Log.d(TAG, "Currect2: "+ mOption2Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
        else if(ans ==3){
            setUpSingleOptionCurrectColor(mOption3CircleMaker,mOption3Txt);
            //Log.d(TAG, "Currect3: "+ mOption3Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
        else if(ans ==4){
            setUpSingleOptionCurrectColor(mOption4CircleMaker,mOption4Txt);
            //Log.d(TAG, "Currect4: "+ mOption4Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
    }

    private void setUpSingleOptionCurrectColor(TextView circleMaker, TextView optionText) {
        circleMaker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_correct);
        optionText.setTextColor(getResources().getColor(R.color.colorSweetGreen));
        circleMaker.setTextColor(getResources().getColor(R.color.colorWhite));
    }


    private void setUpSingleOptionMarkerWrongInitially(TextView optionMarker, TextView optionText) {
        optionMarker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_selected);
        optionMarker.setTextColor(getResources().getColor(R.color.colorWhite));
        optionText.setTextColor(getResources().getColor(R.color.colorSweetRed));
    }


}

