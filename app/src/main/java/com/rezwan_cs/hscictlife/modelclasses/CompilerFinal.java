package com.rezwan_cs.hscictlife.modelclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompilerFinal {

    @SerializedName("valid")
    @Expose
    private String valid;
    @SerializedName("output")
    @Expose
    private String output;
    @SerializedName("compResult")
    @Expose
    private String compResult;
    @SerializedName("memory")
    @Expose
    private String memory;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("status")
    @Expose
    private String status;

    public CompilerFinal() {
    }


    public CompilerFinal(String valid, String output, String compResult, String memory, String time, String id, String hash, String status) {
        super();
        this.valid = valid;
        this.output = output;
        this.compResult = compResult;
        this.memory = memory;
        this.time = time;
        this.id = id;
        this.hash = hash;
        this.status = status;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCompResult() {
        return compResult;
    }

    public void setCompResult(String compResult) {
        this.compResult = compResult;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CompilerFinal{" +
                "valid='" + valid + '\'' +
                ", output='" + output + '\'' +
                ", compResult='" + compResult + '\'' +
                ", memory='" + memory + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                ", hash='" + hash + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}