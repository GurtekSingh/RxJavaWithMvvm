package com.gurtek.aagdevelopers.rxjavawithmvvm.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gurtek.aagdevelopers.rxjavawithmvvm.PeopleApplication;
import com.gurtek.aagdevelopers.rxjavawithmvvm.model.UserInfo;
import com.gurtek.aagdevelopers.rxjavawithmvvm.model.UserInfoModel;
import com.gurtek.aagdevelopers.rxjavawithmvvm.network.PeopleDataApi;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * * Created by Gurtek Singh on 7/19/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class PeopleViewModel {


    private final PeopleDataApi peopleApi;
    private Context context;
    public ObservableInt bottomView;
    public ObservableInt userImage;
    public ObservableInt progreebar;


    private String API_KEY="a315862323a7d6da";

    public ObservableField<String> userlocation=new ObservableField<>();
    public ObservableField<String> username=new ObservableField<>();
    public ObservableField<String> givenname=new ObservableField<>();
    public ObservableField<String> familyname=new ObservableField<>();
    public ObservableField<String> userImageUrl=new ObservableField<>();



    public PeopleViewModel(Context context) {
        this.context = context;
        bottomView=new ObservableInt(View.GONE);
        userImage=new ObservableInt(View.GONE);
        progreebar=new ObservableInt(View.GONE);



        peopleApi=((PeopleApplication)context.getApplicationContext()).getRetorfitApi();
    }

    public void findUserByemail(String email){

        progreebar.set(View.VISIBLE);

        peopleApi.getPeopleInfo(email,API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .filter(new Predicate<UserInfo>() {
                    @Override
                    public boolean test(UserInfo userInfo) throws Exception {
                        return userInfo.contactInfo!=null;
                    }
                })
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {

                        progreebar.set(View.GONE);
                        bottomView.set(View.VISIBLE);

                        if (userInfo.photos!=null&& userInfo.photos.get(0)!=null)
                            userImage.set(View.VISIBLE);

                        if (userInfo.contactInfo!=null) {
                            username.set(userInfo.contactInfo.fullName);
                            givenname.set(userInfo.contactInfo.givenName);
                            familyname.set(userInfo.contactInfo.familyName);
                        }

                       if (userInfo.demographics!=null && userInfo.demographics.locationGeneral!=null)
                           userlocation.set(userInfo.demographics.locationGeneral);
                        if (userInfo.photos!=null && userInfo.photos.get(0)!=null)
                        userImageUrl.set(userInfo.photos.get(0).url);

                    }
                });


    }




    public String getUserImageUser(){
        return userImageUrl.get();
    }

    public String getUserFullName(){
        return username.get();
    }

    public String getUserGivenName(){
        return givenname.get();
    }

    public String getUserFamilyName(){
        return  familyname.get();
    }

    public String getUserLocation(){
        return  userlocation.get();
    }


    @BindingAdapter("android:imageurl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url!=null) {
            Uri uri = Uri.parse(url);
            imageView.setImageURI(uri);
        }
    }




}
