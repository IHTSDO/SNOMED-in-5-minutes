package com.jorgegatica.snomedbrowser.service;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SnomedAPI {

    public static final String BASE_URL = "http://browser.ihtsdotools.org/";
    public static final String API_BASE = "api/v1/snomed/";
    public static final String EDITION = "en-edition";
    public static final String RELEASE = "v20180131";
    public static final String URL = API_BASE + EDITION + "/" + RELEASE;


    /**
     * CLIENT WITH OKHTTP
     *
     * @return
     */
    private static OkHttpClient buildClient() {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }


    private static Retrofit retrofitService = new Retrofit.Builder()
            .client(buildClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    public static SnomedService createDescriptionService() {
        return retrofitService.create(SnomedService.class);
    }

}
