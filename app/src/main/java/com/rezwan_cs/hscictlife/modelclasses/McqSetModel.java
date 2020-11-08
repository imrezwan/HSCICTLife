package com.rezwan_cs.hscictlife.modelclasses;

public class McqSetModel {
    long totalMcq;
    long doneMcq;
    long howManyTimesRead;

    public McqSetModel(long totalMcq, long doneMcq) {
        this.totalMcq = totalMcq;
        this.doneMcq = doneMcq;
        howManyTimesRead = 0;
    }

    public McqSetModel(long totalMcq, long doneMcq, long howManyTimesRead) {
        this.totalMcq = totalMcq;
        this.doneMcq = doneMcq;
        this.howManyTimesRead = howManyTimesRead;
    }

    public long getTotalMcq() {
        return totalMcq;
    }

    public void setTotalMcq(long totalMcq) {
        this.totalMcq = totalMcq;
    }

    public long getDoneMcq() {
        return doneMcq;
    }

    public void setDoneMcq(long doneMcq) {
        this.doneMcq = doneMcq;
    }

    public long getHowManyTimesRead() {
        return howManyTimesRead;
    }

    public void setHowManyTimesRead(long howManyTimesRead) {
        this.howManyTimesRead = howManyTimesRead;
    }
}
