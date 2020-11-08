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
import com.rezwan_cs.hscictlife.adapters.McqStateAdapter;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.ProgressHelper;
import com.rezwan_cs.hscictlife.modelclasses.ExamMcqModel;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;
import com.rezwan_cs.hscictlife.modelclasses.McqState;
import com.rezwan_cs.hscictlife.modelclasses.PracticeMcqModel;

import java.util.ArrayList;

public class McqExamPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "TAG_McqExamPlayActivity";
    //mcq state variables
    RecyclerView mMcqStatesRecycler;
    McqStateAdapter mcqStateAdapter;
    LinearLayoutManager mcqStateLayoutManager;
    ArrayList<IMcqModel> allMcqArrayList = new ArrayList<>();

    //layout variables
    TextView mPracticeQuestionTxt, mExplanationTxt, mRemainingQuestionTxt;
    LinearLayout mOption1Linear, mOption2Linear,
            mOption3Linear, mOption4Linear;
    LinearLayout mNextQuestionSectionLinear;
    TextView mOption1Txt, mOption2Txt, mOption3Txt, mOption4Txt;
    TextView mOption1CircleMaker, mOption2CircleMaker, mOption3CircleMaker, mOption4CircleMaker;
    Button mNextQuestionBtn, mPrevQuestionBtn;

    //toolbar varibale
    Toolbar toolbar;
    TextView mToolbarTitleTxt;

    //Content container
    DrawerLayout mDrawerLayout;
    RecyclerView mMcqListRecyler;
    McqNavigationAdapter mcqNavigationAdapter;

    //mcq play live info variable
    long totalQuestion = 0, answeredQuestion = 0, currentPos = 0;
    ExamMcqModel currentQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_exam_play);
        findViews();
        setUpToolbar();
        readyMcqStateDataInitialization();
        setUpMcqStateRecycler();
        setDrawerLayout();
        totalQuestion = allMcqArrayList.size();
        setUpSingleQuestion(currentPos);
        setAllListeners();
    }

    private void setUpMcqStateRecycler() {
        mcqStateAdapter = new McqStateAdapter(this, allMcqArrayList, new McqStateAdapter.OnMcqStateClicked() {
            @Override
            public void mcqNumberClicked(int pos) {
                currentPos = pos;
                mcqStateAdapter.setOnScreenPosition(pos);
                setUpSingleQuestion(currentPos);
            }
        });
        mcqStateLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mMcqStatesRecycler.setLayoutManager(mcqStateLayoutManager);
        mMcqStatesRecycler.setAdapter(mcqStateAdapter);

    }

    private void scrollToCenter(int itemToScroll) {
        mMcqStatesRecycler.smoothScrollToPosition(itemToScroll);
    }

    private void readyMcqStateDataInitialization() {

        allMcqArrayList.add(new ExamMcqModel("1.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("2.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("3.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("4.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("5.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("6.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("7.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("8.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("9.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));
        allMcqArrayList.add(new ExamMcqModel("10.What's your name?", "Pranto",
                "Rka", "ridi", "ohao",
                1,1, 1));

    }

    private void findViews() {
        mMcqStatesRecycler = findViewById(R.id.rv_mcq_exam_question_state_list);

        mPracticeQuestionTxt = findViewById(R.id.tv_mcq_guide_play_question);

        mOption1Linear = findViewById(R.id.ll_mcq_guide_play_option_1);
        mOption2Linear = findViewById(R.id.ll_mcq_guide_play_option_2);
        mOption3Linear = findViewById(R.id.ll_mcq_guide_play_option_3);
        mOption4Linear = findViewById(R.id.ll_mcq_guide_play_option_4);

        mExplanationTxt = findViewById(R.id.tv_mcq_guide_play_explanation);

        mNextQuestionSectionLinear = findViewById(R.id.ll_mcq_play_guide_next_question_section);

        mRemainingQuestionTxt = findViewById(R.id.tv_mcq_guide_play_question_remaining);
        mNextQuestionBtn = findViewById(R.id.btn_mcq_exam_next_question);
        mPrevQuestionBtn = findViewById(R.id.btn_mcq_exam_prev_question);



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
        mToolbarTitleTxt.setText(Constants.MCQ_EXAM_TOOLBAR_TITLE);
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
                this, allMcqArrayList);
        mMcqListRecyler.setAdapter(mcqNavigationAdapter);
    }

    private void setUpSingleQuestion(long position) {
        setUpDefaultOptionTextColor();
        setUpDefaultOptionMarkerCircle();
        mcqStateAdapter.setOnScreenPosition((int)position);

        if(!allMcqArrayList.isEmpty()){
            currentQuestion = (ExamMcqModel) allMcqArrayList.get((int)position);
            setUpQuestionText();
            setUpAllOptionText();
        }
        else{
            Toast.makeText(this, "Question sesh", Toast.LENGTH_LONG).show();
        }
        //setUpRemainingQuestionCount();
        setUpQuestionTextChangeAnimation();
        clickabililityChecker();

    }

    private void setHowManyAnswered() {
        McqState.STATE state = currentQuestion.getMcqState().getState();
        if(state != McqState.STATE.UNANSWERED){
            answeredQuestion++;
        }
    }

    private void setUpRemainingQuestionCount() {
        String progressString = ProgressHelper
                .getMcqExamRemainingQuestionText(
                        totalQuestion-answeredQuestion+"");
        mRemainingQuestionTxt.setText(progressString);
    }

    private void clickabililityChecker() {
        if(currentQuestion.getMcqState().getState() == McqState.STATE.UNANSWERED){
            enableMcqOptionsClick();
        }
        else{
            disableMcqOptionsClick();
        }

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

    private void setUpAllOptionText() {
        mOption1Txt.setText(Html.fromHtml(currentQuestion.getOption1()));
        mOption2Txt.setText(Html.fromHtml(currentQuestion.getOption2()));
        mOption3Txt.setText(Html.fromHtml(currentQuestion.getOption3()));
        mOption4Txt.setText(Html.fromHtml(currentQuestion.getOption4()));
        setUpSingleOptionEverything(mOption1CircleMaker, mOption1Txt);
        setUpSingleOptionEverything(mOption2CircleMaker, mOption2Txt);
        setUpSingleOptionEverything(mOption3CircleMaker, mOption3Txt);
        setUpSingleOptionEverything(mOption4CircleMaker, mOption4Txt);
    }

    private void setUpSingleOptionEverything(TextView circleMaker, TextView optionText) {
        McqState.STATE currentState = currentQuestion.getMcqState().getState();

        if(currentState == McqState.STATE.CURRECT){
            setUpTheCurrectAnswerColor();
        }
        else if(currentState == McqState.STATE.WRONG){
            setUpTheCurrectAnswerColor();
            setUpTheWrongAnswerColor(currentQuestion.getAnswered());
        }
        else{
            circleMaker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_circle);
            circleMaker.setTextColor(Color.parseColor("#3E3939"));
            optionText.setTextColor(getResources().getColor(R.color.colorTextPrimary));
        }
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

    private void setAllListeners() {
        mOption1Linear.setOnClickListener(this);
        mOption2Linear.setOnClickListener(this);
        mOption3Linear.setOnClickListener(this);
        mOption4Linear.setOnClickListener(this);
        mNextQuestionBtn.setOnClickListener(this);
        mPrevQuestionBtn.setOnClickListener(this);
    }


    private void setUpSingleOptionCircle(TextView mOptionCircleMaker) {
        mOptionCircleMaker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_circle);
        mOptionCircleMaker.setTextColor(Color.parseColor("#3E3939"));
    }

    private void setUpSingleOptionTextColor(TextView mOptionTxt) {
        mOptionTxt.setTextColor(getResources().getColor(R.color.colorTextPrimary));
    }

    private void hideNextQuestionSection(){
        mNextQuestionSectionLinear.setVisibility(View.GONE);
    }


    private void slideAnimation(View mNextQuestionSectionLinear) {
        Animation animation = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        animation.setDuration(300);
        mNextQuestionSectionLinear.startAnimation(animation);
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

            case R.id.btn_mcq_exam_next_question:
                if(currentPos+1 < totalQuestion){
                    setUpSingleQuestion(++currentPos);
                    scrollToCenter((int)(currentPos+3<totalQuestion?(currentPos+3):currentPos));
                }
                else{
                    Toast.makeText(this, "End of Next questions", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_mcq_exam_prev_question:
                if(0 < currentPos){
                    setUpSingleQuestion(--currentPos);
                    scrollToCenter((int)(currentPos+3<totalQuestion?(currentPos+3):currentPos));
                }
                else{
                    Toast.makeText(this, "End of PREV questions", Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    private void valueChangeAccordingly(int i) {
        Log.d(TAG, "TOP: "+i);
        ExamMcqModel item = (ExamMcqModel)allMcqArrayList.get((int)currentPos);
        item.setAnswered(i);
        setHowManyAnswered();
        setUpRemainingQuestionCount();

        mcqStateAdapter.changeMcqState((int)currentPos,item.getMcqState());

        Log.d(TAG, "STATE: "+((ExamMcqModel)allMcqArrayList
                .get((int)currentPos)).getMcqState().getState()+"");

        if(i==1){
            setUpSingleOptionWrongColor(mOption1CircleMaker, mOption1Txt);
            Log.d(TAG, "Inside: "+i);
        }
        else if(i==2){
            setUpSingleOptionWrongColor(mOption2CircleMaker, mOption2Txt);
            Log.d(TAG, "Inside: "+i);
        }
        else if(i==3){
            setUpSingleOptionWrongColor(mOption3CircleMaker, mOption3Txt);
            Log.d(TAG, "Inside: "+i);
        }
        else if(i==4){
            setUpSingleOptionWrongColor(mOption4CircleMaker, mOption4Txt);
            Log.d(TAG, "Inside: "+i);
        }

        setUpTheCurrectAnswerColor();
        disableMcqOptionsClick();
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

    private void setUpTheWrongAnswerColor(long ans) {


        if(ans ==1){
            setUpSingleOptionWrongColor(mOption1CircleMaker,mOption1Txt);
            //Log.d(TAG, "Currect1: "+ mOption1Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
        else if(ans ==2){
            setUpSingleOptionWrongColor(mOption2CircleMaker,mOption2Txt);
            //Log.d(TAG, "Currect2: "+ mOption2Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
        else if(ans ==3){
            setUpSingleOptionWrongColor(mOption3CircleMaker,mOption3Txt);
            //Log.d(TAG, "Currect3: "+ mOption3Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }
        else if(ans ==4){
            setUpSingleOptionWrongColor(mOption4CircleMaker,mOption4Txt);
            //Log.d(TAG, "Currect4: "+ mOption4Txt.getText()+" --> "+ currentQuestion.getCorrectAnswer());
        }

    }

    private void setUpSingleOptionCurrectColor(TextView circleMaker, TextView optionText) {
        circleMaker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_correct);
        optionText.setTextColor(getResources().getColor(R.color.colorSweetGreen));
        circleMaker.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void setUpDefaultOptionMarkerCircle(){
        setUpSingleOptionCircle(mOption1CircleMaker);
        setUpSingleOptionCircle(mOption2CircleMaker);
        setUpSingleOptionCircle(mOption3CircleMaker);
        setUpSingleOptionCircle(mOption4CircleMaker);
    }


    private void setUpSingleOptionWrongColor(TextView optionMarker, TextView optionText) {
        optionMarker.setBackgroundResource(R.drawable.mcq_guide_play_option_round_selected);
        optionMarker.setTextColor(getResources().getColor(R.color.colorWhite));
        optionText.setTextColor(getResources().getColor(R.color.colorSweetRed));
    }

    private void setUpDefaultOptionTextColor(){
        setUpSingleOptionTextColor(mOption1Txt);
        setUpSingleOptionTextColor(mOption2Txt);
        setUpSingleOptionTextColor(mOption3Txt);
        setUpSingleOptionTextColor(mOption4Txt);
    }



}