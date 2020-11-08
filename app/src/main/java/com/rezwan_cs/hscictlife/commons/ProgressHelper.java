package com.rezwan_cs.hscictlife.commons;

public class ProgressHelper {

    public static int getPercentagesOfProgress(long done, long total){
        int percent = (int)((1.0*done/total)*100);
        return percent;
    }

    public static String getMcqSetProgressText(long done, long total){
        return LanguageChangeHelper.englishToBanglaNumber(done+"")+
                "/"+
                LanguageChangeHelper.englishToBanglaNumber(total+"");
    }

    public static String getHowManyTimesMcqSetRead(long t){

        String s = "<b>"+LanguageChangeHelper.englishToBanglaNumber(t+"")+
                " বার </b>"+ Constants.MCQ_SET_HOW_MANY;
        return s;
    }

    public static String getMcqPlayRemainingQuestionText(String remainingValue) {
        return "আরো "+
                LanguageChangeHelper.englishToBanglaNumber(remainingValue)
                +" টি প্রশ্ন বাকি আছে";
    }

    public static String getMcqExamRemainingQuestionText(String remainingValue) {
        return "আরো "+
                LanguageChangeHelper.englishToBanglaNumber(remainingValue)
                +" টি প্রশ্ন উত্তর করা বাকি";
    }

}
