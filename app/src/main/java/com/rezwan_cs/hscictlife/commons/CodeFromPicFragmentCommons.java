package com.rezwan_cs.hscictlife.commons;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class CodeFromPicFragmentCommons extends Fragment {
    protected EditText mCodes;
    protected TextView mCodeLineNumber;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result =
                    CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                if (result != null) {
                    Uri uri = result.getUri(); //path of image in phone
                    try {
                        extractTextFromImage(uri); //method to extract text from image
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d("COmmons", "get Called");
        }
    }


    protected void extractTextFromImage(Uri uri) throws IOException {
        //FirebaseVisionImage Object
        InputImage image = null;
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            image = InputImage.fromFilePath(getContext(), uri);
        }*/
        image = InputImage.fromFilePath(getActivity(), uri);

        TextRecognizer recognizer = TextRecognition.getClient();
        Task<Text> result =
                recognizer.process(image)
                        .addOnSuccessListener(new OnSuccessListener<Text>() {
                            @Override
                            public void onSuccess(Text visionText) {
                                String resultText = visionText.getText();
                                String showText = CodeFromPicHelper.getResultCodeFormated(visionText);
                                Log.d("ML Text: ", visionText.getText());
                                Log.d("---------", "-----------------------");
                                Log.d("ML ShowText: ", showText);

                                String prevText = mCodes.getText().toString();
                                mCodes.setText(prevText+"\n"+showText);
                                resetCodeLineNumberText();
                            }
                        })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                        Toast.makeText(getActivity(),
                                                Constants.ML_RECOG_ERR, Toast.LENGTH_LONG).show();
                                    }
                                });

    }

    protected void resetCodeLineNumberText() {
        String lineCountText = CodeEditorHelper.getCodeLineCountText(
                mCodes.getLineCount());

        mCodeLineNumber.setText(lineCountText);
    }

    protected void liveUpdateCodeLineNumber() {
        mCodes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resetCodeLineNumberText();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
