package com.example.khlopunov.chucknorris.interfaces;

import com.example.khlopunov.chucknorris.POJO.Value;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 23.12.2016.
 */

public interface ChuckNorrisApi {
    @GET("/jokes/random")
    Call<Value> getJoke();
}
