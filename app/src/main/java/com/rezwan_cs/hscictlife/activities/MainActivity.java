package com.rezwan_cs.hscictlife.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.rezwan_cs.hscictlife.R;
import com.rezwan_cs.hscictlife.commons.RetrofitInstance;
import com.rezwan_cs.hscictlife.fragments.mainpage.HomeFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.MoreFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.QuizFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.RankingFragment;
import com.rezwan_cs.hscictlife.fragments.mainpage.ToolsFragment;
import com.rezwan_cs.hscictlife.interfaces.CompilerService;
import com.rezwan_cs.hscictlife.modelclasses.CompilerFinal;
import com.rezwan_cs.hscictlife.modelclasses.CompilerFirst;
import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    int MY_REQUEST_CODE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavigationBar();
        // selectedFragment = new HomeFragment();
        navigation.setSelectedItemId(R.id.nav_quiz);

        checkIfUpdateIsAvailable();

    }

    private void checkIfUpdateIsAvailable() {
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    /*&& appUpdateInfo.updatePriority() >= HIGH_PRIORITY_UPDATE*/
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request an immediate update.
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            // Pass the intent that is returned by 'getAppUpdateInfo()'.
                            appUpdateInfo,
                            // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                            AppUpdateType.IMMEDIATE,
                            // The current activity making the update request.
                            this,
                            // Include a request code to later monitor this update request.
                            MY_REQUEST_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* private void setExperimentCompilerAPI() {

         String code = "#include<stdio.h>\n" +
                 "\n" +
                 "int main() {\n" +
                 "    int i = 0;\n" +
                 "    for (i=0;i<10;i++){\n" +
                 "      printf(\"%d\", i);\n" +
                 "    }\n" +
                 "    return 0;\n" +
                 "}";

         Log.d("Compiler code: ", code);
         final Call<CompilerFirst> call = RetrofitInstance.getRetrofitInstance()
                 .create(CompilerService.class)
                 .compilerFirstCall("c", code, "", true);


         Thread T = new Thread(new Runnable() {
             @Override
             public void run() {
                 call.enqueue(new Callback<CompilerFirst>() {
                     @Override
                     public void onResponse(Call<CompilerFirst> call, Response<CompilerFirst> response) {
                         String sid = response.body().getSid();
                         Log.d("Compiler :: ", sid);
                         try{
                             Log.d("Compiler After: ", "vbefoire");
                             Thread.sleep(4000);
                             finalCompilerApiCall(sid);
                             Log.d("Compiler After: ", "afere");
                         }
                         catch (Exception e){

                         }

                     }
                     @Override
                     public void onFailure(Call<CompilerFirst> call, Throwable t) {
                         Log.d("Compiler Err: ", t.toString());
                     }
                 });
             }
         });
         T.start();

     }

     private void finalCompilerApiCall(String sid) {
         final Call<CompilerFinal> call = RetrofitInstance.getRetrofitInstance()
                 .create(CompilerService.class)
                 .compilerFinalCall(sid, "fetchResults");

         Thread thread = new Thread(new Runnable() {
             @Override
             public void run() {
                 call.enqueue(new Callback<CompilerFinal>() {
                     @Override
                     public void onResponse(Call<CompilerFinal> call, Response<CompilerFinal> response) {
                         Log.d("Compiler: ", response.body().toString());
                         if(response.body().getStatus().equals("IN-QUEUE")){
                             setExperimentCompilerAPI();
                         }
                     }
                     @Override
                     public void onFailure(Call<CompilerFinal> call, Throwable t) {
                         Log.d("Compiler Err: ", t.toString());
                     }
                 });
             }
         });
         thread.start();
     }
 */
    private void initBottomNavigationBar() {
        navigation =
                findViewById(R.id.nav_main_bottom_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            Toast.makeText(this, "Stated Updating...", Toast.LENGTH_SHORT).show();
            if (resultCode != RESULT_OK) {
                checkIfUpdateIsAvailable();
            }
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                /*    case R.id.nav_home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;*/
                    case R.id.nav_quiz:
                        fragment = QuizFragment.newInstance();
                        loadFragment(fragment);
                        return true;
                   /* case R.id.nav_ranking:
                        fragment = new RankingFragment();
                        loadFragment(fragment);
                        return true;*/
                    case R.id.nav_more:
                        fragment = MoreFragment.newInstance();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_tools:
                        fragment = ToolsFragment.newInstance();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        };



    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations
                (android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        transaction.replace(R.id.container_area, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 3000) {    // 3 secs
            backPressedTime = t;
            Toast.makeText(this, "Please double tap to EXIT",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            finish();
        }
    }
}