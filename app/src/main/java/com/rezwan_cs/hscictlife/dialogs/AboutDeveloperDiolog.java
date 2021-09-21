package com.rezwan_cs.hscictlife.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import com.rezwan_cs.hscictlife.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AboutDeveloperDiolog {

    public static void showAboutDevDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.about_developer,null);
        AlertDialog dialog = builder.create();
        CircleImageView git, web,fb;
        fb = v.findViewById(R.id.fb_link);
        git = v.findViewById(R.id.git_link);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rezwan2525"));
                context.startActivity(i);
            }
        });

        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rezwan2525?tab=repositories"));
                context.startActivity(i);
            }
        });


        dialog.setView(v);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
