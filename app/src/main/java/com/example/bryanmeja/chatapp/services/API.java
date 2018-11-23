package com.example.bryanmeja.chatapp.services;

import com.example.bryanmeja.chatapp.clasesJSON.Usuario;
import com.example.bryanmeja.chatapp.clasesJSON.messages;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @POST("/messages/send")
    Call<messages> sendMessage(@Body messages mensaje);

    @GET("/messages/receive")
    Call<ResponseBody> receiveMessages();

    @POST("/users/signup")
    Call<Usuario> signup(@Body Usuario nuevoUsuario);

    @POST("/users/login")
    Call<ResponseBody> login(@Body ResponseBody usuario);

}
