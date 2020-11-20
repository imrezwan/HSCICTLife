package com.rezwan_cs.hscictlife.interfaces;

import com.rezwan_cs.hscictlife.modelclasses.CompilerFinal;
import com.rezwan_cs.hscictlife.modelclasses.CompilerFirst;
import com.squareup.okhttp.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CompilerService {

    @FormUrlEncoded
    @POST("main.php")
    Call<CompilerFirst> compilerFirstCall(
            @Field("lang")String lang,
            @Field("code")String code,
            @Field("input")String input,
            @Field("save")boolean save
    );

    @FormUrlEncoded
    @POST("submissionResult.php")
    Call<CompilerFinal> compilerFinalCall(
            @Field("sid")String sid,
            @Field("requestType")String requestType
    );

}
