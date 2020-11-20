package com.rezwan_cs.hscictlife.utilities;

import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.modelclasses.ChapterModel;

import java.util.ArrayList;

public class ChapterNames {

    private static ArrayList<ChapterModel> chapterModelArrayList = new ArrayList<>();


    public static ArrayList<ChapterModel> getChapterModelList(){
        if(chapterModelArrayList.isEmpty()){
            chapterModelArrayList.add(new ChapterModel("অধ্যায় ১",
                            "বিশ্ব ও বাংলাদেশ প্রেক্ষিত", R.drawable.ic_chapter_1));
            chapterModelArrayList.add(new ChapterModel("অধ্যায় ২",
                    "কমিউনিকেশন সিস্টেম ও নেটওয়ার্কিং", R.drawable.ic_chapter_2));
            chapterModelArrayList.add(new ChapterModel("অধ্যায় ৩",
                    "সংখ্যাপদ্ধতি ও ডিজিটাল ডিভাইস", R.drawable.ic_chapter_3));
            chapterModelArrayList.add(new ChapterModel("অধ্যায় ৪",
                    "ওয়েব ডিজাইন পরিচিতি ও HTML", R.drawable.ic_chapter_4));
            chapterModelArrayList.add(new ChapterModel("অধ্যায় ৫",
                    "প্রোগামিং ভাষা", R.drawable.ic_chapter_5));
            chapterModelArrayList.add(new ChapterModel("অধ্যায় ৬",
                    "ডেটাবেজ ম্যানেজমেন্ট সিস্টেম", R.drawable.ic_chapter_6));

        }
        return chapterModelArrayList;
    }


}
