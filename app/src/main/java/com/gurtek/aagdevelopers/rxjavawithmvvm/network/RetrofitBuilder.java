package com.gurtek.aagdevelopers.rxjavawithmvvm.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * * Created by Gurtek Singh on 7/19/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class RetrofitBuilder {


    private static final String URL = "https://api.fullcontact.com/v2/";
    private static Retrofit retrofit;

    public static PeopleDataApi build(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(provideClient(provideLogger()))
                    .build();
        }
        return retrofit.create(PeopleDataApi.class);
    }


   private static OkHttpClient provideClient(HttpLoggingInterceptor interceptor){
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


   private static HttpLoggingInterceptor provideLogger(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
