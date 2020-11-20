package com.rezwan_cs.hscictlife.modelclasses;

import com.google.firebase.firestore.Exclude;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;

public class PracticeMcqModel extends McqModel implements IMcqModel {
    @Exclude
    long questionId;

    public PracticeMcqModel(){}

/*
    public PracticeMcqModel(String explanation) {
        this.explanation = explanation;
    }
*/

    public PracticeMcqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset, String explanation) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        //this.explanation = explanation;
    }

    public PracticeMcqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
       // this.explanation = "";
    }

    @Exclude
    public long getQuestionId() {
        return questionId;
    }

    @Exclude
    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    /*public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }*/
}
