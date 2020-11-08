package com.rezwan_cs.hscictlife.modelclasses;

import com.rezwan_cs.hscictlife.interfaces.IMcqModel;

public class ExamMcqModel extends McqModel implements IMcqModel {
    long answered;
    McqState mcqState;

    public ExamMcqModel(){}

    public ExamMcqModel(long answered) {
        this.answered = answered;
    }

    public ExamMcqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset, long answered) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        this.answered = answered;
        mcqState = getMcqState(answered);
    }

    public ExamMcqModel(String question, String option1, String option2, String option3, String option4, long correctanswer, long chapter, long questionset) {
        super(question, option1, option2, option3, option4, correctanswer, chapter, questionset);
        this.mcqState = new McqState(McqState.STATE.UNANSWERED);
    }

    public void setMcqState(McqState mcqState) {
        this.mcqState = mcqState;
    }

    public long getAnswered() {
        return answered;
    }

    public void setAnswered(long answered) {
        this.answered = answered;
        this.mcqState = getMcqState(answered);
    }

    public McqState getMcqState() {
        return mcqState;
    }

    private McqState getMcqState(long answered){
        if(answered == getCorrectanswer()){
            return new McqState(McqState.STATE.CURRECT);
        }
        return new McqState(McqState.STATE.WRONG);
    }
}
