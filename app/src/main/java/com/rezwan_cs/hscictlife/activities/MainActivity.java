package com.rezwan_cs.hscictlife.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavigationBar();
        // selectedFragment = new HomeFragment();
        navigation.setSelectedItemId(R.id.nav_quiz);


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
                        fragment = new QuizFragment();
                        loadFragment(fragment);
                        return true;
                   /* case R.id.nav_ranking:
                        fragment = new RankingFragment();
                        loadFragment(fragment);
                        return true;*/
                    case R.id.nav_more:
                        fragment = new MoreFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_tools:
                        fragment = new ToolsFragment();
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
}