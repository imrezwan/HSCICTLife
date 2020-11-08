package com.rezwan_cs.hscictlife.modelclasses;

public class McqState {
    STATE state;

    public McqState(STATE state) {
        this.state = state;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public enum STATE  {
        CURRECT, WRONG, UNANSWERED
    }
}
