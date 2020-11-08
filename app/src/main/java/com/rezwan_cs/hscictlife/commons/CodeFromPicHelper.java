package com.rezwan_cs.hscictlife.commons;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

public class CodeFromPicHelper {



    public static String getResultCodeFormated(Text visionText){
        String txt =  visionText.getText();
        String showText = "";
        for (Text.TextBlock block : visionText.getTextBlocks()) {
            String blockText = block.getText();
            showText += blockText+"\n";
        }
        return showText;
    }
}
