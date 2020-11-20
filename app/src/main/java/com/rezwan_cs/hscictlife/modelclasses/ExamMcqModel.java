package com.rezwan_cs.hscictlife.modelclasses;

import android.util.Log;

import com.google.firebase.firestore.Exclude;
import com.rezwan_cs.hscictlife.interfaces.IMcqModel;

import java.util.Map;

public class ExamMcqModel extends McqModel implements IMcqModel {
    @Exclude
    long answered;
    @Exclude
    STATE mcqState;

    public enum STATE  {
        CURRECT, WRONG, UNANSWERED
    }

    public ExamMcqModel(){}

    public ExamMcqModel(long answered) {
        this.answered = answered;
    }

    public ExamMcqModel(String question, String option1, String option2, String option3, String option4,
                        long correctanswer, long chapter, long questionset, long answered) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        this.answered = answered;
        mcqState = STATE.UNANSWERED;
    }

    public ExamMcqModel(String question, String option1, String option2, String option3, String option4,
                        long correctanswer, long chapter, long questionset) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        this.mcqState = STATE.UNANSWERED;
    }

    public void setMcqState(STATE mcqState) {
        this.mcqState = mcqState;
    }

    public long getAnswered() {
        return answered;
    }

    public ExamMcqModel(Map<String, Object> map) {
        this.question = (String)map.get("question");
        this.option1 = (String)map.get("option1");
        this.option2 = (String)map.get("option2");
        this.option3 = (String)map.get("option3");
        this.option4 = (String)map.get("option4");
        this.correctanswer = (long)map.get("correctanswer");
        this.chapter = (long)map.get("chapter");
        this.questionset = (long)map.get("questionset");
        this.mcqState = STATE.UNANSWERED;
        Log.d("EXAM", chapter+"   STATE");
    }

    @Override
    public String toString() {
        return "ExamMcqModel{" +
                "answered=" + answered +
                ", mcqState=" + mcqState +
                ", question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", correctanswer=" + correctanswer +
                ", chapter=" + chapter +
                ", questionset=" + questionset +
                '}';
    }

    public void setAnswered(long answered) {
        this.answered = answered;
        this.mcqState = getMcqState(answered);
    }

    public STATE getMcqState() {
        return mcqState;
    }

    private STATE getMcqState(long answered){
        if(answered == getCorrectanswer()){
            return STATE.CURRECT;
        }
        return STATE.WRONG;
    }
}
