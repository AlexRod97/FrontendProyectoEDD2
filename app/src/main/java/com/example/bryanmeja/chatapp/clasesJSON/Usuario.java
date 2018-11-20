package com.example.bryanmeja.chatapp.clasesJSON;

public class Usuario {

    String nombre;
    String username;
    String password;
    String email;
    String id;

    public Usuario(String _id, String nombre, String email, String username, String password){

        this.id = _id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }
}
