package com.gurtek.aagdevelopers.rxjavawithmvvm.network;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * * Created by Gurtek Singh on 7/19/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public interface PeopleDataApi {

    /*https://api.fullcontact.com/v2/person.json?email=kapillikes@gmail.com&apiKey=a315862323a7d6da*/

    @GET("person.json")
    Observable<JsonElement> getPeopleInfo(
            @Query("email") String email,
            @Query("apiKey") String apiKey);

}
