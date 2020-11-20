package com.rezwan_cs.hscictlife.commons;

public class RetrofitInstance {
    private final static String BASE_URL = "https://bloodaid.hipranto.com/";
    private static Retrofit retrofit;
    private static RetrofitInstance mInstance;

    public RetrofitInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static synchronized Retrofit getRetrofitInstance(){
        if(retrofit == null){
            new RetrofitInstance();
        }
        return retrofit;
    }
}

