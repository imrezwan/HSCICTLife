package com.rezwan_cs.hscictlife.modelclasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class CCode {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "codes")
    String codes;
    @ColumnInfo(name = "inputs")
    String inputs;
    @ColumnInfo(name = "outputs")
    String outputs;

    public CCode(String codes, String inputs, String outputs) {
        this.codes = codes;
        this.inputs = inputs;
        this.outputs = outputs;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getOutputs() {
        return outputs;
    }

    public void setOutputs(String outputs) {
        this.outputs = outputs;
    }

    @Ignore
    @Override
    public String toString() {
        return "CCode{" +
                "id=" + id +
                ", codes='" + codes + '\'' +
                ", inputs='" + inputs + '\'' +
                ", outputs='" + outputs + '\'' +
                '}';
    }
}
