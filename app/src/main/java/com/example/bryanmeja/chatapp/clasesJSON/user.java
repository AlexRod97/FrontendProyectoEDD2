package com.example.bryanmeja.chatapp.clasesJSON;

import com.google.gson.annotations.SerializedName;

public class user {

    @SerializedName("nombre")
    public String nombre;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("email")
    public String email;
    @SerializedName("_id")
    public String id;



    public user(String nombre, String email, String username, String password){

        this.email = email;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

}
