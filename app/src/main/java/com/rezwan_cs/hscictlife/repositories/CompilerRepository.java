package com.rezwan_cs.hscictlife.repositories;

import android.util.Log;

import com.rezwan_cs.hscictlife.commons.RetrofitInstance;
import com.rezwan_cs.hscictlife.interfaces.CompilerService;
import com.rezwan_cs.hscictlife.modelclasses.CompilerFinal;
import com.rezwan_cs.hscictlife.modelclasses.CompilerFirst;
import com.rezwan_cs.hscictlife.modelclasses.ExamMcqModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompilerRepository {

    OnCompilerRepository compilerListener;
    public CompilerRepository(OnCompilerRepository compilerRepository){
        this.compilerListener = compilerRepository;
    }

    public void getFinalCompilerResult(String code, String input){
        final Call<CompilerFirst> call = RetrofitInstance.getRetrofitInstance()
                .create(CompilerService.class)
                .compilerFirstCall("c", code, input, true);

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
                            Thread.sleep(3000);
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
                        /*Log.d("Compiler: ", response.body().toString());
                        if(response.body().getStatus().equals("IN-QUEUE")){
                            //setExperimentCompilerAPI();
                        }*/
                        compilerListener.onFinalCompilerResult(response.body());
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


    public interface OnCompilerRepository{
        void onFinalCompilerResult(CompilerFinal compilerFinal);
        void onError(Exception e);
    }
}
