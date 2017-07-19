package com.gurtek.aagdevelopers.rxjavawithmvvm.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gurtek.aagdevelopers.rxjavawithmvvm.PeopleApplication;
import com.gurtek.aagdevelopers.rxjavawithmvvm.model.UserInfoModel;
import com.gurtek.aagdevelopers.rxjavawithmvvm.network.PeopleDataApi;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
        progreebar=new ObservableInt(View.VISIBLE);



        peopleApi=((PeopleApplication)context.getApplicationContext()).getRetorfitApi();
    }

    public void findUserByemail(String email){

        peopleApi.getPeopleInfo(email,API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(new Function<JsonElement, ObservableSource<? extends UserInfoModel>>() {
                    @Override
                    public ObservableSource<? extends UserInfoModel> apply(JsonElement jsonElement) throws Exception {

                        UserInfoModel model=new UserInfoModel();

                        JsonObject parentObject = jsonElement.getAsJsonObject();

                        JsonArray photos = parentObject.getAsJsonArray("photos");
                        JsonObject childPhoto = photos.get(0).getAsJsonObject();
                        model.userImageUrl = childPhoto.get("url").getAsString();

                        JsonObject childInfo = parentObject.getAsJsonObject("contactInfo");

                        model.userFamilyName=childInfo.get("familyName").getAsString();
                        model.userName=childInfo.get("fullName").getAsString();
                        model.userGivenName=childInfo.get("givenName").getAsString();

                        JsonObject childLocation = parentObject.getAsJsonObject("demographics");
                        JsonObject childofLocation = childLocation.getAsJsonObject("locationDeduced");
                        model.userLocation= childofLocation.get("deducedLocation").getAsString();



                        return Observable.just( model);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfoModel>() {
            @Override
            public void accept(UserInfoModel userInfoModel) throws Exception {
                progreebar.set(View.GONE);
                bottomView.set(View.VISIBLE);
                if (userInfoModel.userImageUrl.length() > 0) userImage.set(View.VISIBLE);

                username.set(userInfoModel.userName);
                givenname.set(userInfoModel.userGivenName);
                familyname.set(userInfoModel.userFamilyName);
                userlocation.set(userInfoModel.userLocation);
                userImageUrl.set(userInfoModel.userImageUrl);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                Log.e("Error","Error"+throwable);
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


    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
       // Glide.with(imageView.getContext()).load(url).into(imageView);
    }




}
