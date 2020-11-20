package com.rezwan_cs.hscictlife.modelclasses;

import com.google.firebase.firestore.Exclude;

public class ChapterModel {

    String chapterCountName;
    String chapterName;
    int chapterIcon;
    @Exclude
    boolean checked;

    public ChapterModel(String chapterCountName, String chapterName, int chapterIcon) {
        this.chapterCountName = chapterCountName;
        this.chapterName = chapterName;
        this.chapterIcon = chapterIcon;
        checked = false;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getChapterCountName() {
        return chapterCountName;
    }

    public void setChapterCountName(String chapterCountName) {
        this.chapterCountName = chapterCountName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getChapterIcon() {
        return chapterIcon;
    }

    public void setChapterIcon(int chapterIcon) {
        this.chapterIcon = chapterIcon;
    }
}
