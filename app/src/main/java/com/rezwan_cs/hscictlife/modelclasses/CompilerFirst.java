package com.rezwan_cs.hscictlife.modelclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompilerFirst {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sid")
    @Expose
    private String sid;

    public CompilerFirst() {
    }

    public CompilerFirst(String status, String sid) {
        super();
        this.status = status;
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


}
