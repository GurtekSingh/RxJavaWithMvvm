package com.gurtek.aagdevelopers.rxjavawithmvvm.model;

/**
 * * Created by Gurtek Singh on 7/19/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class UserInfoModel {

    public String userName="";
    public String userGivenName="";
    public String userFamilyName="";
    public String userLocation="";
    public String userImageUrl="";

    public UserInfoModel(String userName,
                         String userGivenName,
                         String userFamilyName,
                         String userLocation,
                         String userImageUrl) {
        this.userName = userName;
        this.userGivenName = userGivenName;
        this.userFamilyName = userFamilyName;
        this.userLocation = userLocation;
        this.userImageUrl = userImageUrl;
    }

    public UserInfoModel() {

    }
}
