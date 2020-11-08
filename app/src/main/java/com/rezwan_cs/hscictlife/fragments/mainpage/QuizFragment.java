package com.rezwan_cs.hscictlife.fragments.mainpage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.adapters.ChapterListAdapter;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.interfaces.ChapterClicked;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;

import java.util.ArrayList;

public class QuizFragment extends Fragment {
    private static final String TAG = "TAG_QuizFragment";
    RecyclerView mChapterRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ChapterModel> chapterModelArrayList  = new ArrayList<>();
    ChapterListAdapter chapterListAdapter ;

    //
    Button mSelectQuizExam, mSelectQuizExercise;
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
        defaultQuizTypeSelect();
        return view;
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
                selected = Constants.EXAM;
            }
        });

        mSelectQuizExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(mSelectQuizExercise);
                deSelectButton(mSelectQuizExam);
                selected = Constants.EXCERCISE;
            }
        });
    }

    private void setUpChapterRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(),
                3, RecyclerView.VERTICAL, false);
        chapterListAdapter = new ChapterListAdapter(getContext(),
                chapterModelArrayList, new ChapterClicked() {
            @Override
            public void onChapterClicked(int chapterNum) {

            }
        });
        mChapterRecycler.setAdapter(chapterListAdapter);
        mChapterRecycler.setLayoutManager(layoutManager);
    }

    private void getChapterDataReady() {
        chapterModelArrayList.add(new ChapterModel("অধ্যায় ১",
                "বিশ্ব ও বাংলাদেশ প্রেক্ষিত", R.drawable.ic_chapter_1));
        chapterModelArrayList.add(new ChapterModel("অধ্যায় ২",
                "কমিউনিকেশন সিস্টেম ও নেটওয়ার্কিং", R.drawable.ic_chapter_2));
        chapterModelArrayList.add(new ChapterModel("অধ্যায় ৩",
                "সংখ্যাপদ্ধতি ও ডিজিটাল ডিভাইস", R.drawable.ic_chapter_3));
        chapterModelArrayList.add(new ChapterModel("অধ্যায় ৪",
                "ওয়েব ডিজাইন পরিচিতি ও HTML", R.drawable.ic_chapter_4));
        chapterModelArrayList.add(new ChapterModel("অধ্যায় ৫",
                "প্রোগামিং ভাষা", R.drawable.ic_chapter_5));
        chapterModelArrayList.add(new ChapterModel("অধ্যায় ৬",
                "ডেটাবেজ ম্যানেজমেন্ট সিস্টেম", R.drawable.ic_chapter_6));
    }

    private void findViews(View view) {
        mChapterRecycler = view.findViewById(R.id.rv_chapter_name_list);
        mSelectQuizExam = view.findViewById(R.id.btn_select_quiz_exam);
        mSelectQuizExercise = view.findViewById(R.id.btn_select_quiz_exercise);
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