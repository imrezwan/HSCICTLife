package com.rezwan_cs.hscictlife.utilities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rezwan_cs.hscictlife.modelclasses.LastStudyResult;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LastStudyResultDao {

    @Query("SELECT * FROM laststudyresult ORDER BY id DESC")
    List<LastStudyResult> getAll();

    @Insert
    void insertResult(LastStudyResult studyResult);

    @Insert
    void insertAll(LastStudyResult... studyResult);
}
