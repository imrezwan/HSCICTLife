package com.rezwan_cs.hscictlife.utilities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rezwan_cs.hscictlife.modelclasses.CCode;

import java.util.List;

@Dao
public interface CCodeDao {
    @Query("SELECT * FROM ccode ORDER BY id DESC")
    List<CCode> getAll();

    @Insert
    void insertResult(CCode cCode);

    @Insert
    void insertAll(CCode... cCode);
}
