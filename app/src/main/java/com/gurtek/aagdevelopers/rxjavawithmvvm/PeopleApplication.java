package com.gurtek.aagdevelopers.rxjavawithmvvm;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.gurtek.aagdevelopers.rxjavawithmvvm.network.PeopleDataApi;
import com.gurtek.aagdevelopers.rxjavawithmvvm.network.RetrofitBuilder;

/**
 * * Created by Gurtek Singh on 7/19/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class PeopleApplication extends Application {


    private PeopleDataApi peopleDataApi;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        peopleDataApi = RetrofitBuilder.build();

    }

    public PeopleDataApi getRetorfitApi(){
        return peopleDataApi;
    }
}
