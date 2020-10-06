package com.rezwan_cs.hscictlife.utilities;

import android.app.Activity;
import android.content.Context;

public class DefaultTransition {

    public static void activityTransition(Activity context){
        context.overridePendingTransition(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
    }
}
