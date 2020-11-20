package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.adapters.ChapterListAdapter;
import com.rezwan_cs.hscictlife.adapters.McqSetAdapter;
import com.rezwan_cs.hscictlife.adapters.McqStateAdapter;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;
import com.rezwan_cs.hscictlife.modelclasses.McqSetModel;
import com.rezwan_cs.hscictlife.utilities.ChapterNames;

import java.util.ArrayList;

public class McqSetActivity extends AppCompatActivity {
    private static final String TAG = "TAG_McqSetActivity";

    RecyclerView mMcqSetRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<McqSetModel> mcqSetArrayList  = new ArrayList<>();
    McqSetAdapter mcqSetAdapter ;
    int chapterNumber, chapterMcqSetTotal , mcqSetNumberClicked = 1;
    TextView mChapterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_set);
        findViews();
        retriveDataFromPrevious();
        setChapterTitle();
        getMcqSetDataReady();
        setUpChapterRecyclerView();
    }

    private void setChapterTitle() {
        mChapterName.setText(ChapterNames.getChapterModelList().get(chapterNumber-1).getChapterName());
    }

    private void retriveDataFromPrevious() {
        chapterNumber = getIntent()!=null?getIntent().getIntExtra(Constants.EXTRA_CHAPTER_NUMBER,1):1;
        chapterMcqSetTotal = getIntent()!=null?getIntent().getIntExtra(Constants.EXTRA_CHAPTER_MCQ_SETS_TOTAL,3):3;
        Log.d(TAG, chapterNumber+"  "+chapterMcqSetTotal);
    }

    private void setUpChapterRecyclerView() {
        mcqSetAdapter = new McqSetAdapter(this, mcqSetArrayList, chapterMcqSetTotal,
                new McqSetAdapter.IOnMcqSetClicked() {
            @Override
            public void onMcqSetClicked(int mcqSetNumber) {
                goToMcqPracticePlay(mcqSetNumber);
            }
        });
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mMcqSetRecycler.setAdapter(mcqSetAdapter);
        mMcqSetRecycler.setLayoutManager(layoutManager);
    }

    private void goToMcqPracticePlay(int mcqSetNumber) {
        Intent intent = new Intent(this, McqPracticePlayActivity.class);
        intent.putExtra(Constants.EXTRA_MCQ_SET_NUMBER, mcqSetNumber);
        intent.putExtra(Constants.EXTRA_CHAPTER_NUMBER, chapterNumber);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void getMcqSetDataReady() {
        mcqSetArrayList.add(new McqSetModel(30,7));
        mcqSetArrayList.add(new McqSetModel(30,30, 4));
        mcqSetArrayList.add(new McqSetModel(30,17));

    }

    private void findViews() {
        mMcqSetRecycler = findViewById(R.id.rv_chapter_set_list);
        mChapterName = findViewById(R.id.tv_chapter_name_title);

        ((TextView)findViewById(R.id.tv_back_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}