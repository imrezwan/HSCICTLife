package com.rezwan_cs.hscictlife.fragments.mainpage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.activities.McqSetActivity;
import com.rezwan_cs.hscictlife.adapters.ChapterListAdapter;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.dialogs.StartQuizExamDialog;
import com.rezwan_cs.hscictlife.interfaces.ChapterClicked;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;
import com.rezwan_cs.hscictlife.utilities.ChapterNames;
import com.rezwan_cs.hscictlife.utilities.DefaultTransition;

import java.util.ArrayList;

public class QuizFragment extends Fragment {
    private static final String TAG = "TAG_QuizFragment";
    RecyclerView mChapterRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ChapterModel> chapterModelArrayList  = new ArrayList<>();
    ChapterListAdapter chapterListAdapter ;

    //
    Button mSelectQuizExam, mSelectQuizExercise, mStartQuizExam;
    String selected = Constants.EXCERCISE;
    ArrayList<Integer> chapterList = new ArrayList<>();

    public QuizFragment() {
        // Required empty public constructor
    }


    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
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
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        findViews(view);
        getChapterDataReady();
        setUpChapterRecyclerView();
        quizTypeButtonSelection();
        startQuizExamSection();
        defaultQuizTypeSelect();
        return view;
    }

    private void startQuizExamSection() {
        mStartQuizExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chapterList.size()>0){
                    StartQuizExamDialog.showStartQuizExamDialog(getContext(), chapterList);
                }
                else{
                    Toast.makeText(getContext(), Constants.PLEASE_SELECT_CHAPTER, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void defaultQuizTypeSelect() {
        mSelectQuizExercise.callOnClick();
    }

    private void quizTypeButtonSelection() {
        mSelectQuizExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(mSelectQuizExam);
                deSelectButton(mSelectQuizExercise);
                mStartQuizExam.setVisibility(View.VISIBLE);
                animateStartQuizExamButton();
                selected = Constants.EXAM;
            }
        });

        mSelectQuizExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(mSelectQuizExercise);
                deSelectButton(mSelectQuizExam);
                selected = Constants.EXCERCISE;
                mStartQuizExam.setVisibility(View.INVISIBLE);
                animateStartQuizExamButton();
                uncheckAllChapters();
                setUpChapterRecyclerView();
            }
        });
    }

    private void uncheckAllChapters() {
        for(int i = 0;i<chapterModelArrayList.size();i++){
            chapterModelArrayList.get(i).setChecked(false);
        }
    }

    private void animateStartQuizExamButton() {
        mStartQuizExam.animate().alpha(1.0f).setDuration(600);
    }

    private void setUpChapterRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(),
                3, RecyclerView.VERTICAL, false);
        chapterListAdapter = new ChapterListAdapter(getContext(),
                chapterModelArrayList, new ChapterClicked() {
            @Override
            public void onChapterClicked(int chapterNum, boolean checked) {

                if(selected.equals(Constants.EXAM)){
                    if(!checked){
                        chapterList.add(chapterNum);
                    }
                    else{
                        chapterList.remove((Integer) chapterNum);
                    }
                    chapterListAdapter.toggleChapterCheck(chapterNum-1);
                }
                else{
                    chapterList.clear();
                    //goto chapter set question activity
                    goToMcqSetActivity(chapterNum);
                }

                Log.d(TAG, chapterList.toString());
            }
        });
        mChapterRecycler.setAdapter(chapterListAdapter);
        mChapterRecycler.setLayoutManager(layoutManager);
    }

    private void goToMcqSetActivity(int chapterNum) {
        Intent intent = new Intent(getActivity(), McqSetActivity.class);
        intent.putExtra(Constants.EXTRA_CHAPTER_NUMBER, chapterNum);
        intent.putExtra(Constants.EXTRA_CHAPTER_MCQ_SETS_TOTAL, getChapterMcqSetTotal(chapterNum));
        startActivity(intent);
        DefaultTransition.activityTransition(getActivity());
    }

    public static int getChapterMcqSetTotal(int chapterNum) {
        switch (chapterNum){
            case 1:
                return 8;
            case 2:
                return 5;
            case 3:
                return 5;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 4;
            default:
                return 3;
        }
    }

    private void getChapterDataReady() {
        chapterModelArrayList.addAll(ChapterNames.getChapterModelList());
    }

    private void findViews(View view) {
        mChapterRecycler = view.findViewById(R.id.rv_chapter_name_list);
        mSelectQuizExam = view.findViewById(R.id.btn_select_quiz_exam);
        mSelectQuizExercise = view.findViewById(R.id.btn_select_quiz_exercise);
        mStartQuizExam = view.findViewById(R.id.btn_start_quiz_exam);
    }


    private void selectButton(Button button){
        button.setTextColor(getResources().getColor(R.color.colorTextWhite));
        button.setBackground(getResources().getDrawable(R.drawable.quiz_button_filled_bg));
    }

    private void deSelectButton(Button button){
        button.setTextColor(getResources().getColor(R.color.colorTextPrimary));
        button.setBackground(getResources().getDrawable(R.drawable.quiz_button_blank_bg));
    }
}