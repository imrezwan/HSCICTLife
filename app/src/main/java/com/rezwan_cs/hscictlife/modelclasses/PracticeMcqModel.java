package com.rezwan_cs.hscictlife.modelclasses;

import com.rezwan_cs.hscictlife.interfaces.IMcqModel;

public class PracticeMcqModel extends McqModel implements IMcqModel {
    long questionId;
    String explanation;

    public PracticeMcqModel(){}

    public PracticeMcqModel(String explanation) {
        this.explanation = explanation;
    }

    public PracticeMcqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset, String explanation) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        this.explanation = explanation;
    }

    public PracticeMcqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        this.explanation = "";
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
