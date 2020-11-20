package com.rezwan_cs.hscictlife.commons;

public class LanguageChangeHelper {

    public static String getMcqSetName(int position){
        String serial = getRankingTextFromNumber(position);
        return serial+" সেট প্রশ্ন";
    }


    public static String englishToBanglaNumber(String numberString){
        String re = "";

        for(char ch : numberString.toCharArray()){
            re += singleCharBangla(ch);
        }
        return re;
    }

    public static String singleCharBangla(Character c){
        if(c == '1'){
            return "১";
        }else if(c == '2'){
            return "২";
        }
        else if(c == '3'){
            return "৩";
        }
        else if(c == '4'){
            return "৪";
        }else if(c == '5'){
            return "৫";
        }else if(c == '6'){
            return "৬";
        }else if(c == '7'){
            return "৭";
        }else if(c == '8'){
            return "৮";
        }else if(c == '9'){
            return "৯";
        }else{
            return "০";
        }

    }


    public static String getRankingTextFromNumber(int c){
        if(c == 1){
            return "প্রথম";
        }else if(c == 2){
            return "দ্বিতীয়";
        }
        else if(c == 3){
            return "তৃতীয়";
        }
        else if(c == 4){
            return "চতুর্থ";
        }else if(c == 5){
            return "পঞ্চম";
        }else if(c == 6){
            return "ষষ্ঠ";
        }else if(c == 7){
            return "সপ্তম";
        }else if(c == 8){
            return "অষ্টম";
        }else if(c == 9){
            return "নবম";
        }else{
            return "দশম";
        }

    }
}
