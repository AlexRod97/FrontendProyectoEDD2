package com.example.bryanmeja.chatapp.services;

import com.example.bryanmeja.chatapp.clasesJSON.Token;
import com.example.bryanmeja.chatapp.clasesJSON.user;
import com.example.bryanmeja.chatapp.clasesJSON.messages;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @POST("/messages/send")
    Call<messages> sendMessage(@Body messages mensaje);

    @GET("/messages/receive")
    Call<List<messages>> receiveMessages(@Header("Authorization") String token);

    @POST("/users/signup")
    Call<user> signup(@Body user nuevoUser);

    @POST("/users/login")
    @FormUrlEncoded
    Call<Token> login(@Field("email") String email, @Field("password") String password);

    @GET("/users/obtain")
    Call<List<user>> obtainUser(@Header("Authorization") String token);

    @POST("/users/single")
    @FormUrlEncoded
    Call<user> obtainSingle(@Field("email") String email);
}
