package com.rezwan_cs.hscictlife.modelclasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class LastStudyResult {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "chapter_list")
    public String chapterList;

    @ColumnInfo(name = "correct_answer")
    long correctAnswer;

    @ColumnInfo(name = "wrong_answer")
    long wrongAnswer;

    @ColumnInfo(name = "total_questions")
    long totalQuestions;

    @ColumnInfo(name = "answered_questions")
    long answeredQuestions;

    @ColumnInfo(name = "time")
    String time;

    public LastStudyResult(String chapterList, long correctAnswer, long wrongAnswer, long totalQuestions, long answeredQuestions, String time) {
        this.chapterList = chapterList;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.totalQuestions = totalQuestions;
        this.answeredQuestions = answeredQuestions;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterList() {
        return chapterList;
    }

    public void setChapterList(String chapterList) {
        this.chapterList = chapterList;
    }

    public long getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(long correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public long getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(long wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(long totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public long getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(long answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Ignore
    @Override
    public String toString() {
        return "LastStudyResult{" +
                "id=" + id +
                ", chapterList='" + chapterList + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", wrongAnswer=" + wrongAnswer +
                ", totalQuestions=" + totalQuestions +
                ", answeredQuestions=" + answeredQuestions +
                ", time='" + time + '\'' +
                '}';
    }
}
