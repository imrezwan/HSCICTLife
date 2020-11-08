package com.rezwan_cs.hscictlife.modelclasses;

public class ChapterModel {

    String chapterCountName;
    String chapterName;
    int chapterIcon;

    public ChapterModel(String chapterCountName, String chapterName, int chapterIcon) {
        this.chapterCountName = chapterCountName;
        this.chapterName = chapterName;
        this.chapterIcon = chapterIcon;
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
