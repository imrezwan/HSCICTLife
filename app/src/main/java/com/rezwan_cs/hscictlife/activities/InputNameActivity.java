package com.rezwan_cs.hscictlife.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.Constants;
import com.rezwan_cs.hscictlife.commons.SharedPrefHelper;

public class InputNameActivity extends AppCompatActivity {
    TextInputLayout mInputName ;
    Button mNext;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        mInputName = findViewById(R.id.input_name);
        mNext = findViewById(R.id.btn_next_);
        mAuth = FirebaseAuth.getInstance();





        if(!SharedPrefHelper.getString(InputNameActivity.this, Constants.NAME_KEY, "").equals("")){
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
        else{
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                                if(user != null){
                                    Toast.makeText(InputNameActivity.this,
                                            "অনুগ্রহ করে আপনার নাম প্রবেশ করান", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mInputName.getEditText().getText().toString().trim();
                if(!name.isEmpty()){
                    SharedPrefHelper.save(InputNameActivity.this, Constants.NAME_KEY, name);
                    goToMainActivity();
                }
                else{
                    mInputName.setError("অনুগ্রহ করে নিজের নাম প্রবেশ করান");
                }

            }
        });


    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}