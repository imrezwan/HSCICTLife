package com.rezwan_cs.hscictlife.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.adapters.ChapterListAdapter;
import com.rezwan_cs.hscictlife.adapters.McqSetAdapter;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;
import com.rezwan_cs.hscictlife.modelclasses.McqSetModel;

import java.util.ArrayList;

public class McqSetActivity extends AppCompatActivity {
    private static final String TAG = "TAG_McqSetActivity";

    RecyclerView mMcqSetRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<McqSetModel> mcqSetArrayList  = new ArrayList<>();
    McqSetAdapter mcqSetAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq_set);

        findViews();
        getMcqSetDataReady();
        setUpChapterRecyclerView();
    }

    private void setUpChapterRecyclerView() {
        mcqSetAdapter = new McqSetAdapter(this, mcqSetArrayList);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mMcqSetRecycler.setAdapter(mcqSetAdapter);
        mMcqSetRecycler.setLayoutManager(layoutManager);
    }

    private void getMcqSetDataReady() {
        mcqSetArrayList.add(new McqSetModel(30,7));
        mcqSetArrayList.add(new McqSetModel(30,30, 4));
        mcqSetArrayList.add(new McqSetModel(30,17));
        mcqSetArrayList.add(new McqSetModel(30,30, 2));
        mcqSetArrayList.add(new McqSetModel(30,0));
        mcqSetArrayList.add(new McqSetModel(30,11));
    }

    private void findViews() {
        mMcqSetRecycler = findViewById(R.id.rv_chapter_set_list);
    }
}