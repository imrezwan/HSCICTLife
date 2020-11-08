package com.rezwan_cs.hscictlife.modelclasses;

public class McqModel {
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    long correctanswer;
    long chapter;
    long questionset;

    public McqModel(){}


    public McqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctanswer = correctanswer;
        this.chapter = chapter;
        this.questionset = questionset;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public long getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(long correctanswer) {
        this.correctanswer = correctanswer;
    }

    public long getChapter() {
        return chapter;
    }

    public void setChapter(long chapter) {
        this.chapter = chapter;
    }

    public long getQuestionset() {
        return questionset;
    }

    public void setQuestionset(long questionset) {
        this.questionset = questionset;
    }
}
