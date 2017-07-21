package com.gurtek.aagdevelopers.rxjavawithmvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * * Created by Gurtek Singh on 7/21/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class UserInfo {


    @SerializedName("status")
    public int status;
    @SerializedName("requestId")
    public String requestId;
    @SerializedName("likelihood")
    public double likelihood;
    @SerializedName("photos")
    public List<Photos> photos;
    @SerializedName("contactInfo")
    public ContactInfo contactInfo;
    @SerializedName("demographics")
    public Demographics demographics;
    @SerializedName("socialProfiles")
    public List<SocialProfiles> socialProfiles;
    @SerializedName("digitalFootprint")
    public DigitalFootprint digitalFootprint;

    public static class Photos {
        @SerializedName("type")
        public String type;
        @SerializedName("typeId")
        public String typeId;
        @SerializedName("typeName")
        public String typeName;
        @SerializedName("url")
        public String url;
        @SerializedName("isPrimary")
        public boolean isPrimary;
    }

    public static class ContactInfo {
        @SerializedName("familyName")
        public String familyName;
        @SerializedName("fullName")
        public String fullName;
        @SerializedName("givenName")
        public String givenName;
    }

    public static class City {
        @SerializedName("name")
        public String name;
    }

    public static class State {
        @SerializedName("deduced")
        public boolean deduced;
        @SerializedName("name")
        public String name;
    }

    public static class Country {
        @SerializedName("deduced")
        public boolean deduced;
        @SerializedName("name")
        public String name;
        @SerializedName("code")
        public String code;
    }

    public static class Continent {
        @SerializedName("deduced")
        public boolean deduced;
        @SerializedName("name")
        public String name;
    }

    public static class LocationDeduced {
        @SerializedName("normalizedLocation")
        public String normalizedLocation;
        @SerializedName("deducedLocation")
        public String deducedLocation;
        @SerializedName("city")
        public City city;
        @SerializedName("state")
        public State state;
        @SerializedName("country")
        public Country country;
        @SerializedName("continent")
        public Continent continent;
        @SerializedName("likelihood")
        public double likelihood;
    }

    public static class Demographics {
        @SerializedName("locationDeduced")
        public LocationDeduced locationDeduced;
        @SerializedName("gender")
        public String gender;
        @SerializedName("locationGeneral")
        public String locationGeneral;
    }

    public static class SocialProfiles {
        @SerializedName("type")
        public String type;
        @SerializedName("typeId")
        public String typeId;
        @SerializedName("typeName")
        public String typeName;
        @SerializedName("url")
        public String url;
    }

    public static class Scores {
        @SerializedName("provider")
        public String provider;
        @SerializedName("type")
        public String type;
        @SerializedName("value")
        public int value;
    }

    public static class Topics {
        @SerializedName("provider")
        public String provider;
        @SerializedName("value")
        public String value;
    }

    public static class DigitalFootprint {
        @SerializedName("scores")
        public List<Scores> scores;
        @SerializedName("topics")
        public List<Topics> topics;
    }
}
